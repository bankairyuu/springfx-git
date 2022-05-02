package com.example.springfx.config.navigation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

@Component
public class NavigationEntryStore {

    private final ConfigurableApplicationContext applicationContext;

    private final Map<String, NavigationEntry> navigationEntryMap = new HashMap<>();

    public NavigationEntryStore(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        try {
            fillNavigationEntryMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillNavigationEntryMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<NavigationEntry> navigationEntryList = objectMapper.readValue(applicationContext.getResource("navigation-entries.json").getFile(), new TypeReference<List<NavigationEntry>>() {});

        navigationEntryList.forEach(navigationEntry -> navigationEntryMap.put(navigationEntry.getScreen(), navigationEntry));
    }


    public String getController(String targetScreen) {
        if (navigationEntryMap.containsKey(targetScreen))
            return navigationEntryMap.get(targetScreen).getBeanName();
        throw new MissingResourceException("Controller bean for " + targetScreen + " is not available", getClass().getName(), targetScreen);
    }
}
