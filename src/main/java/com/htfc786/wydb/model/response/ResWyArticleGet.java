package com.htfc786.wydb.model.response;

import com.htfc786.wydb.entity.WyArticle;
import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.model.dto.WyContent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResWyArticleGet {


    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("文集")
    private WyCollection collection;

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

    @ApiModelProperty("文章内容(json)")
    private List<List<WyContent>> content;

    @ApiModelProperty("文章创建时间")
    private Date createdAt;

    public ResWyArticleGet() {
    }

    public ResWyArticleGet(Long id, WyCollection collection, String name, String writer, String dynasty, String source, String mainIdea, String note, List<List<WyContent>> content, Date createdAt) {
        this.id = id;
        this.collection = collection;
        this.name = name;
        this.writer = writer;
        this.dynasty = dynasty;
        this.source = source;
        this.mainIdea = mainIdea;
        this.note = note;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static ResWyArticleGet to(WyArticle wyArticle, WyCollection wyCollection, List<List<WyContent>> content) {
        return new ResWyArticleGet(wyArticle.getId(), wyCollection, wyArticle.getName(), wyArticle.getWriter(), wyArticle.getDynasty(), wyArticle.getSource(), wyArticle.getMainIdea(), wyArticle.getNote(), content, wyArticle.getCreatedAt());
    }
}
