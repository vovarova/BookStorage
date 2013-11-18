package com.lohika.book.storage.config;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConfigurationKeyTest {

    @Test
    public void testGetKey() {
        String key = ConfigurationKey.FILE_STORAGE_URL.getKey();
        assertNotNull(key);
        assertEquals(key, "fileStorageUrl");
    }

}
