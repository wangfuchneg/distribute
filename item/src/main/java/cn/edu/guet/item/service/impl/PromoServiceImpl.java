package cn.edu.guet.item.service.impl;

import cn.edu.guet.item.domain.Item;
import cn.edu.guet.item.domain.ItemDetailDTO;
import cn.edu.guet.item.domain.ItemStock;
import cn.edu.guet.item.domain.Promo;
import cn.edu.guet.item.mapper.ItemMapper;
import cn.edu.guet.item.mapper.ItemStockMapper;
import cn.edu.guet.item.mapper.PromoMapper;
import cn.edu.guet.item.service.PromoService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liwei
 * @description 针对表【promo】的数据库操作Service实现
 * @createDate 2025-08-18 16:30:12
 */
@Service
public class PromoServiceImpl implements PromoService {

    private PromoMapper promoMapper;
    @Autowired
    private ItemStockMapper itemStockMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    public void setItemStockMapper(ItemStockMapper itemStockMapper) {
        this.itemStockMapper = itemStockMapper;
    }

    @Autowired
    public void setPromoMapper(PromoMapper promoMapper) {
        this.promoMapper = promoMapper;
    }

    @Override
    public void publishPromo(Promo promo) {
        promoMapper.insert(promo);
    }

    @Override
    public List<Promo> showPromos() {
        return promoMapper.selectList(null);
    }

    @Override
    @Transactional
    public String reduceStock(Integer itemId) {
        QueryWrapper<ItemStock> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id", itemId);
        ItemStock itemStock = itemStockMapper.selectOne(wrapper);
        if (itemStock != null) {
            itemStock.setStock(itemStock.getStock() - 1);
            itemStockMapper.updateById(itemStock);
        }
        return "success";

    }

    @Override
    public ItemDetailDTO getItemDetailById(Integer itemId) {
        // 直接调用多表关联查询，返回 ItemDetailDTO（无需手动组装）
        ItemDetailDTO dto = promoMapper.selectItemDetailByItemId(itemId);
        return dto;


    }
}



