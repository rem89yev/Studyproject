package com.brainacad.studyproject.data.core;

import com.brainacad.studyproject.exception.ApplicationInitializationException;
import com.brainacad.studyproject.util.ApplicationUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.brainacad.studyproject.util.ApplicationConstants.*;

/**
 * Created by Yevhen-PC on 16.11.2016.
 */
public class JdbcConnectionPool {

    private static volatile JdbcConnectionPool instance;

    private volatile int connectionCounter = 0;
    private Properties dbConfig;
    private Connection[] connections;

    private JdbcConnectionPool() throws ApplicationInitializationException {
        dbConfig = ApplicationUtils.readDbConfig();
        int poolSize = Integer.parseInt(getConfigurationProperty(CONNECTION_POOL_SIZE));
        connections = new Connection[poolSize];
        String driverName = getConfigurationProperty(DB_DRIVER);
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new ApplicationInitializationException("Failed to load " + driverName);
        }
        for (int i = 0; i < poolSize; i++) {
            try {
                connections[i] = DriverManager.getConnection(
                        getConfigurationProperty(JDBC_URL),
                        getConfigurationProperty(USER),
                        getConfigurationProperty(PASSWORD));
            } catch (SQLException e) {
                throw new ApplicationInitializationException("Failed to create connection to DB" + e.getMessage());
            }
        }
    }

    public static JdbcConnectionPool getInstance() {
        if (instance == null) {
            synchronized (JdbcConnectionPool.class) {
                if (instance == null) {
                    instance = new JdbcConnectionPool();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        while (connectionCounter == connections.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                //TODO: log at least
            }
        }
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null) {
                connection = connections[i];
                connections[i] = null;
                break;
            }
        }
        connectionCounter++;
        notify();
        return connection;
    }

    synchronized public void releaseConnection(Connection connection) {
        connectionCounter--;
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == null) {
                connections[i] = connection;
                break;
            }
        }
    }

    synchronized public void closeAll() {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null) {
                try {
                    connections[i].close();
                } catch (SQLException e) {
                    //TODO: log at least
                }
            }
        }
        connections = null;
    }

    private String getConfigurationProperty(String key) {
        String value = dbConfig.getProperty(key);
        if (value == null) {
            throw new ApplicationInitializationException("Failed to get " + key);
        } else {
            return value;
        }
    }
}

