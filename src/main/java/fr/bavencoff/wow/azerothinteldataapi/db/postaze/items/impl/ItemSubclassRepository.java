package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemSubclass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSubclassRepository extends JpaRepository<ItemSubclass, Integer> {
}
