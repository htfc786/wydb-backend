package com.htfc786.wydb.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqWyContentChange {

    // 文章内容
    @ApiModelProperty(value = "文章内容", required = true)
    private String content;

    public String get() {
        return content;
    }

    public static String to(ReqWyContentChange obj) {
        return obj.content;
    }
}
