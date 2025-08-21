package cn.edu.guet.item.service.impl;

import cn.edu.guet.item.domain.ItemStock;
import cn.edu.guet.item.mapper.ItemStockMapper;
import cn.edu.guet.item.service.ItemStockService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liwei
 * @description 针对表【item_stock】的数据库操作Service实现
 */
@Service
public class ItemStockServiceImpl implements ItemStockService {

    private ItemStockMapper itemStockMapper;

    @Autowired
    public void setItemStockMapper(ItemStockMapper itemStockMapper) {
        this.itemStockMapper = itemStockMapper;
    }

    /**
     * 根据商品ID查询库存
     */
    @Override
    public ItemStock getStockByItemId(Integer itemId) {
        QueryWrapper<ItemStock> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id", itemId);
        return itemStockMapper.selectOne(wrapper);
    }

    @Override
    public boolean reduceStock(Integer itemId, int quantity) {
        return false;
    }

    @Override
    public void reservePromoStock(Integer itemId, int promoStock) {

    }

    /**
     * 关联促销活动与库存（仅记录关联，不扣减库存）
     */
    @Override
    public void relatePromoStock(Integer itemId, Integer promoId) {
        ItemStock itemStock = getStockByItemId(itemId);
        if (itemStock != null) {
            // 假设表中有 promo_id 字段记录关联的促销ID
            itemStock.setPromoId(promoId);
            itemStockMapper.updateById(itemStock);
        }
    }

    @Override
    public void insertStock(ItemStock stock) {
        itemStockMapper.insert(stock); // 必须调用Mapper的插入方法

    }

}
