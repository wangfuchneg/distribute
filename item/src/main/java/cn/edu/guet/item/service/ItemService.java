package cn.edu.guet.item.service;

import cn.edu.guet.item.domain.Item;
import cn.edu.guet.item.domain.ItemDetailDTO;
import cn.edu.guet.item.domain.Promo;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
* @author liwei
* @description 针对表【item(商品表)】的数据库操作Service
* @createDate 2025-08-18 16:22:04
*/
public interface ItemService {

    Item getItemById(Integer id);
    List<Promo> showPromos();
    String reduceStock(Integer itemId);
    // 新增：根据商品ID查询详情（关联多表）
    ItemDetailDTO getItemDetailById(Integer itemId);


    @Transactional
    void updateItemPromoInfo(Integer itemId, Integer promoId, BigDecimal promoPrice);




    Integer insertItem(Item item);
}
