package com.example.MicroservicoFrontEnd.proxys;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient("MICROSERVICO-ANIMAIS")
public interface ProxyMicroservicoAnimais {

    @GetMapping("/nomesAnimais")
    Map<String, String> getNomeAnimais();
}
