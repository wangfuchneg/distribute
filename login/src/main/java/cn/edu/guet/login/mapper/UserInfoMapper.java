package cn.edu.guet.login.mapper;

import cn.edu.guet.login.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo findByTelphone(String telphone);
}