package com.htfc786.wydb.model.response;

import com.htfc786.wydb.entity.WyArticle;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ResWyArticleGetListArticle {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("文言文文章名称")
    private String name;

    @ApiModelProperty("文章作者")
    private String writer;

    @ApiModelProperty("文章被写成时的的朝代（年份）")
    private String dynasty;

    @ApiModelProperty("文章选自的书籍")
    private String source;

    @ApiModelProperty("文章简短内容")
    private String content;

    @ApiModelProperty("创建日期")
    private Date createdAt;

    public ResWyArticleGetListArticle(Long id, String name, String writer, String dynasty, String source, String content, Date createdAt) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.dynasty = dynasty;
        this.source = source;
        this.content = content;
        this.createdAt = createdAt;
    }

    public ResWyArticleGetListArticle() {
    }

    public static ResWyArticleGetListArticle to(WyArticle obj, String content) {
        return new ResWyArticleGetListArticle(obj.getId(), obj.getName(), obj.getWriter(), obj.getDynasty(), obj.getSource(), content, obj.getCreatedAt());
    }

    public static ResWyArticleGetListArticle to(WyArticle obj) {
        return to(obj, 80);
    }

    public static ResWyArticleGetListArticle to(WyArticle obj, int len) {
        String content = obj.getContentString(len);
        return new ResWyArticleGetListArticle(obj.getId(), obj.getName(), obj.getWriter(), obj.getDynasty(), obj.getSource(), content, obj.getCreatedAt());
    }

    public static List<ResWyArticleGetListArticle> listTo(List<WyArticle> obj) {
        return listTo(obj, 80);
    }

    public static List<ResWyArticleGetListArticle> listTo(List<WyArticle> obj, int len) {
        List<ResWyArticleGetListArticle> result = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            result.add(to(obj.get(i), len));
        }
        return result;
    }
}
