package cn.edu.guet.item.controller;

import cn.edu.guet.item.domain.Item;
import cn.edu.guet.item.domain.ItemDetailDTO;
import cn.edu.guet.item.domain.Promo;
import cn.edu.guet.item.domain.PromoAndItemDTO;
import cn.edu.guet.item.service.PromoPublishService;
import cn.edu.guet.item.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PromoController {

    private PromoService promoService;
    // 新增：注入协调服务
    private PromoPublishService promoPublishService;

    @Autowired
    public void setPromoService(PromoService promoService) {
        this.promoService = promoService;
    }

    // 新增：注入协调服务的setter方法
    @Autowired

    public void setPromoPublishService(PromoPublishService promoPublishService) {
        this.promoPublishService = promoPublishService;
    }

    // 修改：调用协调服务的多表更新方法
    @PostMapping("/publishPromo")
    public String publishPromo(@RequestBody PromoAndItemDTO dto) {
        // 1. 构造商品对象
        Item item = new Item();
        item.setTitle(dto.getTitle());
        item.setPrice(dto.getPrice());
        item.setDescription(dto.getDescription());
        item.setImgUrl(dto.getImgUrl());




        // 2. 构造促销对象
        Promo promo = new Promo();
        promo.setPromoName(dto.getPromoName());
        promo.setStartDate(dto.getStartDate());
        promo.setEndDate(dto.getEndDate());
        promo.setPromoItemPrice(dto.getPromoItemPrice());

        // 3. 调用协调服务，执行“插入商品→关联促销→关联库存”
        promoPublishService.publishPromoWithRelatedTables(item, promo, dto.getStock());

        return "促销发布成功，已同步更新商品和库存信息";
    }

    @GetMapping("showPromos")
    public List<Promo> showPromos() {
        return promoService.showPromos();
    }

    @GetMapping("reduceStock/{itemId}")
    public String reduceStock(@PathVariable("itemId") Integer itemId) {
        return promoService.reduceStock(itemId);
    }


    // 在PromoController中新增
    @GetMapping("/item/detail/{itemId}") // 前端请求的路径
    public ItemDetailDTO getItemDetail(@PathVariable Integer itemId) {
        return promoService.getItemDetailById(itemId);
    }


}
