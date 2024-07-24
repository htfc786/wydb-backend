package com.htfc786.wydb.model.response;

import com.htfc786.wydb.entity.WyArticle;
import com.htfc786.wydb.entity.WyCollection;
import lombok.Data;

import java.util.List;

@Data
public class ResWyArticleGetList {
    // 组装json数据 示例：{ collection: {文集信息}, articleList: [文章列表] }

    private WyCollection collection;

    private List<WyArticle> articleList;

    public ResWyArticleGetList() {
    }

    public ResWyArticleGetList(WyCollection collection, List<WyArticle> articleList) {
        this.collection = collection;
        this.articleList = articleList;
    }
}
