package com.tas.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static File toFile(MultipartFile multipartFile) {
        try {
            File result = new File(multipartFile.getOriginalFilename());
            result.createNewFile();
            FileOutputStream fos = new FileOutputStream(result);
            fos.write(multipartFile.getBytes());
            fos.close();
            return result;
        } catch (IOException ex) {
            return null;
        }
    }

}
