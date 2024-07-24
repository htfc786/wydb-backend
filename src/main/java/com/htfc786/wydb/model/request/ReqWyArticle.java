package com.htfc786.wydb.model.request;

import com.htfc786.wydb.entity.WyArticle;
import com.htfc786.wydb.model.response.ResWyArticleGet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqWyArticle {

    @ApiModelProperty("文言文文章名称")
    private String name;

    @ApiModelProperty("文章作者")
    private String writer;

    @ApiModelProperty("文章被写成时的的朝代（年份）")
    private String dynasty;

    @ApiModelProperty("文章选自的书籍")
    private String source;

    @ApiModelProperty("文章主旨")
    private String mainIdea;

    @ApiModelProperty("备注信息")
    private String note;

    public ReqWyArticle(String name, String writer, String dynasty, String source, String mainIdea, String note) {
        this.name = name;
        this.writer = writer;
        this.dynasty = dynasty;
        this.source = source;
        this.mainIdea = mainIdea;
        this.note = note;
    }

    public ReqWyArticle() {
    }

    public static WyArticle to(ReqWyArticle obj) {
        return new WyArticle(null, null, obj.name, obj.writer, obj.dynasty, obj.source, obj.mainIdea, obj.note, null);
    }
}
