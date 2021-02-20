package com.ecommerce.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

import static com.ecommerce.api.domain.schema.CategorySchema.*;

@Entity
@Table(name = CATEGORIES)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = PARENT_ID)
    private Long parentId;

    @NotEmpty
    @Column(name = NAME)
    private String name;

    @CreatedDate
    @Column(name = CREATED_DATE)
    private Date createdDate;

}
