package com.htfc786.wydb.controller;

import com.htfc786.wydb.common.BaseResponse;
import com.htfc786.wydb.common.ResponseUtils;
import com.htfc786.wydb.entity.WyContent;
import com.htfc786.wydb.model.request.ReqWyContentAdd;
import com.htfc786.wydb.model.request.ReqWyContentAddString;
import com.htfc786.wydb.service.WyArticleService;
import com.htfc786.wydb.service.WyCollectionService;
import com.htfc786.wydb.service.WyContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "WyContent")
@RestController
public class WyContentController {

    @Autowired
    private WyContentService wyContentService;

    @Autowired
    private WyArticleService wyArticleService;

    @ApiOperation(value = "添加文章字符段落内容")
    @GetMapping("/collection/{collectionId}/article/{articleId}/content/string")
    public String getString(@PathVariable Long collectionId, @PathVariable Long articleId) {

        return wyContentService.getContentByArticle(articleId);
    }

    @ApiOperation(value = "添加文章内容")
    @PostMapping("/collection/{collectionId}/article/{articleId}/content")
    public BaseResponse<WyContent> add(@PathVariable Long collectionId, @PathVariable Long articleId,
                                       @RequestBody ReqWyContentAdd contentAdd) {
        WyContent content = ReqWyContentAdd.to(contentAdd);
        // 检查边界情况
        if ((content.getOrder() != null && content.getOrder() < 1) ||
                (content.getParagraph() != null && content.getParagraph() < 1)) {
            return ResponseUtils.error(400, "参数错误！");
        }
        // 检查文集、文章是否存在
        if (!wyArticleService.isExist(collectionId, articleId)) {
            return ResponseUtils.error(404, "文章 id:" + articleId + " 不存在！");
        }
        content.setCollectionId(collectionId);
        content.setArticleId(articleId);
        // 添加！
//        wyContentService.moveBack(9L, 30, 1);
        wyContentService.add(content);
        return ResponseUtils.success(content);
    }

    @ApiOperation(value = "添加文章字符段落内容")
    @PostMapping("/collection/{collectionId}/article/{articleId}/content/string")
    public BaseResponse<String> addString(@PathVariable Long collectionId, @PathVariable Long articleId,
                                          @RequestBody ReqWyContentAddString reqWyContentAddString) {
        String content = ReqWyContentAddString.to(reqWyContentAddString);
        // 检查文集、文章是否存在
        if (!wyArticleService.isExist(collectionId, articleId)) {
            return ResponseUtils.error(404, "文章 id:" + articleId + " 不存在！");
        }
        // 添加！
        wyContentService.addContent(collectionId, articleId, content);
        return ResponseUtils.success("success!");
    }

}
