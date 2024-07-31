package com.htfc786.wydb.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqWyContentAddString {

    // 文章内容
    @ApiModelProperty(value = "文章内容", required = true)
    private String content;

    public ReqWyContentAddString(String content) {
        this.content = content;
    }

    public ReqWyContentAddString() {
    }

    public static String to(ReqWyContentAddString obj) {
        return obj.content;
    }
}
