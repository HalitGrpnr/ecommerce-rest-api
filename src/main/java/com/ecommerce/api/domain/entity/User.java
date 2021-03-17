package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

import static com.ecommerce.api.domain.schema.UserSchema.*;

@Entity
@Table(name = USERS)
@Data
@EnableJpaAuditing
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @NotEmpty
    @Column(name = NAME)
    private String name;
    @NotEmpty
    @Column(name = SURNAME)
    private String surname;

    @Column(name = PASSWORD)
    private String password;
    @NotEmpty
    @Column(name = EMAIL)
    private String email;
    @NotEmpty
    @Column(name = PHONE)
    private String phone;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

    @Column(name = ADDRESS)
    private String address;

    //TODO private Object avatar;

    @Column(name = USER_ROLE)
    private String userRole;

    @Column(name = LOCKED)
    private Boolean locked = false;

    @Column(name = ENABLED)
    private Boolean enabled = true;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = CART_ID, referencedColumnName = ID)
    @JsonManagedReference("user-cart")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = STORE_ID, referencedColumnName = ID)
    @JsonManagedReference("user-store")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = USER)
    @JsonManagedReference("user-comment")
    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = FAVORITE_ID, referencedColumnName = ID)
    @JsonManagedReference("user-favorite")
    private Favorite favorite;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = USER)
    @JsonManagedReference("user-rating")
    private List<Rating> ratings;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = USER)
    @JsonManagedReference("user-order")
    private List<Order> orders;

}
