package fr.bavencoff.wow.azerothinteldataapi.testutils.mocks;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;

import java.time.Instant;
import java.util.Collections;

public final class ConnectedRealmsMock {

    private ConnectedRealmsMock() {
    }

    public static ConnectedRealmDao ConnectedRealm12() {
        final ConnectedRealmDao dao = new ConnectedRealmDao();

        dao.setId(12);
        dao.setQueue(false);
        dao.setPopulation(ParametersMock.connectedRealmLow());
        dao.setStatus(ParametersMock.connectedRealmUp());
        dao.setActive(true);
        dao.setDateLastUpdate(Instant.parse("2024-01-06T19:56:19Z"));
        dao.setRegion(RegionsMock.regionUs());
        dao.setRealms(Collections.emptySet());

        return dao;
    }

    public static ConnectedRealmDao ConnectedRealm531() {
        final ConnectedRealmDao dao = new ConnectedRealmDao();

        dao.setId(531);
        dao.setQueue(false);
        dao.setPopulation(ParametersMock.connectedRealmLow());
        dao.setStatus(ParametersMock.connectedRealmUp());
        dao.setActive(true);
        dao.setDateLastUpdate(Instant.parse("2024-01-06T19:56:27Z"));
        dao.setRegion(RegionsMock.regionEu());
        dao.setRealms(Collections.emptySet());

        return dao;
    }

    public static ConnectedRealmDao ConnectedRealm563() {
        final ConnectedRealmDao dao = new ConnectedRealmDao();

        dao.setId(563);
        dao.setQueue(false);
        dao.setPopulation(ParametersMock.connectedRealmLow());
        dao.setStatus(ParametersMock.connectedRealmUp());
        dao.setActive(true);
        dao.setDateLastUpdate(Instant.parse("2024-01-06T19:56:28Z"));
        dao.setRegion(RegionsMock.regionKr());
        dao.setRealms(Collections.emptySet());

        return dao;
    }
}
