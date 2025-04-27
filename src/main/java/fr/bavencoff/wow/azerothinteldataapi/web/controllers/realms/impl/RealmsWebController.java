package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.get.GetRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.getall.GetAllRealmResponseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realms")
public class RealmsWebController {

    private final RealmsWebServiceExporter service;

    @Autowired
    public RealmsWebController(RealmsWebServiceExporter service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public GetRealmResponseDto getRealm(
            @Max(Integer.MAX_VALUE) @Min(0)
            @PathVariable(name = "id") @NotNull Integer id
    ) {
        return this.service.get(id);
    }

    @GetMapping("")
    public GetAllRealmResponseDto getRealms() {
        return this.service.findAll();
    }

}
