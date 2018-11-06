package com.example.tongyilu.service.impl;

import com.example.tongyilu.dao.MemberMapper;
import com.example.tongyilu.entity.Member;
import com.example.tongyilu.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Member getMemberInfo(String openId) {
        Member member = new Member();
        member.setOpenid(openId);
        return memberMapper.selectOne(member);
    }
}
