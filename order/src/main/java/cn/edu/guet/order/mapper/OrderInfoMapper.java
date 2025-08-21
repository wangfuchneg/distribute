package cn.edu.guet.order.mapper;

import cn.edu.guet.order.domain.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liwei
* @description 针对表【order_info(订单表)】的数据库操作Mapper
* @createDate 2025-08-18 16:25:31
* @Entity cn.edu.guet.order.domain.OrderInfo
*/
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}




