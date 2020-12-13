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

import static com.ecommerce.api.domain.schema.StoreSchema.*;

@Entity
@Table(name = STORES)
@Data
@EnableJpaAuditing
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @NotEmpty
    @Column(name = NAME)
    private String name;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

    //TODO private Object avatar;

    @Column(name = AVERAGE_RATE)
    private double averageRate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = STORE)
    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = STORE)
    @JsonManagedReference
    private List<Rating> ratings;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = STORE)
    @JsonManagedReference
    private List<Product> products;

    @OneToOne(mappedBy = STORE, fetch = FetchType.EAGER)
    @JsonBackReference
    private User user;
}