package com.h87.sondji.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringJUnitWebConfig
@WebFluxTest(controllers = NoteResources.class)
public abstract class ResourceTest {
    @Autowired
    protected WebTestClient webTestClient;
}
