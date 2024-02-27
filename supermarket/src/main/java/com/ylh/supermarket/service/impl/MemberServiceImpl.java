package com.ylh.supermarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylh.supermarket.entity.Member;
import com.ylh.supermarket.service.MemberService;
import com.ylh.supermarket.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author yi
* @description 针对表【member】的数据库操作Service实现
* @createDate 2024-03-12 14:48:00
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}




