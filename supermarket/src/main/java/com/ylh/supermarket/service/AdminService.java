package com.ylh.supermarket.service;

import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yi
* @description 针对表【admin】的数据库操作Service
* @createDate 2024-03-13 10:34:17
*/
public interface AdminService extends IService<Admin> {

    R login(Admin admin);

    R add(Admin admin);
}
