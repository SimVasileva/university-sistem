package com.university.universityproject.web;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileDownloadController {

    private static final String UPLOAD_DIR = "/home/vas1lb/projects/university-system/university-project/uploads";

    @GetMapping("/files")
    public String listFiles(Model model) {
        List<String> fileNames = new ArrayList<>();
        File[] files = new File(UPLOAD_DIR).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        model.addAttribute("fileNames", fileNames);
        return "download-file";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> handleFileDownload(@PathVariable("fileName") String fileName) {
        try {
            String filePath = UPLOAD_DIR + File.separator + fileName;
            File file = new File(filePath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}

