package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.command.OrderItemDto;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;
import org.academiadecodigo.hackathon.services.OrderItemService;
import org.academiadecodigo.hackathon.services.OrderService;
import org.academiadecodigo.hackathon.services.ProductService;
import org.academiadecodigo.hackathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrderDtoToOrder extends AbstractConverter <OrderDto, Order> {

    private OrderItemService orderItemService;
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order convert(OrderDto orderDto) {

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreationTime(orderDto.getDate());
        //order.setOrderItems(orderItemDtoToOrderItem(orderDto.getItems()));
        order.setUser(userService.get(orderDto.getUserId()));

        return order;
    }

    private List<OrderItem> orderItemDtoToOrderItem(List<OrderItemDto> items) {

        List<OrderItem> dtoItems = new LinkedList<>();

        for (OrderItemDto oid : items){
            dtoItems.add(convertItem(oid));
        }

        return dtoItems;
    }

    public OrderItem convertItem(OrderItemDto oid) {

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(oid.getQuantity());
        orderItem.setProduct(productService.get(oid.getProductId()));
        orderItem.setId(oid.getId());
        //orderItem.setOrder(orderService.get(oid.getOrderId()));

        return orderItem;

    }
}
