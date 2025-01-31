package com.ecw.deidtool.controller;

import com.ecw.deidtool.DeidToolApplication;
import com.ecw.deidtool.config.AppProperties;
import com.ecw.deidtool.interfaces.DeIDFileService;
import com.ecw.deidtool.interfaces.StorageService;
import com.ecw.deidtool.storage.StorageException;
import com.ecw.deidtool.storage.StorageProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(controllers = {DeIDAppController.class},
        excludeAutoConfiguration = {SecurityAutoConfiguration.class,
                DeidToolApplication.class,
                AppProperties.class})
@MockBean(classes = {AppProperties.class, StorageProperties.class, DeIDFileService.class})
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class DeIDAppControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private DeIDFileService deIDFileService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private Principal principal;


    @Test
    @DisplayName("[GET] View Homepage: Azure AD [OFF]")
    public void showHomePage_given_whenAzureADOff_thenReturnHomeViewPage() throws Exception {

        //given
        String viewName = "home";

        //when url is launched

        //then return "home" view
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(viewName));
    }

    @Test
    @DisplayName("[GET] View Homepage: SessionID Attribute Exists")
    public void showHomePage_given_whenHomeUrlLaunched_thenCheckSessionIDAttr() throws Exception {

        //given
        String viewName = "home";

        //when url is launched
//        Mockito.when(session.getId()).thenReturn("TestFolder");

        //then check sessionID attribute
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(viewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sessionID"));
    }

    @Test
    @DisplayName("[GET] View Homepage: Check Storage Service call")
    public void showHomePage_given_whenHomeUrlLaunched_thenCheckStorageServiceCall() throws Exception {

        //given

        //when url is launched

        //then check if storage service's createUserDIR method is called
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(result -> {
                    Mockito.verify(storageService, Mockito.times(1)).createUserDIR(anyString());
                });
    }


    @Test
    @DisplayName("[GET] View Homepage: Azure AD [ON]")
    @EnabledIf(expression = "${spring.cloud.azure.active-directory.enabled}", loadContext = true)
    public void showHomePage_given_whenAzureADOn_thenCheckPrincipalName() throws Exception {

        //given

        //when url is launched

        //then check if storage service's createUserDIR method is called
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(result -> {
                    Mockito.verify(storageService, Mockito.times(1)).createUserDIR(anyString());
                });

    }


    @Test
    @DisplayName("[GET] With DeID Files for user")
    public void showDeIDFileForUser_givenDeIDFileExists_whenMethodCall_thenFetchDeIDFileForUser() throws Exception {

        //given
        Path filePath1 = Paths.get("TestFile1.xml");
        Path filePath2 = Paths.get("TestFile2.xml");
        String viewName = "response :: deid-ccda";

        //when file paths need to be loaded
        Mockito.when(storageService.loadAllForUser()).thenReturn(Stream.of(filePath1, filePath2));

        mockMvc.perform(MockMvcRequestBuilders.get("/loadFileForUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(viewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("files"))
                .andExpect(result -> {
                    List<String> files = (List<String>) result.getModelAndView()
                            .getModel()
                            .get("files");
                    assert files != null;
                    assert files.size() == 2;

                    //verify file URLS contain file names
                    assert files.stream().anyMatch(url -> url.contains("TestFile1.xml"));
                    assert files.stream().anyMatch(url -> url.contains("TestFile2.xml"));
                });
    }

    @Test
    @DisplayName("[GET] Without DeID Files for user")
    public void showDeIDFileForUser_givenNoFilesExist_whenMethodCall_thenReturnEmptyList() throws Exception {

        //given
        String viewName = "response :: deid-ccda";

        //when file paths need to be loaded
        Mockito.when(storageService.loadAllForUser()).thenReturn(Stream.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/loadFileForUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(viewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("files"))
                .andExpect(result -> {
                    List<String> files = (List<String>) result.getModelAndView()
                            .getModel()
                            .get("files");
                    assert files != null;
                    assert files.isEmpty();
                });
    }

    @Test
    @DisplayName("Storage Service Error")
    public void showDeIDFileForUser_givenStorageServiceError_whenMethodCall_thenReturnException() throws Exception {
         //given - there is an error in the Storage Service

        // when
        Mockito.when(storageService.loadAllForUser()).thenThrow(new StorageException("Storage service error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/loadFileForUser"))
                .andExpect((MockMvcResultMatchers.status().isInternalServerError()));

    }

}
