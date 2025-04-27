package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmResponseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connectedrealms")
public class ConnectedRealmsWebController {

    private final ConnectedRealmsWebService connectedRealmsService;

    @Autowired
    public ConnectedRealmsWebController(
            final ConnectedRealmsWebService connectedRealmsService
    ) {
        this.connectedRealmsService = connectedRealmsService;
    }

    @GetMapping(path = "")
    public GetAllConnectedRealmResponseDto findAll() {
        return this.connectedRealmsService.findAll();
    }

    @PostMapping(path = "")
    public PostConnectedRealmResponseDto postConnectedRealm(
            @RequestBody final PostConnectedRealmRequestDto request
    ) {
        return this.connectedRealmsService.post(request);
    }

    @PutMapping(path = "{id}/region/{idRegion}")

    public PutConnectedRealmResponseDto updateConnectedRealm(
            @PathVariable("id") @Min(0) @Max(Integer.MAX_VALUE) @NotNull final Integer id,
            @PathVariable("idRegion") @Min(0) @Max(Short.MAX_VALUE) @NotNull final Short idRegion,
            @RequestBody final PutConnectedRealmRequestDto putConnectedRealmRequestDto
    ) {
        return this.connectedRealmsService.put(id, idRegion, putConnectedRealmRequestDto);
    }
}
