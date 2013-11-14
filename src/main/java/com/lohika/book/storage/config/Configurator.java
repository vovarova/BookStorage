package com.lohika.book.storage.config;

import com.lohika.book.storage.utils.Constants;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is Configurator that loads properties from file.
 * 
 * @author: vroman
 */
public final class Configurator {

    private static final Logger LOGGER = Logger.getLogger(Configurator.class);
    private static Configurator instance;
    private final Properties properties = new Properties();

    private Configurator() {
        InputStream resourceAsStream = getClass().getClassLoader()
                .getResourceAsStream(Constants.CONFIGURATION_FILE);
        try {
            LOGGER.info("Load configurations");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Get instance of Configurator
     * 
     * @return {@link Configurator}
     */
    public static Configurator getInstance() {
        if (instance == null) {
            synchronized (Configurator.class) {
                if (instance == null) {
                    instance = new Configurator();
                }
            }
        }
        return instance;
    }

    /**
     * Get property value that was previosly loaded from file
     * 
     * @param configurationKey unique identifier of property
     * @return property value
     */
    public String getProperty(final ConfigurationKey configurationKey) {
        return (String) properties.get(configurationKey.getKey());
    }

}
