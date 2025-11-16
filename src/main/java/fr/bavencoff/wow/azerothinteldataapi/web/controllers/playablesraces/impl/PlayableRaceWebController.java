package fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.post.PostPlayableRaceRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.post.PostPlayableRaceResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put.PutPlayableRaceRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put.PutPlayableRaceResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playable-races")
public class PlayableRaceWebController {

    private final PlayableRaceWebService serviceExporter;

    @Autowired
    public PlayableRaceWebController(PlayableRaceWebService serviceExporter) {
        this.serviceExporter = serviceExporter;
    }


    @PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public PostPlayableRaceResponseDto postPlayableRace(@Valid @RequestBody PostPlayableRaceRequestDto request) {
        return this.serviceExporter.postPlayableRace(request);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public PutPlayableRaceResponseDto putPlayableRace(
            @NotNull @Min(0) @Max(Integer.MAX_VALUE) @PathVariable Integer id,
            @Valid @RequestBody PutPlayableRaceRequestDto request
    ) {
        return this.serviceExporter.putPlayableRace(id, request);
    }
}
