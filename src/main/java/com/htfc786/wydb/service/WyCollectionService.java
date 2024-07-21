package com.htfc786.wydb.service;

import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.mapper.WyCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WyCollectionService")
public class WyCollectionService {

    @Autowired
    private WyCollectionMapper wyCollectionMapper;

    public WyCollection getById(int id) {
        return wyCollectionMapper.getById(id);
    }

    public List<WyCollection> getAll() {
        return wyCollectionMapper.getAll();
    }

    public void add(WyCollection wyCol) {
        wyCollectionMapper.add(wyCol);
    }

    public int update(int id, WyCollection wyCollection) {
        wyCollection.setId(id);
        return wyCollectionMapper.updateById(wyCollection);
    }

    public int delete(int id) {
        return wyCollectionMapper.deleteById(id);
    }

}
