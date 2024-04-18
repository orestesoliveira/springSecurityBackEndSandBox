package com.bechallenge.orestesoliveirajavabe.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlgoliaScheduler {

    private static final String ALGOLIA_API_URL = "https://api.algolia.com";

    private final RestTemplate restTemplate;

    public AlgoliaScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Scheduled(cron = "0 0 * * * *")
    public void fetchDataFromAlgolia() {
        String algoliaData = restTemplate.getForObject(ALGOLIA_API_URL, String.class);

        processAndInsertData(algoliaData);
    }

    private void processAndInsertData(String data) {

        System.out.println("INSERT INTO DATABASE: " + data);
    }
}
