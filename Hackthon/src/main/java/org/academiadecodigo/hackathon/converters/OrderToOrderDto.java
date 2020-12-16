package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDto extends AbstractConverter <Order, OrderDto> {

    @Override
    public OrderDto convert(Order order) {

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getCreationTime());
        orderDto.setQuantity(order.getQuantity());

        return orderDto;
    }
}
