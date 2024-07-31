package com.htfc786.wydb.service;

import com.htfc786.wydb.entity.WyArticle;
import com.htfc786.wydb.mapper.WyArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WyArticleService")
public class WyArticleService {

    @Autowired
    private WyArticleMapper wyArticleMapper;

    // 根据id和文集获取信息
    public WyArticle get(Long collectionId, Long id) {
        return wyArticleMapper.get(collectionId, id);
    }

    // 根据id获取信息
    public WyArticle getById(Long id) {
        return wyArticleMapper.getById(id);
    }

    // 获取全部文章
    public List<WyArticle> getAll(Long collectionId) {
        return wyArticleMapper.getAll(collectionId);
    }

    // 添加文章
    public void add(WyArticle wyArticle) {
        wyArticleMapper.add(wyArticle);
    }

    // 修改文章
    public int update(Long collectionId, Long id, WyArticle wyArticle) {
        wyArticle.setId(id);
        wyArticle.setCollectionId(collectionId);
        return wyArticleMapper.updateById(wyArticle);
    }

    // 修改文章所在文集
    public int updateCollection(Long oldCollectionId, Long id, Long newCollectionId) {
        return wyArticleMapper.updateCollection(oldCollectionId, id,newCollectionId);
    }

    // 删除文章
    public int delete(Long collectionId, Long id) {
        return wyArticleMapper.deleteById(collectionId, id);
    }

    // 是否存在
    public boolean isExist(Long id) {
        return getById(id) != null;
    }

    public boolean isExist(Long collectionId, Long id) {
        return get(collectionId, id) != null;
    }

    public boolean isExist(WyArticle wyArticle) {
        return getById(wyArticle.getId()) != null;
    }
}
