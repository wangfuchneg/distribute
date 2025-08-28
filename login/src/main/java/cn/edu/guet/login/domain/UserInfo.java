package cn.edu.guet.login.domain;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String telphone;
    private String registerMode;
    private String thirdPartyId;
}