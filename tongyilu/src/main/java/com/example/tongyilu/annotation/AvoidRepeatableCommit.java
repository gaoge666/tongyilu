package com.example.tongyilu.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交
 *
 * @author xwolf
 **/

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AvoidRepeatableCommit {

}
