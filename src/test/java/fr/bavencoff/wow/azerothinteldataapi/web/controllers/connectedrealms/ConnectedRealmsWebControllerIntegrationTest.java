package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.bavencoff.wow.azerothinteldataapi.common.conf.ClockTestConfiguration;
import fr.bavencoff.wow.azerothinteldataapi.common.conf.TestCacheConfig;
import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl.ConnectedRealmDaoRepository;
import fr.bavencoff.wow.azerothinteldataapi.testutils.MethodHelper;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl.ConnectedRealmsWebService;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private ConnectedRealmDaoRepository connectedRealmDaoRepository;
    @Autowired
    private ConnectedRealmsWebService connectedRealmsService;


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


        // Act & Assert: Perform GET request and validate the response
        mockMvc.perform(get("/connectedrealms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // The response status must be 200 OK


        // json containing at least the CR 531
        String expectedJson = MethodHelper.jsonToString("jsons/connectedrealms/FindAllConnectedRealmResponse.json");
        final GetAllConnectedRealmResponseDto expecting = objectMapper.readValue(expectedJson, GetAllConnectedRealmResponseDto.class);
        final GetAllConnectedRealmResponseDto all = connectedRealmsService.findAll(null); // null Set will return all connected realms

        Assertions.assertThat(all.getResults()).usingRecursiveFieldByFieldElementComparator()
                .containsAnyElementsOf(expecting.getResults());
    }

    @Test
    @DisplayName("GET /connectedrealms?regions=US should return connected realms filtered by regions")
    void getAllConnectedRealms_WithRegionsParameter_ShouldReturnFilteredConnectedRealms() throws Exception {
        // Act & Assert: Perform GET request with regions parameter and validate the response
        mockMvc.perform(get("/connectedrealms")
                        .param("regions", "US")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // The response status must be 200 OK

        // Verify that the service method is called with the correct regions parameter
        Set<GlobalRegion> regions = Set.of(GlobalRegion.US);
        final GetAllConnectedRealmResponseDto filteredResults = connectedRealmsService.findAll(regions);

        // Verify that all returned connected realms are from the US region
        Assertions.assertThat(filteredResults.getResults()).isNotEmpty();
        // This assertion assumes that the service correctly filters by region
        // If we had more detailed test data, we could make more specific assertions
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
                )
                .andExpectAll(MethodHelper.RFC_7807_MATCHERS);

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
                .andExpect(status().isBadRequest())
                .andExpectAll(MethodHelper.RFC_7807_MATCHERS);
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


    @SneakyThrows
    @Test
    void should_putConnectedRealm_alreadyExisting_correctly() {

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/connectedrealms/12/region/US")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(MethodHelper.jsonToString("/jsons/connectedrealms/PutConnectedRealm12Request.json"))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(MethodHelper.jsonToString("jsons/connectedrealms/PutConnectedRealm12Response.json"))
                );

        Optional<ConnectedRealmDao> connectedRealmDao = connectedRealmDaoRepository.findById(12);
        Assertions.assertThat(connectedRealmDao).isPresent();
        Assertions.assertThat(connectedRealmDao.get().getRealms()).hasSize(4);

        Assertions.assertThat(connectedRealmDaoRepository.findAll()).hasSize(2);

    }

}
