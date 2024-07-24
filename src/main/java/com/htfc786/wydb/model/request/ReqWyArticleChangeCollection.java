package com.htfc786.wydb.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqWyArticleChangeCollection {

    // 新文集
    @ApiModelProperty(value = "新的文集", required = true)
    private Long newCollectionId;

    public ReqWyArticleChangeCollection(Long newCollectionId) {
        this.newCollectionId = newCollectionId;
    }

    public ReqWyArticleChangeCollection() {
    }

    public static Long to(ReqWyArticleChangeCollection obj) {
        return obj.newCollectionId;
    }

    public static int toInt(ReqWyArticleChangeCollection obj) {
        return (int) (long) obj.newCollectionId;
    }
}
