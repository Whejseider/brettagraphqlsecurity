package com.example.brettagraphql2.controller;

import com.example.brettagraphql2.model.Beer;
import com.example.brettagraphql2.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class BeerController {

    private final BeerRepository beerRepository;

    @QueryMapping
    public Iterable<Beer> beers() {
        return this.beerRepository.findAll();
    }
}
