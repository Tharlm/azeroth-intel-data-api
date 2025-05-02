package fr.bavencoff.wow.azerothinteldataapi.testutils.mocks;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;

import java.time.Instant;

public final class RealmsMock {

    private RealmsMock() {
    }

    public static RealmDao realm123() {
        RealmDao dao = new RealmDao();

        dao.setId(123);
        dao.setRegion(RegionsMock.regionEu());
        dao.setName("Realm_123");
        dao.setCategory("French");
        dao.setLocale("frFr");
        dao.setTimezone("Europe/Paris");
        dao.setSlug("realm-123");
        dao.setTournament(false);
        dao.setActive(true);
        dao.setDateLastUpdate(Instant.parse("2024-01-06T19:56:27.992088Z"));
        dao.setType(ParametersMock.realmNormal());
        dao.setConnectedRealm(ConnectedRealmsMock.ConnectedRealm12());

        return dao;
    }
}
