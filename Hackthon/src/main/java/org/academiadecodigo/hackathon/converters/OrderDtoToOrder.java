package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToOrder extends AbstractConverter <OrderDto, Order> {

    @Override
    public Order convert(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreationTime(orderDto.getDate());

        return order;
    }
}
