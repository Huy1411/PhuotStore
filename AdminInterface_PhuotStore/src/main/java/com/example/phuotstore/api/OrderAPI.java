package com.example.phuotstore.api;

import com.example.phuotstore.dto.OrderDTO;
import com.example.phuotstore.model.*;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderAPI {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComboRepository comboRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.getAllOrders(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable int id) {
        Optional<Order> optionalOrder = orderRepository.findOrderByID(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalOrder.get());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Page<Order>> getOrdersByCustomerID(@PathVariable int id, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(orderRepository.getOrdersByCustomerID(id, pageable));
    }

    @GetMapping("waiting")
    public ResponseEntity<Page<Order>> getOrdersByStatusWaiting(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findPaginateOrderWaiting(pageable));
    }

    @GetMapping("confirm")
    public ResponseEntity<Page<Order>> getOrdersByStatusConfirm(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findPaginateOrderConfirmed(pageable));
    }

    @GetMapping("shipping")
    public ResponseEntity<Page<Order>> getOrdersByStatusShipping(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findPaginateOrderShipping(pageable));
    }

    @GetMapping("/complete")
    public ResponseEntity<Page<Order>> getOrdersByStatusComplete(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findPaginateOrderComplete(pageable));
    }

    @GetMapping("cancel")
    public ResponseEntity<Page<Order>> getOrdersByStatusCancel(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findPaginateOrderCancelled(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        if (comboRepository.existsByComboName(orderDTO.getOrderName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Order Name is already!"));
        }

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(orderDTO.getCustomerID());
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Order order = new Order(orderDTO.getOrderName(), orderDTO.getNote(), orderDTO.getStatus(), orderDTO.getTotalQuantity(), orderDTO.getTotalPrice(), orderDTO.getFirstName(), orderDTO.getLastName(), orderDTO.getEmail(), orderDTO.getShippingAddress(), orderDTO.getPhone(), orderDTO.getPaymentType());

        Set<Integer> productID = orderDTO.getProduct();
        Set<Integer> comboID = orderDTO.getCombo();

        Set<Product> products = new HashSet<>();
        Set<Combo> combos = new HashSet<>();
        if (productID == null && comboID == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            productID.forEach((product) -> {
                Product productSaved = productRepository.findByID(product);
                products.add(productSaved);
            });
            comboID.forEach((combo) -> {
                Combo comboSaved = comboRepository.findByID(combo);
                combos.add(comboSaved);
            });

            order.setCreateAt(new Date());
            order.setProducts(products);
            order.setCustomer(optionalCustomer.get());
            order.setCombos(combos);
            orderRepository.save(order);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrderID())
                .toUri();
            return ResponseEntity.created(location).body(order);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id,
                                         @Valid @RequestBody OrderDTO orderDTO) {

        Optional<Order> optionalOrder = orderRepository.findOrderByID(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(orderDTO.getCustomerID());
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Order order = new Order(orderDTO.getOrderName(), orderDTO.getNote(), orderDTO.getStatus(), orderDTO.getTotalQuantity(), orderDTO.getTotalPrice(), orderDTO.getFirstName(), orderDTO.getLastName(), orderDTO.getEmail(), orderDTO.getShippingAddress(), orderDTO.getPhone(), orderDTO.getPaymentType());

        Set<Integer> productID = orderDTO.getProduct();
        Set<Integer> comboID = orderDTO.getCombo();
        Integer customerID = orderDTO.getCustomerID();

        Set<Product> products = new HashSet<>();
        Set<Combo> combos = new HashSet<>();
        if (customerID == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            productID.forEach((product) -> {
                Product productSaved = productRepository.findByID(product);
                products.add(productSaved);
            });

            comboID.forEach((combo) -> {
                Combo comboSaved = comboRepository.findByID(combo);
                combos.add(comboSaved);
            });

            order.setProducts(products);
            order.setCombos(combos);
            order.setCreateAt(optionalOrder.get().getCreateAt());
            order.setUpdateAt(new Date());
            order.setOrderID(optionalOrder.get().getOrderID());
            order.setCustomer(optionalCustomer.get());
            orderRepository.save(order);
            return ResponseEntity.ok(optionalOrder.get());
        }
    }
}
