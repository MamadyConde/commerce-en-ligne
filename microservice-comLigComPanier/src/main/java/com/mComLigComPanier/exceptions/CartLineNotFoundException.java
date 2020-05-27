package com.mComLigComPanier.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartLineNotFoundException extends RuntimeException {
    public CartLineNotFoundException(String message){
        super(message);
    }
}
