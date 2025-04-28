package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl.RegionServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the RegionWebService class.
 * <p>
 * These tests verify that the service correctly:
 * 1. Delegates calls to the API service
 * 2. Maps API models to DTOs using the mapper
 * 3. Returns the appropriate response DTOs
 * 4. Handles exceptions properly
 */
class RegionWebServiceTest {

    @Mock
    private RegionServiceHelper regionServiceHelper;

    @Mock
    private RegionWebMapper regionWebMapper;

    @InjectMocks
    private RegionWebService regionWebService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getRegion should return the correct DTO when a valid ID is provided")
    void getRegion_ShouldReturnCorrectDto_WhenValidIdProvided() {
        // Arrange
        Short regionId = 1;
        RegionBo regionBo = new RegionBo();
        regionBo.setId(regionId.intValue());
        regionBo.setName("North America");
        regionBo.setTag(GlobalRegion.US);

        GetRegionResponseDto expectedDto = new GetRegionResponseDto();
        expectedDto.setId(regionId);
        expectedDto.setName("North America");
        expectedDto.setTag(GlobalRegion.US);

        when(regionServiceHelper.getRegion(regionId)).thenReturn(regionBo);
        when(regionWebMapper.apiToDto(regionBo)).thenReturn(expectedDto);

        // Act
        GetRegionResponseDto result = regionWebService.getRegion(regionId);

        // Assert
        assertNotNull(result);
        assertEquals(regionId, result.getId());
        assertEquals("North America", result.getName());
        assertEquals(GlobalRegion.US, result.getTag());

        verify(regionServiceHelper, times(1)).getRegion(regionId);
        verify(regionWebMapper, times(1)).apiToDto(regionBo);
    }

    @Test
    @DisplayName("getRegion should propagate exceptions from the API service")
    void getRegion_ShouldPropagateExceptions_WhenApiServiceThrows() {
        // Arrange
        Short regionId = 999;
        when(regionServiceHelper.getRegion(regionId)).thenThrow(new RegionNotFoundResponseException(regionId));

        // Act & Assert
        assertThrows(RegionNotFoundResponseException.class, () -> regionWebService.getRegion(regionId));
        verify(regionServiceHelper, times(1)).getRegion(regionId);
        verify(regionWebMapper, never()).apiToDto(any());
    }

    @Test
    @DisplayName("getRegions should return all regions")
    void getRegions_ShouldReturnAllRegions() {
        // Arrange
        RegionBo region1 = new RegionBo();
        region1.setId(1);
        region1.setName("North America");
        region1.setTag(GlobalRegion.US);

        RegionBo region2 = new RegionBo();
        region2.setId(3);
        region2.setName("Europe");
        region2.setTag(GlobalRegion.EU);

        List<RegionBo> regionBos = Arrays.asList(region1, region2);

        GetRegionsResponseDto.RegionResultDto resultDto1 = new GetRegionsResponseDto.RegionResultDto();
        resultDto1.setId((short) 1);
        resultDto1.setName("North America");
        resultDto1.setTag("US");

        GetRegionsResponseDto.RegionResultDto resultDto2 = new GetRegionsResponseDto.RegionResultDto();
        resultDto2.setId((short) 3);
        resultDto2.setName("Europe");
        resultDto2.setTag("EU");

        List<GetRegionsResponseDto.RegionResultDto> resultDtos = Arrays.asList(resultDto1, resultDto2);

        GetRegionsResponseDto expectedDto = new GetRegionsResponseDto();
        expectedDto.setRegions(resultDtos);

        when(regionServiceHelper.getRegions()).thenReturn(regionBos);
        when(regionWebMapper.apisToDtos(regionBos)).thenReturn(expectedDto);

        // Act
        GetRegionsResponseDto result = regionWebService.getRegions();

        // Assert
        assertNotNull(result);
        assertNotNull(result.getRegions());
        assertEquals(2, result.getRegions().size());
        assertEquals((short) 1, result.getRegions().getFirst().getId());
        assertEquals("North America", result.getRegions().getFirst().getName());
        assertEquals("US", result.getRegions().getFirst().getTag());
        assertEquals((short) 3, result.getRegions().get(1).getId());
        assertEquals("Europe", result.getRegions().get(1).getName());
        assertEquals("EU", result.getRegions().get(1).getTag());

        verify(regionServiceHelper, times(1)).getRegions();
        verify(regionWebMapper, times(1)).apisToDtos(regionBos);
    }

    @Test
    @DisplayName("getRegions should return empty list when no regions exist")
    void getRegions_ShouldReturnEmptyList_WhenNoRegionsExist() {
        // Arrange
        List<RegionBo> emptyList = Collections.emptyList();
        GetRegionsResponseDto emptyDto = new GetRegionsResponseDto();
        emptyDto.setRegions(Collections.emptyList());

        when(regionServiceHelper.getRegions()).thenReturn(emptyList);
        when(regionWebMapper.apisToDtos(emptyList)).thenReturn(emptyDto);

        // Act
        GetRegionsResponseDto result = regionWebService.getRegions();

        // Assert
        assertNotNull(result);
        assertNotNull(result.getRegions());
        assertTrue(result.getRegions().isEmpty());

        verify(regionServiceHelper, times(1)).getRegions();
        verify(regionWebMapper, times(1)).apisToDtos(emptyList);
    }
}
