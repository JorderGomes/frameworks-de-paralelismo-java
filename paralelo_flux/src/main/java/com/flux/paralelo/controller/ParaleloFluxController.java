package com.flux.paralelo.controller;

import com.flux.paralelo.service.ParalelismoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/paralelo_flux")
public class ParaleloFluxController {

    @Autowired
    ParalelismoService paralelismoService;

    @GetMapping("/on")
    public Flux<Object> requisicoesParalelas(){
        return paralelismoService.multiplasRequisicoesParalelas();
    }
    // Flux<Object[]>
}
