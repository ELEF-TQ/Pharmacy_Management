package application;

import java.net.URISyntaxException;

public class FileResourcesUtils { public static java.net.URL getUrlOfFileFromeResource(String fileName) throws URISyntaxException {

    ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
    java.net.URL resource = classLoader.getResource(fileName);
    if(resource == null) {
        throw new IllegalArgumentException("file not found!" + fileName);
    } else {
        return resource;
    }

}
}