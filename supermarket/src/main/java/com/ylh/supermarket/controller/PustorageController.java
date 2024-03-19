package com.ylh.supermarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.ylh.supermarket.entity.Putstorage;
import com.ylh.supermarket.service.PutstorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@CrossOrigin
@Tag(name="库存接口",description = "库存相关接口:增删改查")
@RestController
public class PustorageController {
    @Autowired
    private PutstorageService putstorageService;
    @Operation(summary = "入库")
    @PostMapping("/pustorage/reg")
    public R reg(@RequestBody Putstorage putstorage){

        return putstorageService.add(putstorage);
    }
    @Operation(summary = "查询所有")
    @GetMapping("/pustorage/queryAll")
    public List<Putstorage> queryAll(){
        QueryWrapper<Putstorage> query = Wrappers.query(Putstorage.class);
        List<Putstorage> putstorages = list(query);
        return putstorages;
    }
    @Operation(summary = "出库")
    @PostMapping("/pustorage/de")
    public R del(@RequestBody Putstorage putstorage){
        return putstorageService.del(putstorage);
    }
    @Operation(summary = "删除")
    @DeleteMapping("/pustorage/del")
    public R delById(Integer id){
        System.out.print(id);
        Putstorage putstorage = putstorageService.getById(id);
        if(Objects.isNull(putstorage)){
            return R.fail().setMsg("该商品不存在");
        }
        return putstorageService.removeById(id) ? R.ok().setMsg("删除成功") : R.fail().setMsg("删除失败");
    }
}
