package org.example.message;

import java.time.LocalDate;

public class PeopleMessage {
    // 姓名
    private String name;
    // 出生日期
    private LocalDate birthDate;
    // 性别（true表示男，false表示女）
    private boolean sex;
    // 身份证号
    private String identityCardId;
    // 家庭地址
    private String address;
    // 手机号码
    private String phone;
    // 医保ID
    private String healthcareId;
    // 身高（厘米）
    private int height;
    // 体重（公斤）
    private double weight;

    // 构造方法
    public PeopleMessage(String name, LocalDate birthDate, boolean sex,
                         String identityCardId, String address, String phone,
                         String healthcareId, int height, double weight) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.identityCardId = identityCardId;
        this.address = address;
        this.phone = phone;
        this.healthcareId = healthcareId;
        this.height = height;
        this.weight = weight;
    }

    // Getter和Setter方法
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public boolean getSex() { return sex; }
    public void setSex(boolean sex) { this.sex = sex; }

    public String getIdentityCardId() { return identityCardId; }
    public void setIdentityCardId(String identityCardId) { this.identityCardId = identityCardId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getHealthcareId() { return healthcareId; }
    public void setHealthcareId(String healthcareId) { this.healthcareId = healthcareId; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
}
