package ch.erni.jpa.tutorial.util;

import java.util.Properties;

/**
 * Created by veda on 7/28/2015.
 */
public class ContextUtil {

    public static String getModuleName() {
        Properties properties = System.getProperties();
        String[] path = properties.getProperty("openejb.home").split("\\\\|/");
        return path[path.length - 1];
    }
}
