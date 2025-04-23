package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemDaoRepository extends JpaRepository<ItemDao, Long> {
    @EntityGraph(value = "get-item-graph")
    Optional<ItemDao> findById(long id);
}
