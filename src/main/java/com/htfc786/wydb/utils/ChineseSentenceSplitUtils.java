package com.htfc786.wydb.utils;

import java.util.ArrayList;
import java.util.List;

public class ChineseSentenceSplitUtils {

    /**
     * 句子分段
     *
     * @param content 句子内容
     * @return 分段结果
     */
    public static List<List<String>> split(String content) {
        List<List<String>> result = new ArrayList<>();
        // 先替换掉字符串里的空格 tab
        content = content.replace(" ", "");
        content = content.replace("\t", "");
        // "\n"，换段
        // "。"，"！"，"!"，"？"，"?"，"......"，"..."，"……"，"…"，换句，内容段
        // 分割字符串
        String[] paragraphs = content.split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            String par = paragraphs[i];
            List<String> sentence = splitSentence(par);
            result.add(sentence);
        }
        // 结束！
        return result;
    }

    /**
     * 分割汉语句子
     *
     * @param text 要分割的中文文章字符串
     * @return 包含分割后句子的列表
     */
    public static List<String> splitSentence(String text) {
        List<String> sentences = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return sentences;
        }

        // 使用StringBuilder来构建句子
        StringBuilder currentSentence = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // 遇到结束符，则分割句子
            if (c == '。' || c == '？' || c == '！' || c == '?' || c == '!' || c == '…' || c == '.') {
                currentSentence.append(c);
                // 检查下一个字符
                char nc = 0;
                if (i + 1 != text.length()) {   // 是否超出了句子长度
                    nc = text.charAt(i + 1);
                }
                if ((nc == '”' || nc == '“' || nc == '"') ||        //双引号
                        (nc == '’' || nc == '‘' || nc == '\'') ||   //单引号
                        (nc == '…' || nc == '。' || nc == '.')) {  // 省略号
                    // 判断"。"和"." 是因为可能会出现 "..." "......" "。。。" "。。。。。。" 这样的省略号
                    //先加入
                    currentSentence.append(nc);
                    i++;
                    // 连续标点，全部加入
                    // 反正句子中出现连续的句号没有任何意义，一般来说是不允许出现的情况，
                    //  所以如果出现了就给他添加到一个字段里，不用创建新的字段了
                    char addC = 0;
                    if (i + 1 != text.length()) {   // 是否超出了句子长度
                        addC = text.charAt(i + 1);
                    }
                    while (addC == '。' || addC == '.' ||
                            (addC == '”' || addC == '“' || addC == '"') ||        //双引号
                            (addC == '’' || addC == '‘' || addC == '\'')) {
                        currentSentence.append(addC);
                        i++;

                        if (i + 1 == text.length()) {   // 是否超出了句子长度
                            break;
                        }
                        addC = text.charAt(i + 1);
                    }
                }
                // 去除句子末尾可能的空格
                String trimmedSentence = currentSentence.toString().trim();
                if (!trimmedSentence.isEmpty()) {
                    sentences.add(trimmedSentence);
                }
                currentSentence = new StringBuilder(); // 重置句子构建器
            } else {
                // 将当前字符添加到句子中
                currentSentence.append(c);
            }
        }

        // 添加最后一个句子（如果有）
        String lastSentence = currentSentence.toString().trim();
        if (!lastSentence.isEmpty()) {
            sentences.add(lastSentence);
        }
        return sentences;
    }

}
