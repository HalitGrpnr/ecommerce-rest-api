package com.ecommerce.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.ecommerce.api.domain.schema.AvatarSchema.*;

@Entity
@Table(name = AVATARS)
@Getter
@Setter
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = IMAGE)
    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PRODUCT_ID)
    @JsonIgnore
    private Product product;
}
