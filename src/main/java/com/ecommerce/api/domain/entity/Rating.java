package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;

import static com.ecommerce.api.domain.schema.RatingSchema.*;

@Entity
@Table(name = RATINGS)
@Data
@EnableJpaAuditing
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = RATE)
    private int rate;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID)
    @JsonBackReference("user-rating")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = STORE_ID)
    @JsonBackReference("store-rating")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PRODUCT_ID)
    @JsonBackReference("product-rating")
    private Product product;

}