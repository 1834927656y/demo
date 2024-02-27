package com.ylh.supermarket.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.ylh.supermarket.dto.R;
import com.ylh.supermarket.entity.Admin;
import com.ylh.supermarket.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@CrossOrigin()
@Tag(name="管理员接口",description = "管理员相关接口:增删改查")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping( "/admin/login")
    public R login(String username,String password){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return adminService.login(admin);
    }
    @PostMapping( "/admin/reg")
    public R reg(@RequestBody Admin admin){
        return adminService.add(admin);
    }
    @PostMapping( "/admin/modify")
    public R modify (@RequestBody Admin admin){
        if (Objects.nonNull(admin.getPassword())){
//            生成一个随机盐
            String salt = RandomUtil.randomString(6);
//        将随机盐与输入的密码结合并加密
            String pwd = SecureUtil.md5(admin.getPassword()+salt);
//        将随机盐与密码加入到admin中
            admin.setSalt(salt);
            admin.setPassword(pwd);
        }
        boolean b = adminService.updateById(admin);
        return b ? R.ok().setMsg("修改成功").setData(admin) : R.fail().setMsg("修改失败");
    }

    @DeleteMapping("/admin/del/{id}")
    public R delById(@PathVariable("id") Integer id){
        Admin admin = adminService.getById(id);
        if(Objects.isNull(admin)){
            return R.fail().setMsg("该管理员不存在");
        }
        return adminService.removeById(id) ? R.ok().setMsg("删除成功") : R.fail().setMsg("删除失败");
    }
}
