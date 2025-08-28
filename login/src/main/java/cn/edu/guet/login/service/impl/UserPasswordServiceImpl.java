package cn.edu.guet.login.service.impl;

import cn.edu.guet.login.domain.UserPassword;
import cn.edu.guet.login.mapper.UserPasswordMapper;
import cn.edu.guet.login.service.UserPasswordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class UserPasswordServiceImpl implements UserPasswordService {

    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Override
    public UserPassword findByUserId(Integer userId) {
        return userPasswordMapper.findByUserId(userId);
    }
}