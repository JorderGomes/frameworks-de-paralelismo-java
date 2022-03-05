package com.paralelo.demo.controller;

import com.paralelo.demo.service.ParalelismoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paralelo")
public class ParalelismoController {

    @Autowired
    ParalelismoService paralelismoService;

    @GetMapping("/on")
    public Object[] requisicoesParalelas(){
        Object[] obj;// = "Teste";
        obj = paralelismoService.multiplasRequisicoesParalelas();
        return obj;
    }

}
