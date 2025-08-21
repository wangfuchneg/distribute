package cn.edu.guet.order.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "item")
public interface FeignService {

    @GetMapping(value = "/reduceStock/{itemId}")
    String reduceStock(@PathVariable("itemId") Integer itemId);
}