package com.ylh.supermarket.service;

import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Money;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yi
* @description 针对表【money】的数据库操作Service
* @createDate 2024-03-19 09:11:46
*/
public interface MoneyService extends IService<Money> {
    R add(Money money);

    R del(Money money);
}


