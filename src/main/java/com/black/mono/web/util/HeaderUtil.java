package com.black.mono.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for HTTP headers creation.
 */
@Slf4j
public final class HeaderUtil {

    private static final String APPLICATION = "health";

    private HeaderUtil() {
    }

    /**
     * <p>createAlert.</p>
     *
     * @param message a {@link String} object.
     * @param id      a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createAlert(String message, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION + "-alert", message);
        try {
            headers.add("X-" + APPLICATION + "-params",
                    URLEncoder.encode(id.toString(), StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            // StandardCharsets are supported by every Java implementation so this exception will never happen
        }
        return headers;
    }

    /**
     * <p>createEntityCreationAlert.</p>
     *
     * @param entityName a {@link String} object.
     * @param id         a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityCreationAlert(String entityName, Long id) {
        String message = "A new " + entityName + " is created with identifier " + id;
        return createAlert(message, id);
    }

    /**
     * <p>createEntityUpdateAlert.</p>
     *
     * @param entityName a {@link String} object.
     * @param id         a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityUpdateAlert(String entityName, Long id) {
        String message = "A " + entityName + " is updated with identifier " + id;
        return createAlert(message, id);
    }

    /**
     * <p>createEntityDeletionAlert.</p>
     *
     * @param entityName a {@link String} object.
     * @param id         a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityDeletionAlert(String entityName, Long id) {
        String message = "A " + entityName + " is deleted with identifier " + id;
        return createAlert(message, id);
    }

    /**
     * <p>createFailureAlert.</p>
     *
     * @param entityName     a {@link String} object.
     * @param defaultMessage a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createFailureAlert(String entityName, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION + "-error", defaultMessage);
        headers.add("X-" + APPLICATION + "-params", entityName);
        return headers;
    }
}
