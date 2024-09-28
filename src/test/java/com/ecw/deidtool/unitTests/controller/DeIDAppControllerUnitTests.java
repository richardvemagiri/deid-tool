package com.ecw.deidtool.unitTests.controller;

import com.ecw.deidtool.DeidToolApplication;
import com.ecw.deidtool.config.AppProperties;
import com.ecw.deidtool.controller.DeIDTextModAppController;
import com.ecw.deidtool.interfaces.DeIDFileService;
import com.ecw.deidtool.interfaces.DeIDTextService;
import com.ecw.deidtool.storage.StorageProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {DeIDTextModAppController.class},
        excludeAutoConfiguration = {SecurityAutoConfiguration.class,
                DeidToolApplication.class,
                AppProperties.class})
@MockBean(classes = {AppProperties.class, StorageProperties.class, DeIDFileService.class})

public class DeIDAppControllerUnitTests {

//    @Autowired
//    private MockMvc mockMvc;

//    @InjectMocks
//    private DeIDTextModAppController deIDTextModAppController;

    @MockBean
    private DeIDFileService deIDFileService;

//    @Test
    @DisplayName("[GET] HomePage")
    public void given_when_thenReturnHomeViewPage() throws Exception {


    }
}
