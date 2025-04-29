package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions;

import fr.bavencoff.wow.azerothinteldataapi.common.conf.ClockTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the RegionWebController.
 * <p>
 * These tests verify that the controller correctly interacts with all layers of the application
 * (service, mapper, API service, DAO service, database) to retrieve region data.
 * <p>
 * The tests use:
 * - H2 in-memory database with test data from tb-region-data.sql
 * - The "test" profile for specific test configurations
 * - ClockTestConfiguration for consistent timestamps in responses
 * - Full Spring context to test the entire request-response flow
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(ClockTestConfiguration.class)
class RegionWebControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /regions/{id} should return the region when it exists")
    void getRegion_ShouldReturnRegion_WhenItExists() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/regions/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("North America"))
                .andExpect(jsonPath("$.tag").value("US"));
    }

    @Test
    @DisplayName("GET /regions/{id} should return 404 when the region does not exist")
    void getRegion_ShouldReturn404_WhenRegionDoesNotExist() throws Exception {
        // Arrange
        Short nonExistentRegionId = 999;

        // Act & Assert
        mockMvc.perform(get("/regions/{id}", nonExistentRegionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value("Region doest not exist"))
                .andExpect(jsonPath("$.detail").value("Region with ID = 999 does not exist."))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("GET /regions/{id} should return 400 when the ID is invalid")
    void getRegion_ShouldReturn400_WhenIdIsInvalid() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/regions/{id}", "invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("GET /regions should return all regions")
    void getRegions_ShouldReturnAllRegions() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/regions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.regions").isArray())
                .andExpect(jsonPath("$.regions", hasSize(3)))
                .andExpect(jsonPath("$.regions[0].id").value(1))
                .andExpect(jsonPath("$.regions[0].name").value("North America"))
                .andExpect(jsonPath("$.regions[0].tag").value("US"))
                .andExpect(jsonPath("$.regions[1].id").value(2))
                .andExpect(jsonPath("$.regions[1].name").value("Korea"))
                .andExpect(jsonPath("$.regions[1].tag").value("KR"))
                .andExpect(jsonPath("$.regions[2].id").value(3))
                .andExpect(jsonPath("$.regions[2].name").value("Europe"))
                .andExpect(jsonPath("$.regions[2].tag").value("EU"));
    }
}
