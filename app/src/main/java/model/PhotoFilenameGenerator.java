package model;

import java.util.UUID;

public class PhotoFilenameGenerator {

    public static String generatePhotoCode() {
        String uuid = UUID.randomUUID().toString();
        int index = uuid.indexOf("-");
        if(index != -1)
            return uuid.substring(0, index);
        return null;
    }

}
