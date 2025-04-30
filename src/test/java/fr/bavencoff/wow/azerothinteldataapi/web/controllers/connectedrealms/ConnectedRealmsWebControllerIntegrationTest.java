package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.bavencoff.wow.azerothinteldataapi.common.conf.ClockTestConfiguration;
import fr.bavencoff.wow.azerothinteldataapi.common.conf.TestCacheConfig;
import fr.bavencoff.wow.azerothinteldataapi.testutils.MethodHelper;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the ConnectedRealmsWebController.
 * <p>
 * These tests verify that the controller correctly interacts with all layers of the application
 * (service, mapper, API service, DAO service, database) to manage connected realm data.
 * <p>
 * The tests use:
 * - H2 in-memory database with test data
 * - The "test" profile for specific test configurations
 * - ClockTestConfiguration for consistent timestamps in responses
 * - Full Spring context to test the entire request-response flow
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({ClockTestConfiguration.class, TestCacheConfig.class})
public class ConnectedRealmsWebControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @SneakyThrows
    @Test
    @DisplayName("POST /connectedrealms should create a new connected realm")
    void should_insertNewConnectedRealm_correctly() {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/connectedrealms")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(MethodHelper.jsonToString("/jsons/connectedrealms/NewConnectedRealm71Us.json"))

                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(MethodHelper.jsonToString("jsons/connectedrealms/NewConnectedRealm71UsResponse.json"))
                );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/connectedrealms/71/region/US")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(MethodHelper.jsonToString("jsons/connectedrealms/GetConnectedRealm71Us.json"))
        );
    }

    @Test
    @DisplayName("GET /connectedrealms should return all connected realms with the correct structure and data")
    void getAllConnectedRealms_ShouldReturnConnectedRealms() throws Exception {
        // Arrange: Load the expected JSON response from the file
        String expectedJson = MethodHelper.jsonToString("jsons/connectedrealms/FindAllConnectedRealmResponse.json");

        // Act & Assert: Perform GET request and validate the response
        mockMvc.perform(get("/connectedrealms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // The response status must be 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // The response content type must be JSON
                .andExpect(content().json(expectedJson, JsonCompareMode.LENIENT)); // Match the response body with the expected JSON
    }

    @SneakyThrows
    @Test
    void should_notInsertNewConnectedRealm_becauseAlreadyExisting() {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/connectedrealms")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(MethodHelper.jsonToString("jsons/connectedrealms/NewConnectedRealm12Us.json"))

                )
                .andExpectAll(
                        status().isConflict(),
                        content().contentType(MediaType.APPLICATION_PROBLEM_JSON),
                        content().json(MethodHelper.jsonToString("jsons/connectedrealms/ConnectedRealm12AlreadyExistsProblem.json"))
                );

    }

    @Test
    @DisplayName("POST /connectedrealms should return 400 when request is invalid")
    void postConnectedRealm_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        // Arrange
        PostConnectedRealmRequestDto requestDto = new PostConnectedRealmRequestDto();
        // Missing required fields

        // Act & Assert
        mockMvc.perform(post("/connectedrealms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("PUT /connectedrealms/{id}/region/{idRegion} should return 400 when request is invalid")
    void putConnectedRealm_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        // Arrange
        PutConnectedRealmRequestDto requestDto = new PutConnectedRealmRequestDto();
        // Missing required fields

        // Act & Assert
        mockMvc.perform(put("/connectedrealms/{id}/region/{idRegion}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

}
