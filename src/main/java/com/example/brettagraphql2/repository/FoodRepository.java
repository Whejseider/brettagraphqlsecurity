package com.example.brettagraphql2.repository;

import com.example.brettagraphql2.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findFoodByName(String name);
    Iterable<Food> findFoodsByPrice(Float price);
}
