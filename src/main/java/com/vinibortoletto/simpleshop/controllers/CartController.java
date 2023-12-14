package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.CartRequestDTO;
import com.vinibortoletto.simpleshop.dtos.CartResponseDTO;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "carts")
@RestController
@RequestMapping(value = "/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @Operation(summary = "Add product to cart")
    @PutMapping
    public ResponseEntity<CartResponseDTO> update(
            @RequestBody @Valid CartRequestDTO dto
    ) {
        Cart cart = cartService.update(dto);
        CartResponseDTO response = new CartResponseDTO(cart);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
