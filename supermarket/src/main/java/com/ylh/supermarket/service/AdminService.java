package com.ylh.supermarket.service;

import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yi
* @description 针对表【admin】的数据库操作Service
* @createDate 2024-03-12 14:48:00
*/
public interface AdminService extends IService<Admin> {

    R add(Admin admin);

    R login(Admin admin);
}
