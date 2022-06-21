package org.generation.FinalProject.component;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//This class object is to provide action to "save/store" the uploaded image file from the client to a folder in the server
//This class obj will be called by @PostMapping in the controller
public class FileUploadUtil {

    /*
    This savefile method will be called by the controller under @PostMapping. There are 3 parameters that the method required.
    1)Directory path through relative path of the root folder (WebProject folder)
    2)file name of the image (e.g. T-Shirt_new.jpg)
    3)image object itself
     */

    public static void saveFile(String uploadDir1, String fileName, MultipartFile multipartFile) throws IOException
    {

        //productImages/ is the directory that we set up
        //paths.get - convert a string to the directory path for the upload

        Path uploadPath1 = Paths.get(uploadDir1);

        //get inputStream method is from the multipart class package
        try (InputStream inputStream = multipartFile.getInputStream()) {

            Path filePath1 = uploadPath1.resolve(fileName);
            Files.copy(inputStream, filePath1, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }



}
