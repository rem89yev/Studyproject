package com.brainacad.studyproject;

import com.brainacad.studyproject.data.core.JdbcConnectionPool;
import com.brainacad.studyproject.data.core.StubDataHolder;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.util.ApplicationUtils;

import java.awt.*;
import java.util.Properties;

import static com.brainacad.studyproject.util.ApplicationConstants.*;


/**
 * Created by Yevhen-PC on 16.11.2016.
 */
public class ApplicationInitializer {
    public static void initialize() {
        Properties properties = ApplicationUtils.readAppConfig();
        String datasource = properties.getProperty(DATASOURCE);
        switch (datasource) {
            case STUB:
                StubDataHolder.createData();
                break;
            case DB:
                JdbcConnectionPool.getInstance(); // just for first time pool creation
                break;
            default:
                StubDataHolder.createData();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.start();
            }
        });
    }
}
