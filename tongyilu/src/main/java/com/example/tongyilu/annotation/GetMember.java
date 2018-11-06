package com.example.tongyilu.annotation;

import java.lang.annotation.*;

/**
 * 获取用户信息
 *
 * @author xwolf
 **/

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface GetMember {

}