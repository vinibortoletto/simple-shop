package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByName(String name);

    List<Product> findAllByCategories_Id(String id);
}
