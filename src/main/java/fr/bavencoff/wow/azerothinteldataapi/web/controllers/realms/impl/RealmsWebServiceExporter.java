package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl.RealmServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.get.GetRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.getall.GetAllRealmResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealmsWebServiceExporter {
    private final RealmServiceHelper realmServiceHelper;
    private final RealmsWebMapper mapper;

    @Autowired
    public RealmsWebServiceExporter(
            final RealmServiceHelper realmServiceHelper,
            final RealmsWebMapper mapper
    ) {
        this.realmServiceHelper = realmServiceHelper;
        this.mapper = mapper;
    }

    public GetRealmResponseDto get(Integer id) {
        RealmBo realmView = this.realmServiceHelper.getRealm(id);
        return mapper.castViewToGetRealmResponse(realmView);
    }

    public GetAllRealmResponseDto findAll() {
        final GetAllRealmResponseDto dto = new GetAllRealmResponseDto();
        dto.setRealms(mapper.apisToDtos(
                this.realmServiceHelper.getRealms()
        ));
        return dto;
    }


}
