package com.mPaiement.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.List;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        String[] data = s.split("#",2);
        System.out.println(data);
        if (response.status() == 400){
            return new PaymentNotFoundException("Requete Incorrecte");
        } else if (response.status() == 404 && data[0].equals("MicroservicesComLigComPanier")){
            if (data[1].equals("getOneCart(int)")) return new PaymentNotFoundException("Ce panier n'existe pas");
        }
        return defaultErrorDecoder.decode(s,response);
    }
}
