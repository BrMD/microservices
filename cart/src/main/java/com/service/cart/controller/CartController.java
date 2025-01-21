package com.service.cart.controller;

import com.service.cart.dto.request.CartDtoRequest;
import com.service.cart.dto.response.CartDto;
import com.service.cart.dto.response.PutOrCreateDtoResponse;
import com.service.cart.service.DeleteCartImpl;
import com.service.cart.service.GetCartImpl;
import com.service.cart.service.PutOrCreateImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final GetCartImpl getCartImpl;
    private final PutOrCreateImpl putOrCreateImpl;
    private final DeleteCartImpl deleteCartImpl;

    public CartController(GetCartImpl getCartImpl, PutOrCreateImpl putOrCreateImpl, DeleteCartImpl deleteCartImpl) {
        this.getCartImpl = getCartImpl;
        this.putOrCreateImpl = putOrCreateImpl;
        this.deleteCartImpl = deleteCartImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable String id){
        CartDto cartDto = getCartImpl.getCart(id);
        return new ResponseEntity<>(cartDto, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> editorcreateCart(@PathVariable String id, @Valid @RequestBody CartDtoRequest cartDtoRequest){
        PutOrCreateDtoResponse putOrCreateDtoResponse = putOrCreateImpl.putOrCreate(id, cartDtoRequest);
        return new ResponseEntity<>(putOrCreateDtoResponse.getCartDto(), HttpStatusCode.valueOf(putOrCreateDtoResponse.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteCartImpl.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}
