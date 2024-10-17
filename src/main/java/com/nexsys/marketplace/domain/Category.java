package com.nexsys.marketplace.domain;

import com.nexsys.marketplace.util.Constants;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = Constants.CATEGORIA_BDD)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.C_ID)
    private Long cId;
    private String title;
}
