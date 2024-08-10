package com.htfc786.wydb.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class WyContent {

    /**
     * 句子内容
     */
    @NotBlank(message = "[句子内容]不能为空")
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("句子内容")
    private String content;
    /**
     * 文章句子翻译
     */
    @NotBlank(message = "[文章句子翻译]不能为空")
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("文章句子翻译")
    private String translation;
    /**
     * 文章句子注音（列表）
     */
    @NotBlank(message = "[文章句子注音（列表）]不能为空")
    @Size(max = -1, message = "编码长度不能超过-1")
    @ApiModelProperty("[文章句子注音（列表）")
    private List<String> pinyin;

    public WyContent(@NotBlank(message = "[句子内容]不能为空") @Size(max = -1, message = "编码长度不能超过-1") String content, @NotBlank(message = "[文章句子翻译]不能为空") @Size(max = -1, message = "编码长度不能超过-1") String translation, @NotBlank(message = "[文章句子注音（列表）]不能为空") @Size(max = -1, message = "编码长度不能超过-1") List<String> pinyin) {
        this.content = content;
        this.translation = translation;
        this.pinyin = pinyin;
    }

    public WyContent() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public List<String> getPinyin() {
        return pinyin;
    }

    public void setPinyin(List<String> pinyin) {
        this.pinyin = pinyin;
    }
}
