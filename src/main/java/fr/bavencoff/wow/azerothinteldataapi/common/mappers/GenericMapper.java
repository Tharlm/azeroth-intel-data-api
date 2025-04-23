package fr.bavencoff.wow.azerothinteldataapi.common.mappers;

import java.util.List;

public interface GenericMapper<S, T> {

    T daoToApi(S dao);

    List<T> daosToApis(List<S> dao);
}
