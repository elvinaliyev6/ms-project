package az.elvinali.os.api.mapper;

import az.elvinali.os.api.dto.request.ReqOrder;
import az.elvinali.os.api.dto.response.RespOrder;
import az.elvinali.os.api.entity.Order;

public class OrderMapper {

    public static Order mapRequestToEntity(ReqOrder reqOrder){
        return Order.builder()
                .name(reqOrder.getName())
                .price(reqOrder.getPrice())
                .quantity(reqOrder.getQuantity())
                .build();
    }

    public static RespOrder mapEntityToResponse(Order order){
        return RespOrder.builder()
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .name(order.getName())
                .build();
    }

}
