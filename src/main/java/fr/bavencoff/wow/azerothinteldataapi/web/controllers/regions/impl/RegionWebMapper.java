package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface RegionWebMapper {

    GetRegionResponseDto apiToDto(RegionApi regionApi);

    default GetRegionsResponseDto apisToDtos(List<RegionApi> apis) {
        final GetRegionsResponseDto dto = new GetRegionsResponseDto();
        dto.setRegions(apisToDtosResult(apis));
        return dto;
    }

    List<GetRegionsResponseDto.RegionResultDto> apisToDtosResult(List<RegionApi> regionApi);
}
