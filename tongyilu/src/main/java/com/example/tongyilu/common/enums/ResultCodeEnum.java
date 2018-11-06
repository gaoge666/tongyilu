package com.example.tongyilu.common.enums;

/**
 * @author xwolf
 **/
public enum ResultCodeEnum {

    SUCCESS("1000", "成功"),
    SUCCESS_NO_DATA("1001", "无任何数据"),

    PARAM_ERROR("2000", "参数不全或错误"),
    SYSTEM_ERROR("3000", "内部错误"),
    SENSITIVE_WORDS("2001", "您发布的消息包含敏感词,请检查后重新发布!"),
    MESSAGE_ERROO("2002", "发布失败,请重新发布!"),
    MESSAGE_SUCCESS("6000", "发布成功"),
    NO_REPEATABLE_COMMIT("2003", "请勿重复提交"),


    USER_NOT_EXISTS("4000", "用户不存在"),
    USER_OR_PASSWORD_ERROR("4001", "用户名或密码错误"),
    PASSWORD_ERROR("4002", "密码错误"),
    USER_EXISTS("4003", "用户已存在"),
    USER_NOT_LOGIN("4004", "用户未登录"),

    NOTNULL_OPINION("1003", "发布意见不能为空"),
    NOTMEMBER("1004", "用户不存在"),
    CONTENT_ERROR("1005", "发布失败，请注意意见内容"),
    HAVA_REPORT("1013", "您已经举报过了，不能再次举报"),

    REPORT_RELAY_MESSAGE("2004", "已举报，不可重复提交"),
    REPORT_NULL_REASON("2003", "举报理由空值"),
    REPORT_NULL_MESSAGE("2002", "秘密信息错误"),
    REPORT_NULL_MEMBER("2001", "会员信息错误"),
    REPORT_EXCEPTION("2000", "举报异常"),
    MESSAGE_NOT_EXISTS("2005", "消息不存在"),
    MESSAGE_NOT_COLLECT_REPEATABLE("2006", "你已经喜欢过了"),
    MESSAGE_NOT_CANCEL_COLLECT_REPEATABLE("2006", "你已经不喜欢过了"),
    MESSAGE_NOT_PATRONIZE_REPEATABLE("2007", "不能重复点赞"),
    MESSAGE_NOT_CANCEL_PATRONIZE_REPEATABLE("2008", "不能重复取消点赞"),
    MESSAGE_NOT_OPERATION_REPEATABLE("2009", "不能重复操作"),

    NOT_NULL_REPLYREPORT("1009", "回复举报内容不能为空"),
    NOT_EXISTS_REPORT("1010", "举报不存在"),
    REPLYREPORT_ERROR("1011", "回复失败，请注意回复内容"),
    REPLYREPORT_SUCCESS("1012", "回复成功"),
    NOCOMMENT("1006", "请选择评论"),
    NOT_COMMENT_EXISTS("1007", "评论不存在"),
    REPORT_ERROR("1008", "举报失败，请注意举报内容"),
    NOLOGIN("1002", "用户未登录");


    private String code;

    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
