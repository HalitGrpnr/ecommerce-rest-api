package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

import static com.ecommerce.api.domain.schema.OrderSchema.*;

@Entity
@Table(name = ORDERS)
@Data
@EnableJpaAuditing
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID)
    private Long id;

    @NotEmpty
    @Column(name = TOTAL_PRICE)
    private double totalPrice;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID, nullable = false)
    @JsonBackReference
    private User user;

    @ManyToMany
    @JoinTable(
            name = ORDER_PRODUCTS,
            joinColumns = @JoinColumn(name = ORDER_ID),
            inverseJoinColumns = @JoinColumn(name = PRODUCT_ID))
    @JsonManagedReference
    private List<Product> products;

}
