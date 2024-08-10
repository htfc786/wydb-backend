package com.htfc786.wydb.controller;

import com.htfc786.wydb.common.BaseResponse;
import com.htfc786.wydb.common.ResponseUtils;
import com.htfc786.wydb.model.dto.WyContent;
import com.htfc786.wydb.model.request.ReqWyContentAddString;
import com.htfc786.wydb.model.request.ReqWyContentChange;
import com.htfc786.wydb.service.WyArticleService;
import com.htfc786.wydb.service.WyContentService;
import com.htfc786.wydb.utils.SentenceJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "WyContent")
@RestController
public class WyContentController {

    @Autowired
    private WyContentService wyContentService;

    @Autowired
    private WyArticleService wyArticleService;

    @ApiOperation(value = "获取文章字符内容")
    @GetMapping("/collection/{collectionId}/article/{articleId}/content/string")
    public String getString(@PathVariable Long collectionId, @PathVariable Long articleId) {
        // 检查文集、文章是否存在
        if (!wyArticleService.isExist(collectionId, articleId)) {
            return "文章 id:" + articleId + " 不存在！";
        }
        return wyContentService.getContentString(articleId);
    }

    @ApiOperation(value = "获取文章内容")
    @GetMapping("/collection/{collectionId}/article/{articleId}/content")
    public BaseResponse<List<List<WyContent>>> get(@PathVariable Long collectionId, @PathVariable Long articleId) {
        // 检查文集、文章是否存在
        if (!wyArticleService.isExist(collectionId, articleId)) {
            return ResponseUtils.error(404, "文章 id:" + articleId + " 不存在！");
        }
        List<List<WyContent>> content = wyContentService.getContent(articleId);
        return ResponseUtils.success(content);
    }

    @ApiOperation(value = "添加文章字符段落内容")
    @PostMapping("/collection/{collectionId}/article/{articleId}/content")
    public BaseResponse<List<List<WyContent>>> add(@PathVariable Long collectionId, @PathVariable Long articleId,
                                                   @RequestBody ReqWyContentAddString reqWyContentAddString) {
        String content = ReqWyContentAddString.to(reqWyContentAddString);
        // 检查文集、文章是否存在
        if (!wyArticleService.isExist(collectionId, articleId)) {
            return ResponseUtils.error(404, "文章 id:" + articleId + " 不存在！");
        }
        // 添加！写段号
        String msg;
        Integer paragraph = reqWyContentAddString.getParagraph();
        Integer sentence = reqWyContentAddString.getSentence();
        if (paragraph != null && sentence != null) {
            msg = wyContentService.addContent(articleId, content, paragraph, sentence);
        } else if (paragraph != null) {
            msg = wyContentService.addContent(articleId, content, paragraph);
        } else {
            msg = wyContentService.addContent(articleId, content);
        }
        // 添加失败
        if (msg != null) {
            return ResponseUtils.error(400, msg);
        }
        // 添加成功！
        List<List<WyContent>> data = wyContentService.getContent(articleId);
        return ResponseUtils.success(data);
    }

    @ApiOperation(value = "修改文章部分段的内容")
    @PutMapping("/collection/{collectionId}/article/{articleId}/content")
    public BaseResponse<List<List<WyContent>>> change(@PathVariable Long collectionId, @PathVariable Long articleId,
                                                      @RequestBody ReqWyContentChange reqWyContentChange) {
        // 检查文集、文章是否存在
        if (!wyArticleService.isExist(collectionId, articleId)) {
            return ResponseUtils.error(404, "文章 id:" + articleId + " 不存在！");
        }

        // 转换，保存数据库
        String contentJson = reqWyContentChange.get();
        List<List<WyContent>> content;
        try {
            content = SentenceJsonUtil.parseContentJsonString(contentJson);
        } catch (Exception e) {
            return ResponseUtils.error(400, "json数据格式错误！");
        }

        wyContentService.putContent(articleId, content);

        List<List<WyContent>> result = wyContentService.getContent(articleId);
        return ResponseUtils.success(result);
    }

}
