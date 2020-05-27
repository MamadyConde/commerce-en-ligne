package com.clientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservice-paiement", url = "localhost:8084")
public interface MicroservicesPaiementProxy {
}
