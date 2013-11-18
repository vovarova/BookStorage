package com.lohika.book.storage.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfiguratorTest {

    @Test
    public void testGetInstance() {
        Configurator instance = Configurator.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void testGetProperty() {
        Configurator instance = Configurator.getInstance();
        ConfigurationKey[] configurationKeys = ConfigurationKey.values();
        for (ConfigurationKey configurationKey : configurationKeys) {
            String property = instance.getProperty(configurationKey);
            assertNotNull(property);
            assertNotSame("", property);
        }
    }

}
