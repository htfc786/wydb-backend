package com.htfc786.wydb.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.htfc786.wydb.model.dto.WyContent;

import java.util.ArrayList;
import java.util.List;

public class SentenceJsonUtil {
    /**
     * Json内容转换为字符串内容
     *
     * @param json json数据
     * @return 文章字符串内容
     */
    public static String jsonContentToString(String json) {
        return jsonContentToString(JSON.parseArray(json));
    }

    /**
     * Json内容转换为字符串内容
     *
     * @param data json数据
     * @return 文章字符串内容
     */
    public static String jsonContentToString(JSONArray data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (i != 0) {
                sb.append("\n");
            }
            JSONArray par = data.getJSONArray(i);
            for (int j = 0; j < par.size(); j++) {
                WyContent wyContent = par.getObject(j, WyContent.class);
                sb.append(wyContent.getContent());
            }
        }
        return sb.toString();
    }

    /**
     * Json内容转换为字符串内容
     *
     * @param data json数据
     * @return 文章字符串内容
     */
    public static String jsonContentToString(List<List<WyContent>> data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (i != 0) {
                sb.append("\n");
            }
            List<WyContent> para = data.get(i);
            for (int j = 0; j < para.size(); j++) {
                WyContent wyContent = para.get(j);
                sb.append(wyContent.getContent());
            }
        }
        return sb.toString();
    }

    /**
     * 分割字符串文章内容
     *
     * @param content 文章内容字符串
     * @return 文章列表对象
     */
    public static List<List<WyContent>> splitContentString(String content) {
        List<List<WyContent>> result = new ArrayList<>();
        List<List<String>> contentList = ChineseSentenceSplitUtils.split(content);
        for (int i = 0; i < contentList.size(); i++) {
            List<String> sentenceList = contentList.get(i);
            List<WyContent> sentenceResult = new ArrayList<>();
            for (int j = 0; j < sentenceList.size(); j++) {
                String sentence = sentenceList.get(j);
                sentenceResult.add(new WyContent(sentence, "", new ArrayList<>()));
            }
            result.add(sentenceResult);
        }
        return result;
    }

    /**
     * 解析内容json
     *
     * @param json json
     * @return 内容对象
     */
    public static List<List<WyContent>> parseContentJsonString(String json) {
        JSONArray data = JSON.parseArray(json);
        List<List<WyContent>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONArray para = data.getJSONArray(i);
            List<WyContent> paraResult = new ArrayList<>();
            for (int j = 0; j < para.size(); j++) {
                WyContent sentence = para.getObject(j, WyContent.class);
                if (sentence.getContent() == null) {
                    sentence.setContent("");
                }
                if (sentence.getTranslation() == null) {
                    sentence.setTranslation("");
                }
                if (sentence.getPinyin() == null) {
                    sentence.setPinyin(new ArrayList<>());
                }
                paraResult.add(sentence);
            }
            result.add(paraResult);
        }
        return result;
    }
}