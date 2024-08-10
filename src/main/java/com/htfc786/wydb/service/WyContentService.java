package com.htfc786.wydb.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.htfc786.wydb.entity.WyArticle;
import com.htfc786.wydb.mapper.WyContentMapper;
import com.htfc786.wydb.model.dto.WyContent;
import com.htfc786.wydb.utils.SentenceJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("WyContentService")
public class WyContentService {

    @Autowired
    private WyContentMapper wyContentMapper;

    /**
     * 新添加一整段文言文内容
     *
     * @param articleId 文章id
     * @param content   内容
     */
    public String addContent(Long articleId, String content) {
        return addContent(articleId, content, 0);
    }

    /**
     * 新添加一整段文言文内容
     *
     * @param articleId 文章id
     * @param content   内容
     * @param paragraph 段号，添加位置，0为末尾
     */
    public String addContent(Long articleId, String content, int paragraph) {
        return addContent(articleId, content, paragraph, 0);
    }

    /**
     * 新添加一整段文言文内容
     *
     * @param articleId 文章id
     * @param content   内容
     * @param paragraph 段号，添加位置，0为末尾
     * @param sentence  句子号，添加位置，0为末尾
     */
    public String addContent(Long articleId, String content, int paragraph, int sentence) {
        // 查询之前的内容
        String oldContentJson = wyContentMapper.getContentJson(articleId);
        JSONArray contentObj = JSON.parseArray(oldContentJson);
        // 转换，分段
        List<List<WyContent>> newContentList = SentenceJsonUtil.splitContentString(content);
        if (newContentList.size() == 0) {
            return "没有添加任何内容！";
        }
        // 添加
        // 参数归一化
        if (sentence <= 0) {
            // 没句子肯定要查句子号，默认：最后一句+1
            if (paragraph <= 0) {
                // 我连段也没有，查段，默认：最后一段
                paragraph = contentObj.size();  // 最后一段
            }
            if (paragraph == 0) {   // 如果原本就没内容，那么就直接新建第1段第1句
                paragraph = 1;
                sentence = 1;
                contentObj.add(new ArrayList<>());
            } else {    // 原本有内容，查出第1段，最后1句的句子号，+1
                // 如果这段没内容，查出是0，+1，就是1
                JSONArray lastPara = contentObj.getJSONArray(paragraph - 1);
                sentence = lastPara.size() + 1; // 最后一句的句子号 + 1
            }
        }
        // 指定段落句子添加
        if (paragraph - 1 > contentObj.size()) {
            return "您指定的段号不存在！";
        }  // 段号不对也添加不了啊！
        JSONArray line = contentObj.getJSONArray(paragraph - 1);    // 读取那一段的内容
        if (sentence - 1 > line.size()) {
            return "您指定的句子不存在！";
        } // 句子号不存在也不行啊！
        line.addAll(sentence - 1, newContentList.get(0));   // 把一一个内容直接添加进去，后面的稍后添加
        contentObj.set(paragraph - 1, line);    // 加回总内容对象
        // 第一行接到后面，剩下的新建段
        if (newContentList.size() > 1) {
            newContentList.remove(0);
            contentObj.addAll(paragraph, newContentList);
        }
        // 转换字符串
        String contentStr = SentenceJsonUtil.jsonContentToString(contentObj);
        String contentJson = JSON.toJSONString(contentObj);
        // 存数据库
        _putContent(articleId, contentStr, contentJson);
        return null;
    }

    /**
     * 获取内容字符串
     *
     * @param articleId 文章id
     * @return 内容
     */
    public String getContentString(Long articleId) {
        return wyContentMapper.getContentString(articleId);
    }

    /**
     * 获取内容指定长度字符串
     *
     * @param articleId 文章id
     * @param len       获取长度
     * @return 内容
     */
    public String getContentString(Long articleId, int len) {
        String text = getContentString(articleId);
        if (text.length() < len) {
            return text;
        }
        return text.substring(0, len);
    }

    /**
     * 获取内容
     *
     * @param articleId 文章id
     * @return 内容对象
     */
    public List<List<WyContent>> getContent(Long articleId) {
        String contentJson = wyContentMapper.getContentJson(articleId);
        if (contentJson == null || contentJson.length() < 2) {
            return new ArrayList<>();
        }
        return SentenceJsonUtil.parseContentJsonString(contentJson);
    }

    private void _putContent(Long articleId, String contentStr, String contentJson) {
        // 保存内容
        WyArticle wyArticle = new WyArticle();
        wyArticle.setId(articleId);
        wyArticle.setContent(contentJson);
        wyArticle.setContentString(contentStr);
        wyContentMapper.updateContent(wyArticle);
    }

    public void putContent(Long articleId, List<List<WyContent>> content) {
        // 转换字符串
        String contentStr = SentenceJsonUtil.jsonContentToString(content);
        String contentJson = JSON.toJSONString(content);
        // 保存内容
        _putContent(articleId, contentStr, contentJson);
    }

}