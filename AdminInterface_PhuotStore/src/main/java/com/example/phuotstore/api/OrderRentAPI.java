package com.example.phuotstore.api;


import com.example.phuotstore.dto.OrderRentDTO;
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
@RequestMapping(path = "api/v1/orderRents")
public class OrderRentAPI {

    @Autowired
    private OrderRentRepository orderRentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComboRepository comboRepository;

    @GetMapping //read data
    public ResponseEntity<Page<OrderRent>> getAllOrderRents(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.getAllOrderRents(pageable));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByCustomerID(@PathVariable int id, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(orderRentRepository.getOrderRentsByCustomerID(id, pageable));
    }

    @GetMapping("/waiting")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusWaiting(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsWaiting(pageable));
    }

    @GetMapping("/confirmed")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusConfirm(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsConfirmed(pageable));
    }

    @GetMapping("/shipping")
    public ResponseEntity<Page<OrderRent>> getOrderRentByStatusShipping(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsShipping(pageable));
    }

    @GetMapping("/complete")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusComplete(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsComplete(pageable));
    }

    @GetMapping("/cancel")
    public ResponseEntity<Page<OrderRent>> getOrderRentsByStatusCancel(Pageable pageable) {
        return ResponseEntity.ok(orderRentRepository.findPaginateOrderRentsCancelled(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOrderRent(@Valid @RequestBody OrderRentDTO orderRentDTO) {
        if (comboRepository.existsByComboName(orderRentDTO.getOrderRentName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Order Rent Name is already!"));
        }

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(orderRentDTO.getCustomerID());
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        OrderRent orderRent = new OrderRent(orderRentDTO.getOrderRentName(), orderRentDTO.getNote(), orderRentDTO.getStatus(), orderRentDTO.getTotalQuantity(), orderRentDTO.getTotalPrice(), orderRentDTO.getRental(), orderRentDTO.getBookingDate(), orderRentDTO.getRentalStart(), orderRentDTO.getRentalEnd(), orderRentDTO.getFirstName(), orderRentDTO.getLastName(), orderRentDTO.getEmail(), orderRentDTO.getShippingAddress(), orderRentDTO.getPhone(), orderRentDTO.getPaymentType());

        Set<Integer> productID = orderRentDTO.getProduct();
        Set<Integer> comboID = orderRentDTO.getCombo();

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

            orderRent.setCreateAt(new Date());
            orderRent.setProducts(products);
            orderRent.setCustomer(optionalCustomer.get());
            orderRent.setCombos(combos);
            orderRentRepository.save(orderRent);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderRent.getOrderRentID())
                .toUri();
            return ResponseEntity.created(location).body(orderRent);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderRent(@PathVariable int id,
                                             @Valid @RequestBody OrderRentDTO orderRentDTO) {

        Optional<OrderRent> optionalOrderRent = orderRentRepository.findOrderRentByID(id);
        if (!optionalOrderRent.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(orderRentDTO.getCustomerID());
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        OrderRent orderRent = new OrderRent(orderRentDTO.getOrderRentName(), orderRentDTO.getNote(), orderRentDTO.getStatus(), orderRentDTO.getTotalQuantity(), orderRentDTO.getTotalPrice(), orderRentDTO.getRental(), orderRentDTO.getBookingDate(), orderRentDTO.getRentalStart(), orderRentDTO.getRentalEnd(), orderRentDTO.getFirstName(), orderRentDTO.getLastName(), orderRentDTO.getEmail(), orderRentDTO.getShippingAddress(), orderRentDTO.getPhone(), orderRentDTO.getPaymentType());

        Set<Integer> productID = orderRentDTO.getProduct();
        Set<Integer> comboID = orderRentDTO.getCombo();
        Integer userID = orderRentDTO.getCustomerID();

        Set<Product> products = new HashSet<>();
        Set<Combo> combos = new HashSet<>();
        if (userID == null) {
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

            orderRent.setProducts(products);
            orderRent.setCombos(combos);
            orderRent.setUpdateAt(new Date());
            orderRent.setOrderRentID(optionalOrderRent.get().getOrderRentID());
            orderRentRepository.save(orderRent);
            return ResponseEntity.ok(optionalOrderRent.get());
        }
    }
}
