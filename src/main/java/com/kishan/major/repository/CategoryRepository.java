package com.kishan.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.major.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
