package com.paralelo.demo.service.impl;

import com.paralelo.demo.service.ParalelismoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ParalelismoServiceImpl implements ParalelismoService {
    private RestTemplate restTemplate = new RestTemplate();

    private List<String> urls = List.of(
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
    );


    @Override
    public Object[] multiplasRequisicoesParalelas() {
        Object[] eventos = new Object[0];

        eventos = urls.parallelStream().map(u -> {
            Object o = restTemplate
                    .getForObject( u, Object[].class );
            return o;
        }).toArray();

        return eventos;
    }

}
