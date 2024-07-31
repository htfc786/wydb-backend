package com.htfc786.wydb.model.dto;

import com.htfc786.wydb.entity.WyContent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WyContentOnlyPar {

    @ApiModelProperty("句子所在的段落（从1开始）")
    private Integer paragraph;

    @ApiModelProperty("句子排序，用于还原文章")
    private Integer order;

    @ApiModelProperty("句子内容")
    private String content;

    public WyContentOnlyPar() {
    }

    public WyContentOnlyPar(Integer paragraph, String content) {
        this.paragraph = paragraph;
        this.content = content;
    }

    public Integer getParagraph() {
        return this.paragraph;
    }

    public String getContent() {
        return this.content;
    }

    public static WyContent to(WyContentOnlyPar obj) {
        return new WyContent(null, null, null, obj.paragraph, obj.order, obj.content, null, null);
    }
}
