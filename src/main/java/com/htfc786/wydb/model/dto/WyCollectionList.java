package com.htfc786.wydb.model.dto;

import com.htfc786.wydb.entity.WyCollection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WyCollectionList {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("文集名称")
    private String name;

    public WyCollectionList(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public WyCollectionList() {
    }

    public static WyCollectionList to(WyCollection wyCollection) {
        return new WyCollectionList(wyCollection.getId(), wyCollection.getName());
    }
}
