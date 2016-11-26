package com.brainacad.studyproject.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yevhen-PC on 16.11.2016.
 */
public final class ApplicationUtils {

    private ApplicationUtils() {
    }

    public static Properties readAppConfig() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream is = loader.getResourceAsStream("config/application.properties")) {
            properties.load(is);
        } catch (IOException e) {
            //TODO: write to log
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties readDbConfig() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream is = loader.getResourceAsStream("config/db.properties")) {
            properties.load(is);
        } catch (IOException e) {
            //TODO: write to log
            e.printStackTrace();
        }
        return properties;
    }

}

