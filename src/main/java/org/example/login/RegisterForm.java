package org.example.login;

import lombok.*;

@Data
public class RegisterForm {
    private String name;
    private String password;
    private String phone;

    public RegisterForm(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
