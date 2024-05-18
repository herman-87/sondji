package com.h87.sondji.api;

import static com.h87.sondji.commons.ExtractCode.EXTRACT_CODE;
import static com.h87.sondji.commons.ExtractCode.EXTRACT_CODE_1;
import com.h87.sondji.service.TagService;
import com.manageUser.model.CreateTagDTO;
import com.manageUser.model.TagDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;
import java.util.UUID;

@WebFluxTest(controllers = TagResources.class)
class TagResourcesTest extends ResourceTest {
    @MockBean
    private TagService tagService;
    @Autowired
    protected WebTestClient webTestClient;

    @Test
    @DisplayName(
            """
                    Given CreateTagDTO is respected
                    When I create a new tag
                    Then I should see that service layer is calling to perform creation"""
    )
    void createTag() {
        CreateTagDTO createTagDTO = new CreateTagDTO()
                .name("any name")
                .description("description");
        UUID expectedId = UUID.randomUUID();

        Mockito.when(tagService.createTag(createTagDTO)).thenReturn(expectedId);

        UUID resultUnderTest = webTestClient
                .post()
                .uri("/tag")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createTagDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UUID.class)
                .returnResult()
                .getResponseBody();

        assertThat(resultUnderTest).isEqualTo(expectedId);
    }

    @Test
    void getTagByIdTest() {
        UUID tagId = UUID.randomUUID();
        TagDTO tagDTO1 = new TagDTO();
        String extractCode = EXTRACT_CODE_1;

        Mockito.when(tagService.getTagById(tagId, extractCode)).thenReturn(tagDTO1);

        TagDTO resultUnderTest = webTestClient
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/tag/{tagId}")
                                .queryParam(EXTRACT_CODE, extractCode)
                                .build(Map.of("tagId", tagId))
                )
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TagDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(resultUnderTest).isEqualTo(tagDTO1);
    }
}