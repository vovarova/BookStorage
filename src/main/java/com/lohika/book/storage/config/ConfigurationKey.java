package com.lohika.book.storage.config;

/**
 * @author: vroman
 * This enum describes enumeration keys
 */
public enum ConfigurationKey {
    FILE_STORAGE_URL("fileStorageUrl");
    private final String key;

    ConfigurationKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
