package com.htfc786.wydb.service;

import com.htfc786.wydb.entity.WyContent;
import com.htfc786.wydb.mapper.WyContentMapper;
import com.htfc786.wydb.model.dto.WyContentOnlyPar;
import com.htfc786.wydb.utils.SentenceSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("WyContentService")
public class WyContentService {

    @Autowired
    private WyContentMapper wyContentMapper;

    /**
     * 通过内容段id 获取文章内容段
     *
     * @param articleid 文章id
     * @param id        内容段id
     * @return 文章段内容
     */
    public WyContent get(Long articleid, Long id) {
        return wyContentMapper.getById(articleid, id);
    }

    /**
     * 通过文章 获取文章全都内容段列表
     *
     * @param articleid 文章id
     * @return 文章段内容列表
     */
    public List<WyContent> getByArticle(Long articleid) {
        return wyContentMapper.getByArticle(articleid);
    }

    /**
     * 通过内容段序号 获取文章内容段
     *
     * @param articleid 文章id
     * @param order     内容段序号
     * @return 文章段内容
     */
    public WyContent getByOrder(Long articleid, Integer order) {
        return wyContentMapper.getByOrder(articleid, order);
    }

    /**
     * 通过段号 获取文章内容段列表
     *
     * @param articleid 文章id
     * @param paragraph 段号
     * @return 文章段内容列表
     */
    public List<WyContent> getByParagraph(Long articleid, Integer paragraph) {
        return wyContentMapper.getByParagraph(articleid, paragraph);
    }

    /**
     * 通过内容段序号 获取文章内容段字符串
     *
     * @param articleid 文章id
     * @param order     内容段序号
     * @return 文章段内容字符串
     */
    public String getContentByOrder(Long articleid, Integer order) {
        return wyContentMapper.getContentByOrder(articleid, order);
    }

    /**
     * 通过段号 获取文章内容段字符串
     *
     * @param articleid 文章id
     * @param paragraph 段号
     * @return 文章段内容字符串
     */
    public String getContentByParagraph(Long articleid, Integer paragraph) {
        List<String> contentList = wyContentMapper.getContentByParagraph(articleid, paragraph);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentList.size(); i++) {
            result.append(contentList.get(i));
        }
        return result.toString();
    }

    /**
     * 通过文章 获取文章内容段字符串
     *
     * @param articleid 文章id
     * @return 文章段内容字符串
     */
    public String getContentByArticle(Long articleid) {
        List<WyContentOnlyPar> contentList = wyContentMapper.getContentByArticle(articleid);
        StringBuilder result = new StringBuilder();
        Integer lastPar = 1;    // 上一个内容段的段号
        for (int i = 0; i < contentList.size(); i++) {
            WyContentOnlyPar content = contentList.get(i);
            // 如果不等于 换自然段
            if (!content.getParagraph().equals(lastPar)) {
                result.append('\n');
            }
            // 重新赋值自然段
            lastPar = content.getParagraph();
            // 添加内容
            result.append(content.getContent());
        }
        return result.toString();
    }

    /**
     * 通过文章 获取指定长度文章内容段字符串
     *
     * @param articleId 文章id
     * @param length    指定长度
     * @return 文章段内容字符串
     */
    public String getContentLength(Long articleId, int length) {
        StringBuilder result = new StringBuilder();
        Integer lastPar = null;    // 上一个内容段的段号
        int order = 1; // 循环到的顺序号
        while (result.length() >= length) {
            // 循环获取内容
            WyContent content = wyContentMapper.getByOrder(articleId, order);
            // 如果不等于 换自然段
            if (!content.getParagraph().equals(lastPar)) {
                result.append('\n');
            }
            // 重新赋值自然段
            lastPar = content.getParagraph();
            // 添加内容
            result.append(content.getContent());
        }
        return result.toString();
    }

    /**
     * 获取最大的顺序号
     *
     * @param articleId 文章id
     * @return 顺序号
     */
    public int getMaxOrder(Long articleId) {
        Integer MaxOrder = wyContentMapper.getMaxOrder(articleId);
        return MaxOrder == null ? 0 : (int) MaxOrder;
    }

    /**
     * 获取最大的段号
     *
     * @param articleId 文章id
     * @return 段号
     */
    public int getMaxParagraph(Long articleId) {
        Integer maxParagraph = wyContentMapper.getMaxParagraph(articleId);
        return maxParagraph == null ? 0 : (int) maxParagraph;
    }

    /**
     * 获取段中最大的顺序号
     *
     * @param articleId 文章id
     * @param paragraph 段号
     * @return 顺序号
     */
    public int getMaxParagraphOrder(Long articleId, int paragraph) {
        Integer MaxOrder = wyContentMapper.getMaxParagraphOrder(articleId, paragraph);
        return MaxOrder == null ? 0 : (int) MaxOrder;
    }

    /**
     * 获取下一个段号
     *
     * @param articleId 文章id
     * @return 段号
     */
    public int getNextOrder(Long articleId) {
        return getMaxOrder(articleId) + 1;
    }

    /**
     * 新添加内容段
     *
     * @param wyContent 内容段对象
     */
    @Transactional
    public void add(WyContent wyContent) {
        Long articleId = wyContent.getArticleId();
        if (wyContent.getOrder() == null && wyContent.getParagraph() == null) {
            // 在没有指定段号和顺序号时：
            wyContent.setParagraph(this.getMaxParagraph(articleId));
            wyContent.setOrder(this.getNextOrder(articleId));
        } else if (wyContent.getParagraph() != null && wyContent.getOrder() == null) {
            // 指定段号 没有指定顺序号时
            int nextOrder = this.getMaxParagraphOrder(articleId, wyContent.getParagraph()) + 1;
            wyContent.setParagraph(wyContent.getParagraph());
            wyContent.setOrder(nextOrder);
            // 后面的向后移动
            this.moveBack(articleId, nextOrder, 1);
        } else if (wyContent.getOrder() != null && wyContent.getParagraph() == null) {
            // 指定顺序号 没有指定段号时
            WyContent orderContent = wyContentMapper.getByOrder(articleId, wyContent.getOrder());
            if (orderContent == null) {
                // 如果没有，走 没有指定段号和顺序号 的逻辑：
                wyContent.setOrder(null);
                this.add(wyContent);
                return;
            }
            // 有的话直接添加
            wyContent.setParagraph(orderContent.getParagraph());
            wyContent.setOrder(wyContent.getOrder());
            this.moveBack(articleId, wyContent.getOrder(), 1);
        } else if (wyContent.getOrder() != null && wyContent.getParagraph() != null) {
            // 在没有段号和顺序号时：
            WyContent orderContent = wyContentMapper.getByOrder(articleId, wyContent.getOrder());
            if (orderContent == null) {
                // 如果没有，走指定段号 的逻辑：
                wyContent.setOrder(null);
                this.add(wyContent);
                return;
            } else if (orderContent.getParagraph().equals(wyContent.getParagraph())) {
                // 如果 查到的段号 == 传来的段号
                // 向同一段添加内容，直接添加
                this.moveBack(articleId, wyContent.getOrder(), 1);
            } else if (orderContent.getParagraph() + 1 == wyContent.getParagraph()) {
                // 如果 查到的段号+1 == 传来的段号 也就是他的下一段
                // 查询下一个内容段，
                // 如果下一个内容的段号 == 传来的段号，说明是要向下一段首部添加内容，直接提交
                // 如果不等，那就。。。添加到指定段末尾把。。。
                WyContent orderNextContent = wyContentMapper.getByOrder(articleId, wyContent.getOrder() + 1);
                if (orderNextContent == null || !orderNextContent.getParagraph().equals(wyContent.getParagraph())){
                    wyContent.setParagraph(null);
                    this.add(wyContent);
                    return;
                } else {
                    this.moveBack(articleId, wyContent.getOrder(), 1);
                }
            } else {
                // 最后，如果什么条件都不满足，直接添加到整篇文章末尾
                wyContent.setParagraph(null);
                wyContent.setOrder(null);
                this.add(wyContent);
                return;
            }
        }
        wyContentMapper.add(wyContent);
    }

    /**
     * 新添加字符串
     *
     * @param collectionId 文集id
     * @param articleId    文章id
     * @param paragraph    段落号
     * @param order        顺序号
     * @param content      内容
     */
    @Transactional
    public void addContentString(Long collectionId, Long articleId, int paragraph, int order, String content) {
        WyContent wyContent = new WyContent(null, collectionId, articleId, paragraph, order, content, null, null);
        add(wyContent);
    }

    /**
     * 新添加一整段文言文内容
     *
     * @param collectionId 文集id
     * @param articleId    文章id
     * @param content      内容
     */
    @Transactional
    public void addContent(Long collectionId, Long articleId, String content) {
        int paragraph = this.getMaxParagraph(articleId) + 1;  // 段号 默认新建一段
        int order = this.getNextOrder(articleId);  // 顺序号 应该查询
        // 先替换掉字符串里的空格 tab
        content = content.replace(" ", "");
        content = content.replace("\t", "");
        // "\n"，换段
        // "。"，"！"，"!"，"？"，"?"，"......"，"..."，"……"，"…"，换句，内容段
        // 分割字符串
        String[] paragraphs = content.split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            String par = paragraphs[i];
            // 分割句
            List<String> contents = SentenceSplitUtils.split(par);
            for (int j = 0; j < contents.size(); j++) {
                // 得到内容段
                String text = contents.get(j);
                // 添加！
                addContentString(collectionId, articleId, paragraph, order, text);
                // 顺序号+1
                order += 1;
            }
            // 段号+1
            paragraph += 1;
        }
        // 结束！
    }

    /**
     * 向后移动后面的内容
     * 举例：
     * 原：[1, 2, 3]
     * 调用函数：startOrder：2对应的序号，step：2
     * => [1, _, _, 2, 3]
     *
     * @param articleId  文章id
     * @param startOrder 开始位置
     * @param step       移动长度
     */
    public void moveBack(Long articleId, int startOrder, int step) {
        int maxOrder = this.getMaxOrder(articleId);
        // 判断越界情况
        if (maxOrder < startOrder) {
            return;
        }

        for (int i = startOrder; i <= maxOrder; i++) {
            // 调用修改接口向后移动step位
            WyContent wyContent = new WyContent();
            wyContent.setOrder(i + step);
            wyContentMapper.updateByOrder(articleId, i, wyContent);
        }
    }

}
