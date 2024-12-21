package com.example.ecommerce.config.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class UploadProductImageService {

    final String productImagePath = Paths.get("src/main/resources/static/product/images").toAbsolutePath().toString();

    public String uploadImage(MultipartFile file) {

        createProductFolder();

        imageFolderInsideProductFolderFolder();

        String filePath = productImagePath+File.separator+file.getOriginalFilename();

        try {
            Files.copy(file.getInputStream(),Paths.get(filePath));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return filePath;

    }

    public void createProductFolder(){
        try{
            String staticPath = Paths.get("src/main/resources/static").toAbsolutePath().toString();
            String path = staticPath+ File.separator+"product";
            File productFolder  = new File(path);
            if(!Files.exists(Paths.get(path))){
                productFolder.mkdir();
            }
        }catch (Exception e){
            System.out.println("Cannot create productFolder Folder"+e);
        }
    }

    public void imageFolderInsideProductFolderFolder(){
        try{
            String productPaths = Paths.get("src/main/resources/static/product").toAbsolutePath().toString();
            String paths = productPaths+File.separator+"images";
            File imageFolderInsideProductFolder = new File(paths);

            if(!Files.exists(Paths.get(paths))){
                imageFolderInsideProductFolder.mkdir();
            }
        }catch (Exception e){
            System.out.println("Image folder inside achievement Folder is not created"+e);
        }
    }

}
