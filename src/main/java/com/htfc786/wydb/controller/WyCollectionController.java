package com.htfc786.wydb.controller;

import com.htfc786.wydb.common.BaseResponse;
import com.htfc786.wydb.common.ResponseUtils;
import com.htfc786.wydb.entity.WyCollection;
import com.htfc786.wydb.service.WyCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WyCollectionController {

    @Autowired
    private WyCollectionService wyCollectionService;

    @GetMapping("/collection")
    public BaseResponse<List<WyCollection>> get() {
        return ResponseUtils.success(wyCollectionService.getAll());
    }

    @GetMapping("/collection/{id}")
    public BaseResponse<WyCollection> getById(@PathVariable int id) {
        WyCollection col = wyCollectionService.getById(id);
        if (col == null) {
            return ResponseUtils.error(400, "id:" + id + " 文集不存在！");
        }
        return ResponseUtils.success(col);
    }

    @PostMapping("/collection")
    public BaseResponse<WyCollection> add(@RequestBody WyCollection wyCollection) {
        wyCollectionService.add(wyCollection);
        System.out.println(wyCollection);
        return ResponseUtils.success(wyCollection);
    }

    @PutMapping("/collection/{id}")
    public BaseResponse<WyCollection> put(@PathVariable int id,
                                          @RequestBody WyCollection wyCollection) {
        if (wyCollectionService.update(id, wyCollection) == 0) {
            return ResponseUtils.error(400, "id:" + id + " 不存在，修改失败！");
        }
        return ResponseUtils.success(wyCollection);
    }

    @DeleteMapping("/collection/{id}")
    public BaseResponse<String> delete(@PathVariable int id) {
        if (wyCollectionService.delete(id) == 0) {
            return ResponseUtils.error(400, "id:" + id + " 不存在，删除失败！");
        }
        return ResponseUtils.success("success");
    }

}
