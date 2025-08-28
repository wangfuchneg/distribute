package cn.edu.guet.login.service.impl;

import cn.edu.guet.login.domain.UserInfo;
import cn.edu.guet.login.mapper.UserInfoMapper;
import cn.edu.guet.login.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findByTelphone(String telphone) {
        return userInfoMapper.findByTelphone(telphone);
    }
}