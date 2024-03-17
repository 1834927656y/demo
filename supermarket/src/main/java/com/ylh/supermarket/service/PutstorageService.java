package com.ylh.supermarket.service;

import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.ylh.supermarket.entity.Putstorage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author yi
 * @description 针对表【putstorage】的数据库操作Service
 * @createDate 2024-03-14 16:02:14
 */
public interface PutstorageService extends IService<Putstorage> {
    R add(Putstorage putstorage);
}
