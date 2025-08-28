package cn.edu.guet.login.service;

import cn.edu.guet.login.domain.UserInfo;

public interface UserInfoService {
    UserInfo findByTelphone(String telphone);
}