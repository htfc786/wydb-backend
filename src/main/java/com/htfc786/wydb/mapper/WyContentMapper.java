package com.htfc786.wydb.mapper;

import com.htfc786.wydb.entity.WyArticle;

public interface WyContentMapper {

    // 修改文章
    public int updateContent(WyArticle wyArticle);

    // 获取文章内容
    public String getContentString(Long id);

    // 获取文章内容json
    public String getContentJson(Long id);
}
