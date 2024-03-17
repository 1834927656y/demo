package com.ylh.supermarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Putstorage;
import com.ylh.supermarket.service.PutstorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@CrossOrigin
@Tag(name="库存接口",description = "库存相关接口:增删改查")
@RestController
public class PustorageController {
    @Autowired
    private PutstorageService putstorageService;

    @PostMapping("/pustorage/reg")
    public R reg(@RequestBody Putstorage putstorage){

        return putstorageService.add(putstorage);
    }

    @GetMapping("/pustorage/queryAll")
    public List<Putstorage> queryAll(){
        QueryWrapper<Putstorage> query = Wrappers.query(Putstorage.class);
        List<Putstorage> putstorages = list(query);
        return putstorages;
    }
}
