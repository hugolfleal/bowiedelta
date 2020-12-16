
package org.academiadecodigo.hackathon.controllers.rest;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.converters.OrderDtoToOrder;
import org.academiadecodigo.hackathon.converters.OrderToOrderDto;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.academiadecodigo.hackathon.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user/id/order")
public class OrderController {

    private OrderService orderService;
    private OrderToOrderDto orderToOrderDto;
    private OrderDtoToOrder orderDtoToOrder;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setOrderToOrderDto(OrderToOrderDto orderToOrderDto) {
        this.orderToOrderDto = orderToOrderDto;
    }

    @Autowired
    public void setOrderDtoToOrder(OrderDtoToOrder orderDtoToOrder) {
        this.orderDtoToOrder = orderDtoToOrder;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<OrderDto> getOrderId(@PathVariable Integer id) {
        try {
            Order order = orderService.get(id);
            OrderDto orderDto = orderToOrderDto.convert(order);
            return new ResponseEntity(orderDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderDto.setId(null);
        Order order = orderDtoToOrder.convert(orderDto);
        Order persistedOrder = orderService.save(order);
        orderDto = orderToOrderDto.convert(persistedOrder);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}")
    public ResponseEntity<OrderDto> editOrder(@Valid @RequestBody OrderDto orderDto,
                                              BindingResult bindingResult,
                                              @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Order order = orderService.get(id);
            order.setQuantity(orderDto.getQuantity());
            order.setCreationTime(orderDto.getDate());
            order.setId(orderDto.getId());

            order = orderService.save(order);
            OrderDto orderConvertedToDto = orderToOrderDto.convert(order);
            return new ResponseEntity<>(orderConvertedToDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        try {
            orderService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
