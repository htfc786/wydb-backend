package com.htfc786.wydb.controller;

import com.htfc786.wydb.common.BaseResponse;
import com.htfc786.wydb.common.ResponseUtils;
import com.htfc786.wydb.entity.WyArticle;
import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.model.dto.WyContent;
import com.htfc786.wydb.model.request.ReqWyArticle;
import com.htfc786.wydb.model.request.ReqWyArticleChangeCollection;
import com.htfc786.wydb.model.response.ResWyArticleGet;
import com.htfc786.wydb.model.response.ResWyArticleGetList;
import com.htfc786.wydb.model.response.ResWyArticleGetListArticle;
import com.htfc786.wydb.service.WyArticleService;
import com.htfc786.wydb.service.WyCollectionService;
import com.htfc786.wydb.service.WyContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "WyArticle")
@RestController
public class WyArticleController {

    @Autowired
    private WyArticleService wyArticleService;

    @Autowired
    private WyCollectionService wyCollectionService;

    @Autowired
    private WyContentService wyContentService;

    @ApiOperation(value = "获取全部文章")
    @GetMapping("/collection/{collectionId}/article")
    public BaseResponse<ResWyArticleGetList> getByCollectionId(@PathVariable Long collectionId) {
        // 组装json数据 示例：{ collection: {文集信息}, articleList: [文章列表] }
        // 查询文集信息
        WyCollection wyCollection = wyCollectionService.getById((int) (long) collectionId);
        if (wyCollection == null) {
            return ResponseUtils.error(404, "id:" + collectionId + " 文集不存在！");
        }
        // 查询文章列表
        List<WyArticle> wyArticleList = wyArticleService.getAll(collectionId);
        List<ResWyArticleGetListArticle> res = ResWyArticleGetListArticle.listTo(wyArticleList);

        ResWyArticleGetList result = new ResWyArticleGetList(wyCollection, res);
        return ResponseUtils.success(result);
    }

    @ApiOperation(value = "根据id获取文章")
    @GetMapping("/collection/{collectionId}/article/{id}")
    public BaseResponse<ResWyArticleGet> getById(@PathVariable Long collectionId, @PathVariable Long id) {
        WyArticle wyArticle = wyArticleService.get(collectionId, id);
        if (wyArticle == null) {
            return ResponseUtils.error(404, "id:" + id + " 的文章不存在！");
        }
        WyCollection wyCollection = wyCollectionService.getById((int) (long) wyArticle.getCollectionId());
        List<List<WyContent>> content = wyContentService.getContent(id);
        ResWyArticleGet result = ResWyArticleGet.to(wyArticle, wyCollection, content);
        return ResponseUtils.success(result);
    }

    @ApiOperation(value = "根据id获取文章（无需collectionId）")
    @GetMapping("/article/{id}")
    public BaseResponse<ResWyArticleGet> getWithNoCollection(@PathVariable Long id) {
        // 未知文集的情况
        WyArticle wyArticle = wyArticleService.getById(id);
        if (wyArticle == null) { //文章不存在
            return ResponseUtils.error(404, "id:" + id + " 的文章不存在！");
        }
        return getById(wyArticle.getCollectionId(), id);
    }

    @ApiOperation(value = "添加文章")
    @PostMapping("/collection/{collectionId}/article")
    public BaseResponse<WyArticle> add(@PathVariable Long collectionId, @RequestBody ReqWyArticle reqWyArticle) {
        WyArticle wyArticle = ReqWyArticle.to(reqWyArticle);
        // 检查文集是否存在
        if (!wyCollectionService.isExist(collectionId)) {
            return ResponseUtils.error(404, "文集 id:" + collectionId + " 不存在！");
        }
        wyArticle.setCollectionId(collectionId);
        // 添加！
        wyArticleService.add(wyArticle);
        return ResponseUtils.success(wyArticle);
    }

    @ApiOperation(value = "修改文章内容")
    @PutMapping("/collection/{collectionId}/article/{id}")
    public BaseResponse<WyArticle> change(@PathVariable Long collectionId, @PathVariable Long id,
                                          @RequestBody ReqWyArticle reqWyArticle) {
        WyArticle wyArticle = ReqWyArticle.to(reqWyArticle);

        if (wyArticleService.update(collectionId, id, wyArticle) == 0) {
            return ResponseUtils.error(400, "id:" + id + " 不存在，修改失败！");
        }
        return ResponseUtils.success(wyArticle);
    }

    @ApiOperation(value = "修改文章所在文集的位置")
    @PostMapping("/collection/{collectionId}/article/{id}/changeCollection")
    public BaseResponse<ResWyArticleGet> changeCollection(@PathVariable Long collectionId, @PathVariable Long id,
                                                          @RequestBody ReqWyArticleChangeCollection reqWyArticle) {
        Long newCollectionId = ReqWyArticleChangeCollection.to(reqWyArticle);
        // 判断新旧collectionId不可相等
        if (collectionId.equals(newCollectionId)) {
            return ResponseUtils.error(400, "新旧文集相同！");
        }
        // 判断新文集
        if (!wyCollectionService.isExist(newCollectionId)) {
            return ResponseUtils.error(400, "新指定的文集 id:" + newCollectionId + " 不存在，修改失败！");
        }
        // 更新collectionId
        if (wyArticleService.updateCollection(collectionId, id, newCollectionId) == 0) {
            return ResponseUtils.error(400, "文章 id:" + id + " 不存在，修改失败！");
        }
        return this.getById(newCollectionId, id);
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("/collection/{collectionId}/article/{id}")
    public BaseResponse<String> deleteArticle(@PathVariable Long collectionId, @PathVariable Long id) {
        if (wyArticleService.delete(collectionId, id) == 0) {
            return ResponseUtils.error(400, "id:" + id + " 不存在，删除失败！");
        }
        return ResponseUtils.success("success");
    }
}
