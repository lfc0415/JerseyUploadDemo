package com.mon.demo.upload.controller;

import com.mon.demo.upload.client.FileUploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author liu fucheng
 */
@RestController
@RequestMapping({"/api/v1/"})
public class FileUploadController {
    @Autowired
    private FileUploadClient fileUploadClient;

    @PostMapping("/upload_file")
    public String uploadFile(@RequestParam("content") MultipartFile file,
            @RequestParam(value = "uploadToken") String uploadToken) throws Exception {
        String tempPath = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
        File tmpFile = new File(tempPath + file.getOriginalFilename());
        file.transferTo(tmpFile);
        return fileUploadClient.uploadFile(tmpFile, uploadToken);
    }
}
