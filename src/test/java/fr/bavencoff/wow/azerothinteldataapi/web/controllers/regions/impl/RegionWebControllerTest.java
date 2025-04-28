package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegionWebControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RegionWebService regionWebService;

    @InjectMocks
    private RegionWebController regionWebController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver();
        exceptionResolver.afterPropertiesSet();
        mockMvc = MockMvcBuilders.standaloneSetup(regionWebController)
                .setHandlerExceptionResolvers(exceptionResolver)
                .build();
    }

    @Test
    void getRegion_ShouldReturnRegion_WhenValidIdProvided() throws Exception {
        // Arrange
        Short regionId = 1;
        GetRegionResponseDto responseDto = new GetRegionResponseDto();
        responseDto.setId(regionId);
        responseDto.setName("US");
        responseDto.setTag(GlobalRegion.US);

        when(regionWebService.getRegion(regionId)).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(get("/regions/{id}", regionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("US"))
                .andExpect(jsonPath("$.tag").value("US"));

        verify(regionWebService, times(1)).getRegion(regionId);
    }


    @Test
    void getRegions_ShouldReturnAllRegions() throws Exception {
        GetRegionsResponseDto.RegionResultDto region1 = new GetRegionsResponseDto.RegionResultDto();
        region1.setId((short) 1);
        region1.setName("US");
        region1.setTag("US");

        GetRegionsResponseDto.RegionResultDto region2 = new GetRegionsResponseDto.RegionResultDto();
        region2.setId((short) 3);
        region2.setName("EU");
        region2.setTag("EU");

        List<GetRegionsResponseDto.RegionResultDto> regionList = Arrays.asList(region1, region2);

        GetRegionsResponseDto responseDto = new GetRegionsResponseDto();
        responseDto.setRegions(regionList);

        when(regionWebService.getRegions()).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(get("/regions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.regions").isArray())
                .andExpect(jsonPath("$.regions.length()").value(2))
                .andExpect(jsonPath("$.regions[0].id").value(1))
                .andExpect(jsonPath("$.regions[0].name").value("US"))
                .andExpect(jsonPath("$.regions[0].tag").value("US"))
                .andExpect(jsonPath("$.regions[1].id").value(3))
                .andExpect(jsonPath("$.regions[1].name").value("EU"))
                .andExpect(jsonPath("$.regions[1].tag").value("EU"));

        verify(regionWebService, times(1)).getRegions();
    }

    @Test
    void getRegions_ShouldReturnEmptyList_WhenNoRegionsExist() throws Exception {
        // Arrange
        GetRegionsResponseDto responseDto = new GetRegionsResponseDto();
        responseDto.setRegions(Collections.emptyList());

        when(regionWebService.getRegions()).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(get("/regions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.regions").isArray())
                .andExpect(jsonPath("$.regions.length()").value(0));

        verify(regionWebService, times(1)).getRegions();
    }
}
