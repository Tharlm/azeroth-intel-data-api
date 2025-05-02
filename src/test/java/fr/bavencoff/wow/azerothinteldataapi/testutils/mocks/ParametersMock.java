package fr.bavencoff.wow.azerothinteldataapi.testutils.mocks;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;

public final class ParametersMock {

    private ParametersMock() {
    }

    public static ParameterTypeDao realmNormal() {
        ParameterTypeDao dao = new ParameterTypeDao();
        dao.setId(1);
        dao.setKey(KeyParameterType.REA);
        dao.setName("Normal");
        dao.setType("NORMAL");
        return dao;
    }

    public static ParameterTypeDao realmRolePlaying() {
        ParameterTypeDao dao = new ParameterTypeDao();
        dao.setId(2);
        dao.setKey(KeyParameterType.REA);
        dao.setName("Roleplaying");
        dao.setType("RP");
        return dao;
    }

    public static ParameterTypeDao connectedRealmUp() {
        ParameterTypeDao dao = new ParameterTypeDao();

        dao.setId(3);
        dao.setKey(KeyParameterType.CRS);
        dao.setName("Up");
        dao.setType("UP");

        return dao;
    }

    public static ParameterTypeDao connectedRealmLow() {
        ParameterTypeDao dao = new ParameterTypeDao();

        dao.setId(4);
        dao.setKey(KeyParameterType.CRS);
        dao.setName("Low");
        dao.setType("LOW");

        return dao;
    }

}
