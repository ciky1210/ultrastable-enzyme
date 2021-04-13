package com.hnlg.enzyme.enums;

/**
 * 异常枚举类
 */
public enum ExceptionEnum {
    //系统级错误
    UNKNOW_ERROR(-1, "未知错误"),
    RPC_ERROR(-2, "远程调用错误"),
    MYSQL_SQL_GRAMMAR_ERROR(-8, "sql语法错误"),
    METHOD_ARGUMENT_TYPE_MISMATCH_ERROR(-10, "方法参数无法转换"),
    CAN_NOT_FIND_USER_FROM_REQ(-11, "未能从请求中获取到用户信息"),
    //常用错误
    ADD_FAIL(1, "新增失败"),
    LOSE_FILED(2, "缺少必须条件"),
    NAME_IS_NULL(3,"名称不能为空"),
    NAME_IS_REPETITION(4,"名称不能重复"),
    PARAM_IS_NOT_ALL(5,"参数校验不全"),
    //token验证相关
    TOKEN_NULL(100, "token不存在"),
    TOKEN_INVALID(101, "token失效"),
    TOKEN_VERIFY_FAIL(102, "token验证失败"),
    VERIFY_PARAM_INCOMPLETE(103, "token验证参数不全"),
    VERIFY_USER_NOT_FOUND(104, "未找到验证用户信息"),
    DECRYPT_USER_INFO_ERROR(105, "解密用户信息失败"),
    //http请求相关
    HTTP_METHOD_NOT_SUPPORT(200, "不支持的请求方法类型"),
    HTTP_BODY_NOT_READABLE(201, "HTTP请求体无法读取"),
    HTTP_PARAMETER_ERROR(202, "HTTP请求参数无法对应"),
    //用户验证
    USER_NOT_FOUND(300, "账户名或者密码错误"),
    USER_IS_DEL(301, "用户已被删除"),
    USER_IS_LOCK(302, "用户已锁定,请联系管理员解锁"),
    USERNAME_OR_PASSWORD_ERROR(303, "用户名或密码错误"),
    USER_IS_NOT_ADMIN(304, "非管理没有权限进行此操作"),
    USER_OLD_PASSWORD_ERROR(305, "请输入正确的密码"),
    USER_OLD_NEW_PASSWORD_ERROR(306, "新密码不能与旧密码相同"),
    USER_NOT_REGISTER(307, "用户尚未注册"),
    //参数验证
    EC_CODE_IS_NOT_NULL(308, "EC编号不能为空"),
    CLASSIFICATION_IS_NOT_NULL(309, "分类不能为空"),
    ENZYME_TYOE_IS_NOT_NULL(310, "类型不能为空"),
    ENZYME_SPECIES_IS_NOT_NULL(311, "所属物种不能为空"),
    ENZYME_MUTATION_TYPE_IS_NOT_NULL(312, "突变类型不能为空"),
    ENZYME_SEQUENCE_IS_NOT_NULL(313, "序列不能为空"),
    ENZYME_BELONG_IS_NOT_NULL(314, "所属途径不能为空"),
    SEQUENCE_IS_NOT_REPEAT(315, "序列号不允许重复"),
    GINUM_IS_NOT_REPEAT(316, "GI号不允许重复");
    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}