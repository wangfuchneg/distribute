package cn.edu.guet.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1. 配置跨域信息
        CorsConfiguration config = new CorsConfiguration();
        // 允许前端域名（你的前端地址：http://localhost:8085）
        config.addAllowedOrigin("http://localhost:8085");
        // 允许跨域请求携带 Cookie（前端已配置 axios.defaults.withCredentials = true）
        config.setAllowCredentials(true);
        // 允许的请求方法（GET/POST/PUT/DELETE 等）
        config.addAllowedMethod("*");
        // 允许的请求头（如 Content-Type、Authorization 等）
        config.addAllowedHeader("*");
        // 跨域请求有效期（单位：秒，避免频繁预检请求）
        config.setMaxAge(3600L);

        // 2. 配置跨域路径（所有路径都允许跨域）
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // 3. 返回跨域过滤器
        return new CorsFilter(source);
    }
}