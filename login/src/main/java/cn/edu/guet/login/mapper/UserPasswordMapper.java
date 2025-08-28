package cn.edu.guet.login.mapper;

import cn.edu.guet.login.domain.UserPassword;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPasswordMapper {
    UserPassword findByUserId(Integer userId);
}