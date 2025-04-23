package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RegionApiServiceImpl class.
 * 
 * These tests verify that the service correctly:
 * 1. Delegates calls to the DAO service exporter
 * 2. Maps DAO entities to API models using the mapper
 * 3. Returns the appropriate API models
 * 4. Handles exceptions properly
 */
class RegionApiServiceImplTest {

    @Mock
    private RegionDaoServiceExporter daoServiceExporter;

    @Mock
    private RegionApiMapper mapper;

    @InjectMocks
    private RegionApiServiceImpl regionApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getRegion should return the correct API model when a valid ID is provided")
    void getRegion_ShouldReturnCorrectApiModel_WhenValidIdProvided() {
        // Arrange
        Short regionId = 1;
        RegionDao regionDao = new RegionDao();
        regionDao.setId(regionId);
        regionDao.setName("North America");
        regionDao.setTag(GlobalRegion.US);

        RegionApi expectedApi = new RegionApi();
        expectedApi.setId(regionId.intValue());
        expectedApi.setName("North America");
        expectedApi.setTag(GlobalRegion.US);

        when(daoServiceExporter.findById(regionId)).thenReturn(regionDao);
        when(mapper.daoToApi(regionDao)).thenReturn(expectedApi);

        // Act
        RegionApi result = regionApiService.getRegion(regionId);

        // Assert
        assertNotNull(result);
        assertEquals(regionId.intValue(), result.getId());
        assertEquals("North America", result.getName());
        assertEquals(GlobalRegion.US, result.getTag());

        verify(daoServiceExporter, times(1)).findById(regionId);
        verify(mapper, times(1)).daoToApi(regionDao);
    }

    @Test
    @DisplayName("getRegion should propagate exceptions from the DAO service exporter")
    void getRegion_ShouldPropagateExceptions_WhenDaoServiceExporterThrows() {
        // Arrange
        Short regionId = 999;
        when(daoServiceExporter.findById(regionId)).thenThrow(new RegionNotFoundResponseException(regionId));

        // Act & Assert
        assertThrows(RegionNotFoundResponseException.class, () -> regionApiService.getRegion(regionId));
        verify(daoServiceExporter, times(1)).findById(regionId);
        verify(mapper, never()).daoToApi(any());
    }

    @Test
    @DisplayName("getRegions should return all regions")
    void getRegions_ShouldReturnAllRegions() {
        // Arrange
        RegionDao regionDao1 = new RegionDao();
        regionDao1.setId((short) 1);
        regionDao1.setName("North America");
        regionDao1.setTag(GlobalRegion.US);

        RegionDao regionDao2 = new RegionDao();
        regionDao2.setId((short) 3);
        regionDao2.setName("Europe");
        regionDao2.setTag(GlobalRegion.EU);

        List<RegionDao> regionDaos = Arrays.asList(regionDao1, regionDao2);

        RegionApi regionApi1 = new RegionApi();
        regionApi1.setId(1);
        regionApi1.setName("North America");
        regionApi1.setTag(GlobalRegion.US);

        RegionApi regionApi2 = new RegionApi();
        regionApi2.setId(3);
        regionApi2.setName("Europe");
        regionApi2.setTag(GlobalRegion.EU);

        List<RegionApi> expectedApis = Arrays.asList(regionApi1, regionApi2);

        when(daoServiceExporter.findAll()).thenReturn(regionDaos);
        when(mapper.daosToApis(regionDaos)).thenReturn(expectedApis);

        // Act
        List<RegionApi> result = regionApiService.getRegions();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("North America", result.get(0).getName());
        assertEquals(GlobalRegion.US, result.get(0).getTag());
        assertEquals(3, result.get(1).getId());
        assertEquals("Europe", result.get(1).getName());
        assertEquals(GlobalRegion.EU, result.get(1).getTag());

        verify(daoServiceExporter, times(1)).findAll();
        verify(mapper, times(1)).daosToApis(regionDaos);
    }

    @Test
    @DisplayName("getRegions should return empty list when no regions exist")
    void getRegions_ShouldReturnEmptyList_WhenNoRegionsExist() {
        // Arrange
        List<RegionDao> emptyList = Collections.emptyList();
        List<RegionApi> emptyApiList = Collections.emptyList();

        when(daoServiceExporter.findAll()).thenReturn(emptyList);
        when(mapper.daosToApis(emptyList)).thenReturn(emptyApiList);

        // Act
        List<RegionApi> result = regionApiService.getRegions();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(daoServiceExporter, times(1)).findAll();
        verify(mapper, times(1)).daosToApis(emptyList);
    }
}
