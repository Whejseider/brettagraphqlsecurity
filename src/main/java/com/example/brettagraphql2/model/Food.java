package com.example.brettagraphql2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "food")
public class Food {
    /**
     * type Food {
     * id: ID!
     * name: String!
     * description: String!
     * price: Float!
     * image: String!
     * }
     */

    @Id
    @Column(name = "FOOD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="NAME")
    private String name;

    @Column(name ="DESCRIPTION")
    private String description;

    @Column(name ="PRICE")
    private Float price;

    @Column(name ="IMAGE")
    private String image;

}
