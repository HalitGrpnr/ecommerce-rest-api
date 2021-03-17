package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static com.ecommerce.api.domain.schema.FavoriteSchema.*;

@Entity
@Table(name = FAVORITES)
@Getter
@Setter
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID)
    private Long id;

    @OneToOne(mappedBy = FAVORITE)
    @JsonBackReference("user-favorite")
    private User user;

    @ManyToMany
    @JoinTable(
            name = FAVORITE_PRODUCTS,
            joinColumns = @JoinColumn(name = FAVORITE_ID),
            inverseJoinColumns = @JoinColumn(name = PRODUCT_ID))
    private List<Product> products;


}
