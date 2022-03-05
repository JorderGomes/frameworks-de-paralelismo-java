package com.flux.paralelo.service.impl;

import com.flux.paralelo.service.ParalelismoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParalelismoServiceImpl implements ParalelismoService {

    private WebClient webClient = WebClient.create("https://api.github.com");


    private Flux<String> urls = Flux.fromIterable(List.of(
            "https://api.github.com/events",
            "https://api.github.com/repos/torvalds/linux/comments",
            "https://api.github.com/repos/torvalds/linux/commits",
            "https://api.github.com/gitignore/templates",
            "https://api.github.com/repos/levxyca/pandadomalbot/issues",
            "https://api.github.com/events",
            "https://api.github.com/repos/torvalds/linux/comments",
            "https://api.github.com/repos/torvalds/linux/commits",
            "https://api.github.com/gitignore/templates",
            "https://api.github.com/repos/levxyca/pandadomalbot/issues"
    ));



    @Override
    public Flux<Object> multiplasRequisicoesParalelas() {
        Flux<Object> eventosAssincronos;

        eventosAssincronos = urls.parallel(4)
                       .runOn(Schedulers.parallel())
                       .flatMap(
                               url -> {
                                   return webClient
                                           .get()
                                           .uri(url)
                                           .retrieve()
                                           .bodyToFlux(Object.class);
                               }
                       ).sequential();

        return eventosAssincronos;
    }





}
