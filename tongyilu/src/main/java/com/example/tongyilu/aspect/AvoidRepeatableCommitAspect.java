package com.example.tongyilu.aspect;

import com.alibaba.fastjson.JSON;
import com.example.tongyilu.common.enums.ResultCodeEnum;
import com.example.tongyilu.common.result.ResultUtil;
import com.example.tongyilu.utils.HttpUtils;
import com.example.tongyilu.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xwolf
 **/
@Slf4j
@Component
@Aspect
@Order(1)
public class AvoidRepeatableCommitAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Around("@annotation(com.example.tongyilu.annotation.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ranstr = request.getParameter("ranstr");
        Map<String, Object> parameterMap = HttpUtils.getParameterMap(request);
        String sign = SignUtil.sign(parameterMap);
        log.info("avoid repeatable commit,ranstr={},paramMap={},sign={}", ranstr, JSON.toJSONString(parameterMap), sign);
        if (StringUtils.isBlank(ranstr) || StringUtils.isBlank(sign)) {
            return ResultUtil.error(ResultCodeEnum.PARAM_ERROR);
        }
        String signVal = redisTemplate.opsForValue().get(sign);
        String methodName = point.getSignature().getName();
        if (StringUtils.isBlank(signVal)) {
            //10s内禁止重复提交
            redisTemplate.opsForValue().set(sign, sign, 10, TimeUnit.SECONDS);
            return point.proceed();
        } else {
            log.info("method name={},has been refused to commit.", methodName);
            return ResultUtil.error(ResultCodeEnum.NO_REPEATABLE_COMMIT);
        }
    }
}
