package com.example.tongyilu.controller.member;

import com.example.tongyilu.annotation.AvoidRepeatableCommit;
import com.example.tongyilu.annotation.GetMember;
import com.example.tongyilu.common.result.Result;
import com.example.tongyilu.common.result.ResultUtil;
import com.example.tongyilu.entity.Member;
import com.example.tongyilu.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("tyl/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("getmemberinfo")
    //@AvoidRepeatableCommit
    @ResponseBody
    public Result getMemberInfo(@RequestParam(value = "openid") String openid) {
        Member member = memberService.getMemberInfo(openid);
        return ResultUtil.success(member);
    }
}
