package com.lohika.book.storage.config;

/**
 * This enum describes enumeration keys.
 * 
 * @author: vroman
 */
public enum ConfigurationKey {
    /** Path to file storage */
    FILE_STORAGE_URL("fileStorageUrl");
    private final String key;

    ConfigurationKey(final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
