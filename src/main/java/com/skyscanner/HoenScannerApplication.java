//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skyscanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {
    private final List<SearchResult> searchResults = new ArrayList();

    public HoenScannerApplication() {
    }

    public static void main(String[] args) throws Exception {
        (new HoenScannerApplication()).run(args);
    }

    public String getName() {
        return "hoen-scanner";
    }

    public void initialize(Bootstrap<HoenScannerConfiguration> bootstrap) {
    }

    public void run(HoenScannerConfiguration configuration, Environment environment) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<SearchResult> carResults = Arrays.asList((SearchResult[])mapper.readValue(this.getClass().getClassLoader().getResource("rental_cars.json"), SearchResult[].class));
        List<SearchResult> hotelResults = Arrays.asList((SearchResult[])mapper.readValue(this.getClass().getClassLoader().getResource("hotels.json"), SearchResult[].class));
        List<SearchResult> searchResults = new ArrayList();
        searchResults.addAll(carResults);
        searchResults.addAll(hotelResults);
        SearchResource resource = new SearchResource(searchResults);
        environment.jersey().register(resource);
    }
}
