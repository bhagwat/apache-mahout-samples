package se.mat.recommendation;

import java.io.File;

public class Utils {
    public static File getFile(String filePath) throws Exception {
        ClassLoader classLoader = Utils.class.getClassLoader();
        return new File(classLoader.getResource(filePath).toURI());
    }
}
