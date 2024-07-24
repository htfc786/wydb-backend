package com.htfc786.wydb.service;

import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.mapper.WyArticleMapper;
import com.htfc786.wydb.mapper.WyCollectionMapper;
import com.htfc786.wydb.model.dto.WyCollectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("WyCollectionService")
public class WyCollectionService {

    @Autowired
    private WyCollectionMapper wyCollectionMapper;

    @Autowired
    private WyArticleMapper wyArticleMapper;

    public WyCollection getById(int id) {
        return wyCollectionMapper.getById(id);
    }

    public List<WyCollection> getAll() {
        return wyCollectionMapper.getAll();
    }

    public List<WyCollectionList> getList() {
        return wyCollectionMapper.getList();
    }

    public void add(WyCollection wyCol) {
        wyCollectionMapper.add(wyCol);
    }

    public int update(int id, WyCollection wyCollection) {
        wyCollection.setId(id);
        return wyCollectionMapper.updateById(wyCollection);
    }

    @Transactional
    public int delete(int id) {
        // 删除文章
        wyArticleMapper.deleteByCollectionId((Long) (long) id);
        // 删除文集数据
        return wyCollectionMapper.deleteById(id);
    }

    // 是否存在
    public boolean isExist(Long id) {
        return getById((int) (long) id) != null;
    }

    public boolean isExist(WyCollection wyCollection) {
        return getById((int) (long) wyCollection.getId()) != null;
    }
}
