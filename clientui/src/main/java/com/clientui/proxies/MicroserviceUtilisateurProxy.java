package com.clientui.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservice-utilisateur", url = "localhost:8081")
public interface MicroserviceUtilisateurProxy {
}
