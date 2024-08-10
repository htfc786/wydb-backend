package com.htfc786.wydb.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqWyContentAddString {

    // 文章内容
    @ApiModelProperty(value = "文章内容", required = true)
    private String content;

    // 段
    @ApiModelProperty(value = "段")
    private Integer paragraph;

    // 句子
    @ApiModelProperty(value = "句子")
    private Integer sentence;

    public ReqWyContentAddString(String content) {
        this.content = content;
    }

    public ReqWyContentAddString() {
    }

    public Integer getParagraph() {
        return paragraph;
    }

    public Integer getSentence() {
        return sentence;
    }

    public static String to(ReqWyContentAddString obj) {
        return obj.content;
    }
}
