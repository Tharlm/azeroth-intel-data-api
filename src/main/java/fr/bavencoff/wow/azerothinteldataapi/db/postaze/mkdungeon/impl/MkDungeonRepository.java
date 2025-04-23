package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MkDungeonRepository extends JpaRepository<MkDungeon, Integer> {
}
