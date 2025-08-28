package cn.edu.guet.login.service;

import cn.edu.guet.login.domain.UserPassword;

public interface UserPasswordService {
    UserPassword findByUserId(Integer userId);
}