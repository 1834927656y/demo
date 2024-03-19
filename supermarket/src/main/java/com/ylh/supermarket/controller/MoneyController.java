package com.ylh.supermarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Money;
import com.ylh.supermarket.entity.Putstorage;
import com.ylh.supermarket.service.MoneyService;
import com.ylh.supermarket.service.PutstorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@CrossOrigin
@Tag(name="销售接口",description = "金额相关接口:增删改查")
@RestController
public class MoneyController {

    @Autowired
    private MoneyService moneyService;
    @Operation(summary = "销售")
    @PostMapping("/money/reg")
    public R reg(@RequestBody Money money){
        return moneyService.add(money);
    }
    @Operation(summary = "退货")
    @PostMapping ("/money/del")
    public R del(@RequestBody Money money){
        return moneyService.del(money);
    }
    @Operation(summary = "查询所有")
    @GetMapping("/money/queryAll")
    public List<Money> queryAll(){
        QueryWrapper<Money> query = Wrappers.query(Money.class);
        List<Money> money = list(query);
        return money;
    }
}
