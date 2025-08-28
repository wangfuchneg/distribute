package cn.edu.guet.item.mapper;

import cn.edu.guet.item.domain.ItemDetailDTO;
import cn.edu.guet.item.domain.Promo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liwei
* @description 针对表【promo】的数据库操作Mapper
* @createDate 2025-08-18 16:30:12
* @Entity cn.edu.guet.item.domain.Promo
*/
@Mapper
public interface PromoMapper extends BaseMapper<Promo> {



    List<ItemDetailDTO> showPromos();  // 必须有这个方法
    ItemDetailDTO selectItemDetailByItemId(Integer itemId);
}




