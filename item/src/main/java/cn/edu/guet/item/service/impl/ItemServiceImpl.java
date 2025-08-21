package cn.edu.guet.item.service.impl;

import cn.edu.guet.item.domain.Item;
import cn.edu.guet.item.domain.ItemDetailDTO;
import cn.edu.guet.item.domain.Promo;
import cn.edu.guet.item.mapper.ItemMapper;
import cn.edu.guet.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liwei
 * @description 针对表【item】的数据库操作Service实现
 */
@Service

public class ItemServiceImpl implements ItemService {

    private ItemMapper itemMapper;

    @Autowired
    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }


    /**
     * 插入商品，返回自增的商品ID
     *
     * @return
     */
    @Override
    public Integer insertItem(Item item) {
        itemMapper.insert(item); // 数据库插入，自增ID自动生成
        return item.getId(); // 获取自增的商品ID
    }

    /**
     * 根据ID查询商品
     */
    @Override
    public Item getItemById(Integer id) {
        return itemMapper.selectById(id);
    }

    @Override
    public List<Promo> showPromos() {
        return List.of();
    }

    @Override
    public String reduceStock(Integer itemId) {
        return "";
    }

    @Override
    public ItemDetailDTO getItemDetailById(Integer itemId) {
        return null;
    }


    /**
     * 更新商品的促销信息
     */
    @Transactional
    @Override
    public void updateItemPromoInfo(Integer itemId, Integer promoId, BigDecimal promoPrice) {
        Item item = getItemById(itemId);
        if (item != null) {
            // 记录促销活动ID
            item.setPromoId(promoId);
            // 可以记录促销价格，方便展示
            item.setPromoPrice(promoPrice);
            // 标记商品参与促销
            item.setInPromo(true);
            itemMapper.updateById(item);
        }
    }




}
