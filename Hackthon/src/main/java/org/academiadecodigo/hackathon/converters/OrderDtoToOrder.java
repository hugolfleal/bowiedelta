package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.command.OrderItemDto;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrderDtoToOrder extends AbstractConverter <OrderDto, Order> {

    @Override
    public Order convert(OrderDto orderDto) {

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreationTime(orderDto.getDate());


        order.setOrderItems(orderItemDtoToOrderItem(orderDto.getItems()));


        return order;
    }

    private List<OrderItem> orderItemDtoToOrderItem(List<OrderItemDto> items) {

        List<OrderItem> dtoItems = new LinkedList<>();

        for (OrderItemDto oid : items){
            dtoItems.add(convertItem(oid));
        }

        return dtoItems;
    }

    private OrderItem convertItem(OrderItemDto oid) {

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(oid.getQuantity());
        orderItem.setProductId(oid.getProductId());
        orderItem.setId(oid.getId());


        return orderItem;

    }
}
