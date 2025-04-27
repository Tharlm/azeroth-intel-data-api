package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.getall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRealmResponseDto {
    private List<RealmResultDto> realms;
}
