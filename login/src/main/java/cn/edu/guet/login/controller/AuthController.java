package cn.edu.guet.login.controller;

import cn.edu.guet.login.domain.UserInfo;
import cn.edu.guet.login.domain.UserPassword;
import cn.edu.guet.login.domain.vo.TokenVO;
import cn.edu.guet.login.service.UserInfoService;
import cn.edu.guet.login.service.UserPasswordService;
import cn.edu.guet.login.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserPasswordService userPasswordService;

    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public TokenVO login(
            @RequestParam("telphone") String telphone,
            @RequestParam("password") String password
    ) {
        UserInfo user = userInfoService.findByTelphone(telphone);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        UserPassword userPassword = userPasswordService.findByUserId(user.getId());
        if (userPassword == null || !password.equals(userPassword.getEncrptPassword())) {
            throw new RuntimeException("密码错误");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getId());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());

        return new TokenVO(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public TokenVO refreshToken(@RequestHeader("refresh_token") String refreshToken) {
        if (!jwtUtil.validateRefreshToken(refreshToken)) {
            throw new RuntimeException("refresh_token 无效或已过期");
        }

        Integer userId = jwtUtil.getUserIdFromToken(refreshToken);
        String newAccessToken = jwtUtil.generateAccessToken(userId);

        return new TokenVO(newAccessToken, refreshToken);
    }

    @GetMapping("/test")
    public String test(@RequestHeader("access_token") String accessToken) {
        if (!jwtUtil.validateAccessToken(accessToken)) {
            throw new RuntimeException("access_token 无效或已过期");
        }
        Integer userId = jwtUtil.getUserIdFromToken(accessToken);
        return "登录用户 ID：" + userId + "，访问测试接口成功！";
    }
}