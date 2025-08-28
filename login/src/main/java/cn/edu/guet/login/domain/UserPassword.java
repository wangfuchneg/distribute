package cn.edu.guet.login.domain;

import lombok.Data;

@Data
public class UserPassword {
    private Integer id;
    private String encrptPassword;
    private Integer userId;
}