package com.ylh.supermarket.mapper;

import com.ylh.supermarket.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yi
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2024-03-12 14:48:00
* @Entity com.ylh.supermarket.entity.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}




