package cn.edu.guet.item.mapper;

import cn.edu.guet.item.domain.ItemStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liwei
* @description 针对表【item_stock(商品库存表)】的数据库操作Mapper
* @createDate 2025-08-18 16:22:39
* @Entity cn.edu.guet.item.domain.ItemStock
*/
@Mapper
public interface ItemStockMapper extends BaseMapper<ItemStock> {


    static ItemStock selectByItemId(Integer itemId) {
        return null;
    }

    ItemStock selectByItemIdForUpdate(Integer itemId);

    void updateByPrimaryKey(ItemStock stock);
    int insert(ItemStock stock);
}




