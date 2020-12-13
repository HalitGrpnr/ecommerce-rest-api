package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

import static com.ecommerce.api.domain.schema.CommentSchema.*;

@Entity
@Table(name = COMMENTS)
@Data
@EnableJpaAuditing
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @NotEmpty
    @Column(name = CONTENT)
    private String content;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID, nullable = false)
    @JsonBackReference("user-comment")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = STORE_ID)
    @JsonBackReference("store-comment")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PRODUCT_ID)
    @JsonBackReference("product-comment")
    private Product product;


}