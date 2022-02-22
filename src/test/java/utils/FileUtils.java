package utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {

    public static File bytesToFile(String fileName, byte[] screenshotByte) {
        File file = new File(fileName);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            outputStream.write(screenshotByte);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
