package com.ylh.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylh.supermarket.dto.R;

import com.ylh.supermarket.entity.Putstorage;
import com.ylh.supermarket.service.PutstorageService;
import com.ylh.supermarket.mapper.PutstorageMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author yi
 * @description 针对表【putstorage】的数据库操作Service实现
 * @createDate 2024-03-14 16:02:14
 */
@Service
public class PutstorageServiceImpl extends ServiceImpl<PutstorageMapper, Putstorage>
        implements PutstorageService{
    @Override
    public R add(Putstorage putstorage) {
        QueryWrapper<Putstorage> query = Wrappers.query(Putstorage.class);
        query.eq("name",putstorage.getName());
        Putstorage one = getOne(query);
        if (Objects.nonNull(one)){
//            if(!one.getSavetime().equals(putstorage.getSavetime()) ){
//                System.out.println(one.getSavetime());
//                System.out.println(putstorage.getSavetime());
//                save(putstorage);
//                return R.ok().setMsg("添加成功").setData(putstorage);
//            }
//            else {
                int newSage = one.getSage() + putstorage.getSage();
                one.setSage(newSage);
                one.setSavetime(putstorage.getSavetime());
                one.setImg(putstorage.getImg());
                one.setPrice(putstorage.getPrice());
                one.setTime(putstorage.getTime());
                update(one,query);
                return R.ok().setMsg("添加成功").setData(putstorage);
//            }
        }
        else {
            if (save(putstorage)){
                return R.ok().setMsg("添加成功").setData(putstorage);
            }
            return R.fail().setMsg("添加失败");
        }

    }
    @Override
    public R del(Putstorage putstorage){
        QueryWrapper<Putstorage> query = Wrappers.query(Putstorage.class);
        query.eq("name",putstorage.getName());
        Putstorage one = getOne(query);
        if (Objects.nonNull(one)){
            int newSage = one.getSage() - putstorage.getSage();
            if( newSage >0 ){
                one.setSage(newSage);
                one.setSavetime(putstorage.getSavetime());
                one.setImg(putstorage.getImg());
                one.setPrice(putstorage.getPrice());
                one.setTime(putstorage.getTime());
                update(one,query);
                return R.ok().setMsg("添加成功").setData(putstorage);
            }
            else {
                return R.fail().setMsg("库存不足").setData(putstorage);
            }

        }
        return R.fail().setMsg("商品不存在");
    }
}




