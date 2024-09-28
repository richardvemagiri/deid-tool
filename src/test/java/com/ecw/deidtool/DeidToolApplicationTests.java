package com.ecw.deidtool;

import com.ecw.deidtool.controller.DeIDAppController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DeidToolApplicationTests {

    @Autowired
    DeIDAppController deIDAppController;

    @Test
    void contextLoads() {
        assertThat(deIDAppController).isNotNull();
    }

}
