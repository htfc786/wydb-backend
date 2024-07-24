package com.htfc786.wydb.mapper;

import com.htfc786.wydb.entity.WyArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WyArticleMapper {

    // 添加文章
    public void add(WyArticle wyArticle);

    // 删除文章
    public int deleteById(Long collectionId, Long id);

    // 通过collectionId删除文章
    public int deleteByCollectionId(Long collectionId);

    // 修改文章
    public int updateById(WyArticle wyCol);

    // 修改文章所在文集
    public int updateCollection(Long oldCollectionId, Long id, Long newCollectionId);

    // 通过id和文集查询文章
    public WyArticle get(Long collectionId, Long id);

    // 通过id查询文章
    public WyArticle getById(Long id);

    // 查询全部文集
    public List<WyArticle> getAll(Long collectionId);
}
