package cn.edu.guet.item.service;

import cn.edu.guet.item.domain.Item;
import cn.edu.guet.item.domain.ItemStock;
import cn.edu.guet.item.domain.Promo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromoPublishService {

    @Autowired
    private PromoService promoService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemStockService itemStockService;


    /**
     * 完整流程：先插入商品表→获取自增ID→插入促销表→关联库存表
     * @param item 商品信息（用于插入商品表）
     * @param promo 促销信息（需关联商品ID）
     * @param stock 初始库存数量
     */
    @Transactional
    public void publishPromoWithRelatedTables(Item item, Promo promo, Integer stock) {
        // 1. 先插入商品表，获取自增的商品ID
        itemService.insertItem(item);
        Integer itemId = item.getId(); // 数据库自增生成的商品ID

        // 2. 设置促销表与商品的关联，插入促销表
        promo.setItemId(itemId); // 绑定商品ID
        promoService.publishPromo(promo);
        Integer promoId = promo.getId(); // 数据库自增生成的促销ID

        // 3. 初始化库存表，关联商品ID，设置库存数量（对应表中stock字段）
        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(itemId); // 关联商品ID（对应item_stock表的item_id字段）
        itemStock.setStock(stock); // 设置库存数量（对应item_stock表的stock字段）
        itemStockService.insertStock(itemStock); // 插入库存记录


        // 4. （可选）更新商品表关联促销信息
        itemService.updateItemPromoInfo(itemId, promoId, promo.getPromoItemPrice());
    }
}
