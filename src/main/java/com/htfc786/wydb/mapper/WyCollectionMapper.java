package com.htfc786.wydb.mapper;


import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.model.dto.WyCollectionList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WyCollectionMapper {

    // 添加文集
    public void add(WyCollection wyCol);

    // 删除文集
    public int deleteById(int id);

    // 修改文集
    public int updateById(WyCollection wyCol);

    // 通过id查询文集
    public WyCollection getById(int id);

    // 查询全部文集
    public List<WyCollection> getAll();

    // 查询文集列表
    public List<WyCollectionList> getList();
}
