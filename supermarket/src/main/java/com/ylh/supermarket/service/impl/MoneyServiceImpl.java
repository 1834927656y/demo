package com.ylh.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.ylh.supermarket.entity.Money;
import com.ylh.supermarket.entity.Putstorage;
import com.ylh.supermarket.service.MoneyService;
import com.ylh.supermarket.mapper.MoneyMapper;
import com.ylh.supermarket.service.PutstorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author yi
* @description 针对表【money】的数据库操作Service实现
* @createDate 2024-03-19 09:11:46
*/
@Service
public class MoneyServiceImpl extends ServiceImpl<MoneyMapper, Money> implements MoneyService {
    @Autowired
    private PutstorageService putstorageService;

    @Override
    public R add(Money money) {
        QueryWrapper<Putstorage> query = Wrappers.query(Putstorage.class);
        query.eq("name", money.getName());
        Putstorage putstorage = putstorageService.getOne(query);
        if (Objects.nonNull(putstorage)) {
            int newSage = putstorage.getSage() - money.getSage();
            int newMoney = Integer.parseInt(putstorage.getPrice()) * money.getSage();
            if (newSage >= 0) {
                putstorage.setSage(newSage);
                putstorageService.updateById(putstorage); // 使用PutstorageService的updateById方法更新数据
                money.setMoney(newMoney);
                money.setType(0);
                save(money);
                return R.ok().setMsg("售出").setData(money); // 返回更新后的Putstorage对象
            } else {
                return R.fail().setMsg("库存不足").setData(money);
            }
        } else {
            return R.fail().setMsg("未找到对应的库存信息");
        }
    }

    @Override
    public R del(Money money){
        QueryWrapper<Money> query = Wrappers.query(Money.class);
        query.eq("id",money.getId());
        Money one = getOne(query);
        if(Objects.nonNull(one) && one.getType()==0){
            one.setType(1);
            System.out.println(one.getMoney());
            update(one,query);
            return R.ok().setMsg("退货成功").setData(one);
        }else {
            return R.fail().setMsg("订单不存在").setData(money);
        }
    }
}




