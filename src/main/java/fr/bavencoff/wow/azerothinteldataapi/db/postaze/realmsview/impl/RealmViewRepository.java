package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realmsview.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realmsview.dao.RealmView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmViewRepository extends JpaRepository<RealmView, Integer> {
}
