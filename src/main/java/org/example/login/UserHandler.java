package org.example.login;

import org.example.data.NullPlaceholder;

import java.net.URISyntaxException;

public class UserHandler {

    /**
     * 用户登录方法
     *
     * @param loginForm {@link LoginForm#getCode()}短信验证码(可选)
     * @param loginForm {@link LoginForm#getPassword()}    密码(4~32位的字母数字下划线)(可选)
     * @param loginForm {@link LoginForm#getPhone()}        用户电话号码
     * @return 登录响应结果
     */

//    public NullPlaceholder login(LoginForm loginForm) throws InterruptedException, URISyntaxException {
//
//        //TODO 返回登录成功结果响应
//        HttpRequest httpRequest = AllergyManagementSystem.REQUEST_BUILDER.buildPostRequest(new URI("user/login"), loginForm);
//        RestfulHttpResponse execute = AllergyManagementSystem.MANAGER.execute(httpRequest);
//        if (execute.success()) {
//            return execute.getData(NullPlaceholder.class);
//        }
//        throw new RuntimeException(execute.getMessage());
//    }

    public NullPlaceholder login(LoginForm loginForm) throws InterruptedException, URISyntaxException {
        // 假装发送请求并返回成功
        return new NullPlaceholder(); // 假数据
    }
    /**
     * 用户注册方法
     *
     * @param registerForm     用户姓名
     * @param registerForm     用户电话号码
     * @param registerForm 密码(4~32位的字母数字下划线)
     * @return 注册响应结果(JSON格式)
     */
//    public NullPlaceholder register(RegisterForm registerForm)  throws InterruptedException, URISyntaxException {
//        //TODO 返回登录成功结果响应
//        HttpRequest httpRequest = AllergyManagementSystem.REQUEST_BUILDER.buildPostRequest(new URI("/user/api/register"), registerForm);
//        RestfulHttpResponse execute = AllergyManagementSystem.MANAGER.execute(httpRequest);
//        if (execute.success()) {
//            return execute.getData(NullPlaceholder.class);
//        }
//        throw new RuntimeException(execute.getMessage());
//    }
}