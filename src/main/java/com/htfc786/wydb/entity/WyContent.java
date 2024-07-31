package com.htfc786.wydb.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 文言文内容表
* @TableName wy_content
*/
@Data
public class WyContent implements Serializable {

    /**
    * id
    */
    @NotNull(message="[id]不能为空")
    @ApiModelProperty("id")
    private Long id;
    /**
    * 所属集合id，关联collection表
    */
    @NotNull(message="[所属集合id，关联collection表]不能为空")
    @ApiModelProperty("所属集合id，关联collection表")
    private Long collectionId;
    /**
    * 所属文章id，关联article表
    */
    @NotNull(message="[所属文章id，关联article表]不能为空")
    @ApiModelProperty("所属文章id，关联article表")
    private Long articleId;
    /**
    * 句子所在的段落（从1开始）
    */
    @NotNull(message="[句子所在的段落（从1开始）]不能为空")
    @ApiModelProperty("句子所在的段落（从1开始）")
    private Integer paragraph;
    /**
    * 句子排序，用于还原文章
    */
    @NotNull(message="[句子排序，用于还原文章]不能为空")
    @ApiModelProperty("句子排序，用于还原文章")
    private Integer order;
    /**
    * 句子内容
    */
    @NotBlank(message="[句子内容]不能为空")
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("句子内容")
    private String content;
    /**
    * 句子所对应的翻译
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("句子所对应的翻译")
    private String translations;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private Date createdAt;

    public WyContent(@NotNull(message = "[id]不能为空") Long id, @NotNull(message = "[所属集合id，关联collection表]不能为空") Long collectionId, @NotNull(message = "[所属文章id，关联article表]不能为空") Long articleId, @NotNull(message = "[句子所在的段落（从1开始）]不能为空") Integer paragraph, @NotNull(message = "[句子排序，用于还原文章]不能为空") Integer order, @NotBlank(message = "[句子内容]不能为空") @Size(max = -1, message = "编码长度不能超过-1") String content, @Size(max = -1, message = "编码长度不能超过-1") String translations, @NotNull(message = "[创建时间]不能为空") Date createdAt) {
        this.id = id;
        this.collectionId = collectionId;
        this.articleId = articleId;
        this.paragraph = paragraph;
        this.order = order;
        this.content = content;
        this.translations = translations;
        this.createdAt = createdAt;
    }

    public WyContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getParagraph() {
        return paragraph;
    }

    public void setParagraph(Integer paragraph) {
        this.paragraph = paragraph;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
