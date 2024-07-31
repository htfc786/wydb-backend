package com.htfc786.wydb.model.request;

import com.htfc786.wydb.entity.WyContent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqWyContentAdd {

    @ApiModelProperty("句子所在的段落（从1开始）")
    private Integer paragraph;

    @ApiModelProperty("句子排序，用于还原文章")
    private Integer order;

    @ApiModelProperty("句子内容")
    private String content;

    @ApiModelProperty("句子所对应的翻译")
    private String translations;

    public boolean hasParagraph() {
        return this.paragraph != null;
    }

    public boolean hasOrder() {
        return this.content != null;
    }

    public static WyContent to(ReqWyContentAdd obj) {
        return new WyContent(null, null, null, obj.paragraph, obj.order, obj.content, obj.translations, null);
    }
}
