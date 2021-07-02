package com.yhyr.ftpdemo.controller;

import com.yhyr.ftpdemo.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/device")
public class UpdateController {

    @Autowired
    private StorageService storageService;

    @Value("${ftp.host}")
    private String ftpHostName;
    @Value("${ftp.port}")
    private Integer ftpPort;
    @Value("${ftp.user}")
    private String ftpUserName;
    @Value("${ftp.password}")
    private String ftpPassWord;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/update")
    public int uploadToDevice(@RequestParam("files") MultipartFile[] files) throws IOException {

        if (files == null || files.length == 0)
            return 0;

        String remotePath = "/test/data";
        for (MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            logger.info("*******************" + fileName + "*******************");

            boolean resultFlag = storageService.uploadFile(ftpHostName, ftpPort, ftpUserName, ftpPassWord,
                    remotePath, fileName, file.getInputStream());
            if (resultFlag) {
                logger.info("Upload Local File: {} successful", fileName);
            } else {
                logger.info("Upload Local File: {} failed", fileName);
            }
        }
        return 1;
    }
}
