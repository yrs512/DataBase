package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 用户登录方法
     * @param code 短信验证码(可选)
     * @param password 密码(4~32位的字母数字下划线)(可选)
     * @param phone 用户电话号码
     * @return 登录响应结果(JSON格式)
     */
    public String login(String code, String password, String phone) {
        // 参数校验
        if (phone == null || phone.trim().isEmpty()) {
            return createErrorResponse(400, "电话号码不能为空");
        }

        // 确保至少提供一种认证方式
        boolean hasPassword = password != null && !password.trim().isEmpty();
        boolean hasCode = code != null && !code.trim().isEmpty();

        if (!hasPassword && !hasCode) {
            return createErrorResponse(400, "密码或验证码必须提供一项");
        }

        // 如果有密码，检查密码格式
        if (hasPassword && !password.matches("^[a-zA-Z0-9_]{4,32}$")) {
            return createErrorResponse(400, "密码格式不正确，必须是4~32位的字母数字下划线");
        }

        // 模拟登录逻辑
        switch (phone) {
            case "13800000000":
                if (hasCode && "123456".equals(code)) {
                    return createSuccessResponse(200, "验证码登录成功");
                } else if (hasPassword && "password123".equals(password)) {
                    return createSuccessResponse(201, "密码登录成功");
                }
                break;
            case "13800001111":
                return createErrorResponse(401, "未授权访问");
            case "13800002222":
                return createErrorResponse(403, "禁止访问");
            case "13800003333":
                return createErrorResponse(404, "用户不存在");
        }

        // 默认登录失败情况
        return createErrorResponse(400, "登录失败，请检查您的凭据");
    }

    /**
     * 创建成功响应
     */
    private String createSuccessResponse(int statusCode, String message) {
        try {
            LoginResponse response = new LoginResponse(statusCode, null, message);
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return fallbackErrorResponse();
        }
    }

    /**
     * 创建错误响应
     */
    private String createErrorResponse(int statusCode, String errorMessage) {
        try {
            LoginResponse response = new LoginResponse(statusCode, null, errorMessage);
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return fallbackErrorResponse();
        }
    }

    /**
     * JSON处理失败时的备用响应
     */
    private String fallbackErrorResponse() {
        return "{\"code\":500,\"msg\":\"内部服务器错误\"}";
    }

    /**
     * 登录响应内部类
     */
    private static class LoginResponse {
        private int code;
        private Object data;
        private String msg;

        public LoginResponse(int code, Object data, String msg) {
            this.code = code;
            this.data = data;
            this.msg = msg;
        }

        // Jackson需要getter方法进行序列化
        public int getCode() {
            return code;
        }

        public Object getData() {
            return data;
        }

        public String getMsg() {
            return msg;
        }
    }
}