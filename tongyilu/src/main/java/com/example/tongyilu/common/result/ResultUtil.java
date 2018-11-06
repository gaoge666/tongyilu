package com.example.tongyilu.common.result;


import com.example.tongyilu.common.enums.ResultCodeEnum;

/**
 * result工具类
 *
 * @author xwolf
 **/
public class ResultUtil {

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result success(Object content) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setContent(content);
        return result;
    }


    public static Result success(String code, String message, String content) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(message);
        result.setContent(content);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(ResultCodeEnum.SYSTEM_ERROR.getMessage());
        return result;
    }

    public static Result error(Object content) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(ResultCodeEnum.SYSTEM_ERROR.getMessage());
        result.setContent(content);
        return result;
    }

    public static Result error(String code, String message, String content) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setContent(content);
        return result;
    }

    public static Result error(ResultCodeEnum resultCodeEnum) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }
}

