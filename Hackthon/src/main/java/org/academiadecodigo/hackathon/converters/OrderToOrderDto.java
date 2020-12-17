package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.command.OrderItemDto;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrderToOrderDto extends AbstractConverter <Order, OrderDto> {

    @Override
    public OrderDto convert(Order order) {

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getCreationTime());
        orderDto.setItems(orderItemToDto(order.getItems()));
        orderDto.setUserId(order.getUser().getId());

        return orderDto;
    }

    private List<OrderItemDto> orderItemToDto(List<OrderItem> items) {

        List<OrderItemDto> dtoItems = new LinkedList<>();

        for (OrderItem oi : items){
            dtoItems.add(convertItem(oi));
        }

        return dtoItems;
    }

    private OrderItemDto convertItem(OrderItem oi) {

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(oi.getQuantity());
        orderItemDto.setProductName(oi.getProduct().getName());
        orderItemDto.setId(oi.getId());


        return orderItemDto;

    }
}
