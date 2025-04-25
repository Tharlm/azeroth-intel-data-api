package fr.bavencoff.wow.azerothinteldataapi.integration.db;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for the RegionDaoServiceExporter.
 * <p>
 * These tests verify that the service correctly interacts with the database
 * to retrieve region data.
 * <p>
 * The tests use:
 * - H2 in-memory database with test data from  tb-region-data.sql
 * - DataJpaTest for automatic configuration of the test database
 */
@ActiveProfiles("test")
@DataJpaTest
@Import(RegionDaoServiceExporter.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionDaoServiceExporterIntegrationTest {

    @Autowired
    private RegionDaoServiceExporter regionDaoServiceExporter;

    @Test
    @DisplayName("findById should return the region when it exists")
    void findById_ShouldReturnRegion_WhenItExists() {
        // Arrange
        Short regionId = 1;

        // Act
        RegionDao result = regionDaoServiceExporter.findById(regionId);

        // Assert
        assertNotNull(result);
        assertEquals(regionId, result.getId());
        assertEquals("North America", result.getName());
        assertEquals(GlobalRegion.US, result.getTag());
    }

    @Test
    @DisplayName("findById should throw RegionNotFoundResponseException when the region does not exist")
    void findById_ShouldThrowException_WhenRegionDoesNotExist() {
        // Arrange
        Short nonExistentRegionId = 999;

        // Act & Assert
        assertThrows(RegionNotFoundResponseException.class, () -> {
            regionDaoServiceExporter.findById(nonExistentRegionId);
        });
    }

    @Test
    @DisplayName("findOptionalById should return an Optional containing the region when it exists")
    void findOptionalById_ShouldReturnOptionalWithRegion_WhenItExists() {
        // Arrange
        Short regionId = 1;

        // Act
        Optional<RegionDao> result = regionDaoServiceExporter.findOptionalById(regionId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(regionId, result.get().getId());
        assertEquals("North America", result.get().getName());
        assertEquals(GlobalRegion.US, result.get().getTag());
    }

    @Test
    @DisplayName("findOptionalById should return an empty Optional when the region does not exist")
    void findOptionalById_ShouldReturnEmptyOptional_WhenRegionDoesNotExist() {
        // Arrange
        Short nonExistentRegionId = 999;

        // Act
        Optional<RegionDao> result = regionDaoServiceExporter.findOptionalById(nonExistentRegionId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("findAll should return all regions")
    void findAll_ShouldReturnAllRegions() {
        // Act
        List<RegionDao> result = regionDaoServiceExporter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        // Verify first region
        assertEquals((short) 1, result.getFirst().getId());
        assertEquals("North America", result.get(0).getName());
        assertEquals(GlobalRegion.US, result.get(0).getTag());

        // Verify second region
        assertEquals((short) 2, result.get(1).getId());
        assertEquals("Korea", result.get(1).getName());
        assertEquals(GlobalRegion.KR, result.get(1).getTag());

        // Verify third region
        assertEquals((short) 3, result.get(2).getId());
        assertEquals("Europe", result.get(2).getName());
        assertEquals(GlobalRegion.EU, result.get(2).getTag());
    }
}
