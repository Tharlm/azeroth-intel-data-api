package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRegionsResponseDto {

    private List<RegionResultDto> regions;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegionResultDto {
        private Short id;
        private String name;
        private String tag;
    }
}
