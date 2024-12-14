package com.ecw.deidtool.unitTests.controller;

import com.ecw.deidtool.DeidToolApplication;
import com.ecw.deidtool.config.AppProperties;
import com.ecw.deidtool.controller.DeIDAppController;
import com.ecw.deidtool.interfaces.DeIDFileService;
import com.ecw.deidtool.interfaces.StorageService;
import com.ecw.deidtool.storage.StorageProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@WebMvcTest(controllers = {DeIDAppController.class},
        excludeAutoConfiguration = {SecurityAutoConfiguration.class,
                DeidToolApplication.class,
                AppProperties.class})
@MockBean(classes = {AppProperties.class, StorageProperties.class, DeIDFileService.class})

public class DeIDAppControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

//    @InjectMocks
//    private DeIDTextModAppController deIDTextModAppController;

    @MockBean
    private StorageService storageService;

    @MockBean
    private DeIDFileService deIDFileService;

    @Test
    @DisplayName("[GET] HomePage: Azure Principal is NULL")
    public void given_whenGetPrincipalIsNull_thenReturnHomeViewPage() throws Exception {

        //given
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        Model mockModel = mock(Model.class);
        HttpSession mockSession = mock(HttpSession.class);
        String viewName = "home";

        //when url is launched
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getId()).thenReturn("1234");

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(viewName))
                .andExpect(MockMvcResultMatchers.model().attributeExists("sessionID"))
                .andExpect(result -> {
                    assert result.getRequest().getSession().getMaxInactiveInterval() == 7;
//                    assert result.getRequest().getSession().getId() == "1234";
                });
        Mockito.verify(storageService).createUserDIR(Mockito.anyString());


        //then return "home" view

    }
}
