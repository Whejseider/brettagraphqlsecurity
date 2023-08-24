package com.example.brettagraphql2.model.input;

import com.example.brettagraphql2.model.Food;
import lombok.Data;

@Data
public class FoodInput {

    private String name;
    private String description;
    private Float price;
    private String image;

    public Food getFoodEntity(){
        Food food = new Food();
        food.setName(this.name);
        food.setDescription(this.description);
        food.setPrice(this.price);
        food.setImage(this.image);
        return food;
    }
}
