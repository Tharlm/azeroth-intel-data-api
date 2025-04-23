package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between API models and DTOs in the region web layer.
 * <p>
 * This mapper is responsible for:
 * 1. Converting RegionApi objects to GetRegionResponseDto objects
 * 2. Converting lists of RegionApi objects to GetRegionsResponseDto objects
 * <p>
 * It uses MapStruct for automatic implementation of the mapping methods based on
 * field names and types. The mapper inherits common mapping configurations from
 * AzerothMapperParent.
 */
@Mapper(
        config = AzerothMapperParent.class
)
public interface RegionWebMapper {

    /**
     * Converts a single RegionApi object to a GetRegionResponseDto.
     *
     * @param regionApi The RegionApi object to convert
     * @return The corresponding GetRegionResponseDto
     */
    GetRegionResponseDto apiToDto(RegionApi regionApi);

    /**
     * Converts a list of RegionApi objects to a GetRegionsResponseDto.
     * <p>
     * This method uses apisToDtosResult to convert the list of RegionApi objects
     * to a list of RegionResultDto objects, then wraps them in a GetRegionsResponseDto.
     *
     * @param apis The list of RegionApi objects to convert
     * @return A GetRegionsResponseDto containing the converted RegionResultDto objects
     */
    default GetRegionsResponseDto apisToDtos(List<RegionApi> apis) {
        final GetRegionsResponseDto dto = new GetRegionsResponseDto();
        dto.setRegions(apisToDtosResult(apis));
        return dto;
    }

    /**
     * Converts a list of RegionApi objects to a list of RegionResultDto objects.
     * <p>
     * This method is used internally by apisToDtos to convert the individual
     * RegionApi objects to RegionResultDto objects.
     *
     * @param regionApi The list of RegionApi objects to convert
     * @return A list of corresponding RegionResultDto objects
     */
    List<GetRegionsResponseDto.RegionResultDto> apisToDtosResult(List<RegionApi> regionApi);
}
