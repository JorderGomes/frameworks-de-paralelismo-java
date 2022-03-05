package com.flux.paralelo.service;

import reactor.core.publisher.Flux;

public interface ParalelismoService {

    public Flux<Object> multiplasRequisicoesParalelas();

}
