package com.htfc786.wydb.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 文言文文章表
 *
 * @TableName wy_article
 */
public class WyArticle implements Serializable {

    /**
     * id
     */
    @NotNull(message = "[id]不能为空")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 所属集合ID，关联collection表
     */
    @NotNull(message = "[所属集合ID，关联collection表]不能为空")
    @ApiModelProperty("所属集合ID，关联collection表")
    private Long collectionId;
    /**
     * 文言文文章名称
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("文言文文章名称")
    private String name;
    /**
     * 文章作者
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("文章作者")
    private String writer;
    /**
     * 文章被写成时的的朝代（年份）
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("文章被写成时的的朝代（年份）")
    private String dynasty;
    /**
     * 文章选自的书籍
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("文章选自的书籍")
    private String source;
    /**
     * 文章主旨
     */
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("文章主旨")
    private String mainIdea;
    /**
     * 备注信息
     */
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("备注信息")
    private String note;
    /**
     * 文章内容(json)
     */
    @NotBlank(message = "[文章内容(json)]不能为空")
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("文章内容(json)")
    private String content;
    /**
     * 文章内容(文字)
     */
    @NotBlank(message = "[文章内容(文字)]不能为空")
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("文章内容(文字)")
    private String contentString;
    /**
     * 文章创建时间
     */
    @NotNull(message = "[文章创建时间]不能为空")
    @ApiModelProperty("文章创建时间")
    private Date createdAt;

    public WyArticle() {
    }

    public WyArticle(@NotNull(message = "[id]不能为空") Long id, @NotNull(message = "[所属集合ID，关联collection表]不能为空") Long collectionId, @Size(max = 255, message = "编码长度不能超过255") String name, @Size(max = 255, message = "编码长度不能超过255") String writer, @Size(max = 255, message = "编码长度不能超过255") String dynasty, @Size(max = 255, message = "编码长度不能超过255") String source, @Size(max = -1, message = "编码长度不能超过-1") String mainIdea, @Size(max = -1, message = "编码长度不能超过-1") String note, @NotBlank(message = "[文章内容(json)]不能为空") @Size(max = -1, message = "编码长度不能超过-1") String content, @NotBlank(message = "[文章内容(文字)]不能为空") @Size(max = -1, message = "编码长度不能超过-1") String contentString, @NotNull(message = "[文章创建时间]不能为空") Date createdAt) {
        this.id = id;
        this.collectionId = collectionId;
        this.name = name;
        this.writer = writer;
        this.dynasty = dynasty;
        this.source = source;
        this.mainIdea = mainIdea;
        this.note = note;
        this.content = content;
        this.contentString = contentString;
        this.createdAt = createdAt;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMainIdea() {
        return mainIdea;
    }

    public void setMainIdea(String mainIdea) {
        this.mainIdea = mainIdea;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentString() {
        return contentString;
    }

    public String getContentString(int len) {
        String text = getContentString();
        if (text.length() < len) {
            return text;
        }
        return text.substring(0, len);
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "WyArticle{" +
                "id=" + id +
                ", collectionId=" + collectionId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", contentString='" + contentString + '\'' +
                ", writer='" + writer + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", source='" + source + '\'' +
                ", mainIdea='" + mainIdea + '\'' +
                ", note='" + note + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
