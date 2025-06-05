package org.example.login;

public class LoginForm {
    // 验证码
    private String code;
    // 密码
    private String password;
    // 手机号
    private String phone;

    public LoginForm(String code, String password, String phone) {
        this.code = code;
        this.password = password;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
