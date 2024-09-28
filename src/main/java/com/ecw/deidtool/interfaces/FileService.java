package com.ecw.deidtool.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public boolean validateFileForCCDA(MultipartFile file);
}
