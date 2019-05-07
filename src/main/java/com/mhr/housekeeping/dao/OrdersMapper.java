package com.mhr.housekeeping.dao;
import com.mhr.housekeeping.entity.OrdersDO;
import com.mhr.housekeeping.entity.vo.OrdersVO;
import com.mhr.housekeeping.entity.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by min on 2019/05/04
 */
@Repository
public interface OrdersMapper {

    Integer addOrders(OrdersVO ordersVO);

    Integer updateOrders(OrdersVO ordersVO);

    OrdersDO findDetailOrders(OrdersVO ordersVO);

    List<OrdersVO> listOrders(OrdersVO ordersVO);

    List<OrdersVO> listOrdersPage(OrdersVO ordersVO);

    Integer countOrders(OrdersVO ordersVO);

    Integer deleteOrders(OrdersVO ordersVO);

    List<OrdersVO> listEmployeeOrders(Integer userId,Integer state);

    List<OrdersVO> listEmployerOrders(Integer userId, Integer state);

    Integer countOrdersByEmployeeId(UserVO it);

    Integer countGoodOrders(OrdersVO orderVO);

    List<OrdersVO> getAllOrderByPage(Integer page, Integer size);
}
