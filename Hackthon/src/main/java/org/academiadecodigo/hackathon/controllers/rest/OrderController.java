
package org.academiadecodigo.hackathon.controllers.rest;

import org.academiadecodigo.hackathon.command.OrderDto;
import org.academiadecodigo.hackathon.command.OrderItemDto;
import org.academiadecodigo.hackathon.converters.OrderDtoToOrder;
import org.academiadecodigo.hackathon.converters.OrderToOrderDto;
import org.academiadecodigo.hackathon.persistence.model.User;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;
import org.academiadecodigo.hackathon.persistence.model.product.BusProduct;
import org.academiadecodigo.hackathon.persistence.model.product.GamingProduct;
import org.academiadecodigo.hackathon.persistence.model.product.Product;
import org.academiadecodigo.hackathon.persistence.model.product.ProductType;
import org.academiadecodigo.hackathon.services.OrderItemService;
import org.academiadecodigo.hackathon.services.OrderService;
import org.academiadecodigo.hackathon.services.ProductService;
import org.academiadecodigo.hackathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private OrderToOrderDto orderToOrderDto;
    private OrderDtoToOrder orderDtoToOrder;
    private UserService userService;
    private ProductService productService;
    private OrderItemService orderItemService;

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

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

    @RequestMapping(method = RequestMethod.GET, path = "/{uid}/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer id, @PathVariable Integer uid) {
        try {

            config();
            Order order = orderService.get(id);

            if (order == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            User user = userService.get(uid);

           /* if (user == null || !user.getOrders().contains(order)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }*/

            OrderDto orderDto = orderToOrderDto.convert(order);


            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{uid}/create")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Integer uid, @Valid @RequestBody OrderDto orderDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //orderDto.setUserId(uid);
        Order order = orderDtoToOrder.convert(orderDto);
        order.setUser(userService.get(uid));

        for (OrderItemDto oid : orderDto.getItems()) {
            OrderItem oi = orderDtoToOrder.convertItem(oid);
            oi.setOrder(order);
            order.addOrderItem(oi);
        }

        Order persistedOrder = orderService.save(order);

        orderDto = orderToOrderDto.convert(persistedOrder);

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

   /* @RequestMapping(method = RequestMethod.PUT, value = "/{uid}/edit/{id}")
    public ResponseEntity<OrderDto> editOrder(@Valid @RequestBody OrderDto orderDto,
                                              BindingResult bindingResult, @PathVariable Integer uid,
                                              @PathVariable Integer id) {


        try {
            Order order = orderService.get(id);

            if (order == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            User user = userService.get(uid);

            if (user == null || !user.getOrders().contains(order)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            //order = orderDtoToOrder.convert(orderDto);
            //order.setId(id);

            for (OrderItem oi : order.getItems()){
                order.removeOrderItem(oi);
            }
            for (OrderItemDto oid : orderDto.getItems()) {
                OrderItem oi = orderDtoToOrder.convertItem(oid);
                oi.setOrder(order);
                order.addOrderItem(oi);
                orderItemService.save(oi);
            }

            orderService.save(order);

            OrderDto orderConvertedToDto = orderToOrderDto.convert(order);

            return new ResponseEntity<>(orderConvertedToDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }

    */


    /*@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        try {
            orderService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

     */

    public void config() {
        User user1 = new User();
        user1.setFirstName("Hugo");
        userService.save(user1);

        Product product = new BusProduct();
        product.setName("Prod1");
        product.setPrice(20000);
        //product.setProductType(product.getProductType());
        productService.save(product);

        Product product1 = new GamingProduct();
        product1.setName("Prod2");
        product1.setPrice(3000);
        //product1.setProductType(product1.getProductType());
        productService.save(product1);

        LinkedList<Product> products = new LinkedList<>();
        products.add(product);
        products.add(product1);

        Order order1 = new Order();
        order1.setUser(user1);


        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(2);
        orderItem.setProduct(product);
        orderItem.setOrder(order1);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setQuantity(3);
        orderItem1.setProduct(product1);
        orderItem1.setOrder(order1);

        order1.addOrderItem(orderItem);
        order1.addOrderItem(orderItem1);
        user1.addOrder(order1);
        orderService.save(order1);
    }
}
