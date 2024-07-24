package com.htfc786.wydb.controller;

import com.htfc786.wydb.common.BaseResponse;
import com.htfc786.wydb.common.ResponseUtils;
import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.model.dto.WyCollectionList;
import com.htfc786.wydb.service.WyCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "WyCollection")
@RestController
public class WyCollectionController {

    @Autowired
    private WyCollectionService wyCollectionService;

    @ApiOperation(value = "获取全部文集")
    @GetMapping("/collection")
    public BaseResponse<List<WyCollection>> get() {
        return ResponseUtils.success(wyCollectionService.getAll());
    }

    @ApiOperation(value = "获取文集列表")
    @GetMapping("/collection/list")
    public BaseResponse<List<WyCollectionList>> getList() {
        return ResponseUtils.success(wyCollectionService.getList());
    }

    @ApiOperation(value = "根据id获取文集")
    @GetMapping("/collection/{id}")
    public BaseResponse<WyCollection> getById(@PathVariable int id) {
        WyCollection col = wyCollectionService.getById(id);
        if (col == null) {
            return ResponseUtils.error(404, "id:" + id + " 文集不存在！");
        }
        return ResponseUtils.success(col);
    }

    @ApiOperation(value = "添加文集")
    @PostMapping("/collection")
    public BaseResponse<WyCollection> add(@RequestBody WyCollection wyCollection) {
        wyCollectionService.add(wyCollection);
        return ResponseUtils.success(wyCollection);
    }

    @ApiOperation(value = "修改文集内容")
    @PutMapping("/collection/{id}")
    public BaseResponse<WyCollection> put(@PathVariable int id,
                                          @RequestBody WyCollection wyCollection) {
        if (wyCollectionService.update(id, wyCollection) == 0) {
            return ResponseUtils.error(400, "id:" + id + " 不存在，修改失败！");
        }
        return ResponseUtils.success(wyCollection);
    }

    @ApiOperation(value = "删除文集")
    @DeleteMapping("/collection/{id}")
    public BaseResponse<String> delete(@PathVariable int id) {
        if (wyCollectionService.delete(id) == 0) {
            return ResponseUtils.error(400, "id:" + id + " 不存在，删除失败！");
        }
        return ResponseUtils.success("success");
    }

}
