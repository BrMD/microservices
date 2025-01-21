package com.service.cart.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutOrCreateDtoResponse {
    private CartDto cartDto;
    private Integer code;
}
