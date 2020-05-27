package com.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        String data = s.split("#",2)[0];
        String dataUrl = s.split("#",2)[1];
        if (response.status() == 404 && dataUrl.equals("addProductInCart(ProductBean,int,int)")) {
            return new NotFoundException("Produit Insuffisant ");
        }
        return defaultErrorDecoder.decode(s,response);
    }
}
