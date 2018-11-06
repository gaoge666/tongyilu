package com.example.tongyilu.aspect;

import com.example.tongyilu.annotation.GetMember;
import com.example.tongyilu.common.enums.ResultCodeEnum;
import com.example.tongyilu.common.result.ResultUtil;
import com.example.tongyilu.entity.Member;
import com.example.tongyilu.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 获取用户信息
 *
 * @author xwolf
 **/
@Slf4j
@Component
@Aspect
@Order(2)
public class GetMemberAspect {

    private static final String LOGIN_URL = "wechat/login";

    @Autowired
    private MemberService memberService;

    @Around("execution ( * com.example.tongyilu.controller..*(..)) && args(com.example.tongyilu.entity.Member,..)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String openid = request.getParameter("openid");
        String uri = request.getRequestURI();
        log.info("get member,openid={},uri={}", openid, uri);
        if (uri.contains(LOGIN_URL)) {
            return point.proceed();
        } else {
            if (StringUtils.isBlank(openid)) {
                return ResultUtil.error(ResultCodeEnum.USER_NOT_LOGIN);
            }
            Member member = memberService.getMemberInfo(openid);
            if (Objects.isNull(member)) {
                return ResultUtil.error(ResultCodeEnum.USER_NOT_EXISTS);
            }
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            Annotation[][] methodAnnotations = method.getParameterAnnotations();
            int length = methodAnnotations.length;
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    if (methodAnnotations[i].length > 0) {
                        Annotation annotation = methodAnnotations[i][0];
                        Object[] args = point.getArgs();
                        if (annotation.annotationType().getName().equals(GetMember.class.getName())) {
                            Member memberArg = (Member) args[i];
                            BeanUtils.copyProperties(member, memberArg);
                        }
                    }
                }
            }
            return point.proceed();
        }
    }
}
