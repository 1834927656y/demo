package com.ylh.supermarket.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.ylh.supermarket.service.AdminService;
import com.ylh.supermarket.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Objects;

/**
* @author yi
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2024-03-12 14:48:00
*/

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{
    public R login(Admin admin) {
        //select * from admin where username = ?
        QueryWrapper<Admin> query = Wrappers.query(Admin.class);
        query.eq("username",admin.getUsername());
        Admin one = getOne(query);
        if (Objects.isNull(one)){
            return R.fail().setMsg("账号不存在");
        }
        if(one.getStatus() != 0){
            return R.fail().setMsg("账号禁用").setCode(1);
        }
        String pwd = SecureUtil.md5(admin.getPassword()+one.getSalt());
        if(Objects.equals(one.getPassword(),pwd)){
            return  R.ok().setMsg("登录成功").setData(one);
        }
        else {
            System.out.println(pwd);
            System.out.println(admin.getPassword());
            System.out.println(one.getSalt());
            return R.fail().setMsg("密码错误").setCode(2);
        }
    }

    @Override
    public R add(Admin admin) {
        QueryWrapper<Admin> query = Wrappers.query(Admin.class);
        query.eq("username",admin.getUsername());
        Admin one = getOne(query);
        if (Objects.nonNull(one)){
            return R.fail().setMsg("账号已存在");
        }
//        生成一个随机盐
        String salt = RandomUtil.randomString(6);
//        将随机盐与输入的密码结合并加密
        String pwd = SecureUtil.md5(admin.getPassword()+salt);
//        将随机盐与密码加入到admin中
        admin.setSalt(salt);
        admin.setPassword(pwd);
        if(save(admin)){
            return R.ok().setMsg("添加成功").setData(admin);
        }
        return R.fail().setMsg("添加失败");
    }
}




