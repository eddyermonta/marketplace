package com.nexsys.marketplace.domain;

import com.nexsys.marketplace.util.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = Constants.PRODUCTO_BDD)
public class Product {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.P_ID)
    private Long pId;
    private String name;
    private double buyPrice;
    @ElementCollection
    private List<String> images;
    @ManyToOne()
    @JoinColumn(name = Constants.CATEGORY_ID)
    private Category category;

}
