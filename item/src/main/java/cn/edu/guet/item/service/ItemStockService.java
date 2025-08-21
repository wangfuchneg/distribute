package cn.edu.guet.item.service;

import cn.edu.guet.item.domain.ItemStock;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
* @author liwei
* @description 针对表【item_stock(商品库存表)】的数据库操作Service
* @createDate 2025-08-18 16:22:39
*/
public interface ItemStockService {

    ItemStock getStockByItemId(Integer itemId);

    @Transactional
    boolean reduceStock(Integer itemId, int quantity);

    @Transactional
    void reservePromoStock(Integer itemId, int promoStock);

    void relatePromoStock(Integer itemId, Integer promoId);

    void insertStock(ItemStock stock);
}
