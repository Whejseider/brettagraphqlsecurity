package com.example.brettagraphql2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "beer")
public class Beer {
    /**
     * type Beer {
     * id: ID! xxx
     * name: String! xxxx
     * description: String! xxx
     * category: String! xxx
     * ibu: Float! xx
     * alc: Float!  xxx
     * price: Float! xxxx
     * image: String!xxxx
     * }
     */

    @Id
    @Column(name = "BEER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "IBU")
    private Float ibu;

    @Column(name = "ALC")
    private Float alc;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "IMAGE")
    private String image;
}
