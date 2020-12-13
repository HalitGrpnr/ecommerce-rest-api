package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static com.ecommerce.api.domain.schema.CartSchema.*;

@Entity
@Table(name = CARTS)
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID)
    private Long id;

    @OneToOne(mappedBy = CART)
    @JsonBackReference("user-cart")
    private User user;

    @ManyToMany
    @JoinTable(
            name = CART_PRODUCTS,
            joinColumns = @JoinColumn(name = CART_ID),
            inverseJoinColumns = @JoinColumn(name = PRODUCT_ID))
    private List<Product> products;


}
