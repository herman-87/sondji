//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.h87.sondji.common;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringJUnitConfig
public abstract class ResourcesTest {
    protected WebTestClient webTestClient;

    public ResourcesTest() {
    }

    @BeforeEach
    public void initWebTestClient() {
        this.webTestClient = WebTestClient.bindToController(new Object[]{this.getResources()}).build();
    }

    protected abstract Object getResources();
}
