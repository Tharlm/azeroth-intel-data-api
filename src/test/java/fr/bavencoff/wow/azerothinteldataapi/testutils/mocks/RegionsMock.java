package fr.bavencoff.wow.azerothinteldataapi.testutils.mocks;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;

public final class RegionsMock {

    private RegionsMock() {
    }

    public static RegionDao regionUs() {
        return RegionDao.builder()
                .id((short) 1)
                .name("North America")
                .tag(GlobalRegion.US)
                .build();
    }

    public static RegionDao regionKr() {
        return RegionDao.builder()
                .id((short) 2)
                .name("Korea")
                .tag(GlobalRegion.KR)
                .build();
    }

    public static RegionDao regionEu() {
        return RegionDao.builder()
                .id((short) 3)
                .name("Europe")
                .tag(GlobalRegion.EU)
                .build();
    }
}
