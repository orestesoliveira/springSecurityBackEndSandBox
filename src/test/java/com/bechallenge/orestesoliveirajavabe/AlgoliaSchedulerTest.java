package com.bechallenge.orestesoliveirajavabe;

import com.bechallenge.orestesoliveirajavabe.scheduler.AlgoliaScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlgoliaSchedulerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AlgoliaScheduler algoliaScheduler;

    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchDataFromAlgolia() {
        String responseData = "mocked response data";
        when(restTemplate.getForObject(AlgoliaScheduler.ALGOLIA_API_URL, String.class)).thenReturn(responseData);

        algoliaScheduler.fetchDataFromAlgolia();

        verify(restTemplate).getForObject(AlgoliaScheduler.ALGOLIA_API_URL, String.class);

    }
}
