package com.htfc786.wydb.mapper;

import com.htfc786.wydb.entity.WyContent;
import com.htfc786.wydb.model.dto.WyContentOnlyPar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WyContentMapper {

    // 添加内容段
    public void add(WyContent wyContent);

    // 删除内容段
    public int deleteById(Long articleId, Long id);

    // 删除内容段 通过文章id
    public int deleteByArticle(Long articleId);

    // 删除内容段 通过文集id
    public int deleteByCollection(Long collectionId);

    // 修改内容段
    public int updateById(WyContent wyContent);

    // 修改内容段
    public int updateByOrder(Long oldArticleId, Integer oldOrder, WyContent wyContent);

    // 通过id和文集查询内容段
    public WyContent getById(Long articleId, Long id);

    // 查询文章中的全部文段
    public List<WyContent> getByArticle(Long articleId);

    // 查询文章中的顺序号查询文段
    public WyContent getByOrder(Long articleId, Integer order);

    // 查询文章中的段号查询文段
    public List<WyContent> getByParagraph(Long articleId, Integer paragraph);

    // 通过id和文集查询内容段的内容
    public String getContentById(Long articleId, Long id);

    // 查询文章中的全部文段中的内容
    public List<WyContentOnlyPar> getContentByArticle(Long articleId);

    // 查询文章中的顺序号查询文段的内容
    public String getContentByOrder(Long articleId, Integer order);

    // 查询文章中的段号查询文段的内容
    public List<String> getContentByParagraph(Long articleId, Integer paragraph);

    // 查询文章中最大顺序号
    public Integer getMaxOrder(Long articleId);

    // 查询文章中最大段号
    public Integer getMaxParagraph(Long articleId);

    // 查询文段中最大顺序号
    public Integer getMaxParagraphOrder(Long articleId, Integer paragraph);
}
