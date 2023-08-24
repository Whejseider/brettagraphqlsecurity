package com.example.brettagraphql2.controller;

import com.example.brettagraphql2.model.Food;
import com.example.brettagraphql2.model.input.FoodInput;
import com.example.brettagraphql2.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class FoodController {
    private final FoodRepository foodRepository;

    @QueryMapping
    public Iterable<Food> foods() {
        return this.foodRepository.findAll();
    }

    @QueryMapping
    public Food foodById(@Argument Long id) {
        return this.foodRepository.findById(id).orElseThrow(null);
    }

    @QueryMapping
    public Food foodByName(@Argument String name) {
        return this.foodRepository.findFoodByName(name);
    }

    @QueryMapping
    public Iterable<Food> foodsByPrice(@Argument Float price) {
        return this.foodRepository.findFoodsByPrice(price);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addFood(@Argument(name = "input") FoodInput foodInput) {
        if (foodByName(foodInput.getName()) == null) {
            this.foodRepository.save(foodInput.getFoodEntity());
            return "Food added succesfull!";
        }

        return "Error. Already exists a food with the same name!";
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean removeFoodById(@Argument(name = "id") Long id) {
        Food elim = this.foodRepository.findById(id).orElseThrow(null);
        this.foodRepository.deleteById(elim.getId());
        return true;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String updateFoodById(
            @Argument(name = "id") Long id,
            @Argument(name = "input") FoodInput foodInput) {
        if (this.foodRepository.existsById(id)) {
            Food updateFood = foodById(id);
            updateFood.setName(foodInput.getName());
            updateFood.setDescription(foodInput.getDescription());
            updateFood.setPrice(foodInput.getPrice());
            updateFood.setImage(foodInput.getImage());
            return "Update success!";
        }
        return "The food does not exists!";
    }
}
