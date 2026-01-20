package com.comcast.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for reading data from property files.
 * <p>
 *     This class loads `.properties` files and provides method to fetch key-value pairs.
 * </p>
 * @author Siddharth Malviya
 *
 *
 */
public class PropReader{
    private final Properties props;

    /**
     * Constructs object of ProperReader class and loads the property file from the given path
     * @param path the absolute or relative path to the .properties file.
     * @throws IOException if the file cannot be read
     */
    public PropReader(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        props = new Properties();
        props.load(fis);
        fis.close();
    }

    /**
     * Checks whether a property with the given key exists.
     * @param key The name of the property
     * @return {@code true} if the key exists, {@code false} otherwise.
     */
    public boolean containsKey(String key){
        return props.containsKey(key);
    }

    /**
     * Retrieves the value associated with the specified key from the properties.
     * <p>
     * If the key is not found, the provided default value is returned.
     * The returned value is always returned as a string, even if the stored value is of a different type.
     *
     * @param key        the property key whose associated value is to be returned
     * @param defaultVal the default value to return if the key is not found in the properties
     * @return the value associated with the key, or {@code defaultVal} if the key is not found
     */
    public String getOrDefault(String key, String defaultVal) {
        return props.getOrDefault(key, defaultVal).toString();
    }

    /**
     * Retrieves the value associated with the specified key from the properties.
     * <p>
     * If the key is not found, an empty string is returned as the default value.
     *
     * @param key the property key whose associated value is to be returned
     * @return the value associated with the key, or an empty string if the key is not found
     */
    public String getOrDefault(String key) {
        return getOrDefault(key, "");
    }


    /**
     * Retrieves the value associated with the specified key from the properties.
     * <p>
     * If the key is not present, a {@link KeyNotPresentException} is thrown.
     *
     * @param key the property key whose associated value is to be returned
     * @return the value associated with the specified key
     * @throws KeyNotPresentException if the key is not present in the properties
     */
    public String get(String key){
        if (!containsKey(key)){
            throw new KeyNotPresentException(key + " not present in the property file.");
        }
        return props.getProperty(key);
    }

}

/**
 * Exception thrown to indicate that a required key is not present in the properties.
 * <p>
 * This is an unchecked exception, extending {@link RuntimeException}, and can be used
 * to signal missing configuration or required values during runtime.
 */
class KeyNotPresentException extends RuntimeException {
    public KeyNotPresentException(String message) {
        super(message);
    }
}