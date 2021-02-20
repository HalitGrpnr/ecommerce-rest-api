package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static com.ecommerce.api.domain.schema.ProductSchema.*;

@Entity
@Table(name = PRODUCTS)
@Getter
@Setter
@EnableJpaAuditing
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @NotEmpty
    @Column(name = NAME)
    private String name;

    @NotEmpty
    @Column(name = DESCRIPTION)
    private String description;

    @NotNull
    @Column(name = PRICE)
    private double price;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = LAST_MODIFIED_DATE)
    private Date lastModifiedDate;

    @NotNull
    @Column(name = LEAD_TIME)
    private int leadTime;

    @Column(name = AVERAGE_RATE)
    private double averageRate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = PRODUCT)
    @JsonManagedReference("product-avatars")
    private List<Avatar> avatars;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = PRODUCT)
    @JsonManagedReference("product-comment")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = PRODUCT)
    @JsonManagedReference("product-rating")
    private List<Rating> ratings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = STORE_ID, referencedColumnName = ID)
    @JsonBackReference("store-product")
    private Store store;

    @ManyToMany
    @JoinTable(
            name = PRODUCT_CATEGORIES,
            joinColumns = @JoinColumn(name = PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = CATEGORY_ID))
    private List<Category> categories;

}
