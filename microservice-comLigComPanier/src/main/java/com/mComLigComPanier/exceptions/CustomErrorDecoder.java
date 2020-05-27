package com.mComLigComPanier.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String s, Response response) {
        String data = s.split("#",2)[0];
        if (response.status() == 400){
            return new OrderNotFoundException("Requete Incorrecte");
        }
        return defaultErrorDecoder.decode(s,response);
    }
}
