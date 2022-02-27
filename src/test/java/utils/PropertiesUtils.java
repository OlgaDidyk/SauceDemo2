package utils;

public class PropertiesUtils {

    public static String get(String key){
        String envVar = System.getenv(key);
        if (envVar==null){
            envVar = System.getProperty(key);
        }
        return envVar;
    }

}
