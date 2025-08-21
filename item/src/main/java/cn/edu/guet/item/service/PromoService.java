package cn.edu.guet.item.service;

import cn.edu.guet.item.domain.ItemDetailDTO;
import cn.edu.guet.item.domain.Promo;

import java.util.List;

/**
 * @author liwei
 * @description 针对表【promo】的数据库操作Service
 * @createDate 2025-08-18 16:30:12
 */
public interface PromoService {

    void publishPromo(Promo promo);

    List<Promo> showPromos();

    String reduceStock(Integer itemId);

    ItemDetailDTO getItemDetailById(Integer itemId);

}
