package com.ecw.deidtool.service;

import com.ecw.deidtool.interfaces.DeIDFileService;
import com.ecw.deidtool.interfaces.StorageService;
import com.ecw.deidtool.helper.DOMXmlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DeIDFileServiceImpl implements DeIDFileService {

    private final StorageService storageService;
    private final DOMXmlHelper domXmlHelper;


    public DeIDFileServiceImpl(StorageService storageService, DOMXmlHelper domXmlHelper) {
        this.storageService = storageService;
        this.domXmlHelper = domXmlHelper;
    }

    public boolean deidentifyCCDA(MultipartFile file, List<String> categories) {
        boolean isCCDADeID = false;
        Document document = null;

        MultipartFile deIDfile = domXmlHelper.removePII(file, categories);

        if(Objects.isNull(deIDfile))
            return false;

        storageService.store(file,deIDfile);
        isCCDADeID = true;
        log.info("PII removed from C-CDA XML");

        return isCCDADeID;
    }
}
