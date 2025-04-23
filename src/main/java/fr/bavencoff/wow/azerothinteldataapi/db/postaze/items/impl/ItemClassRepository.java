package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemClass;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemClassRepository extends JpaRepository<ItemClass, Short> {
    @EntityGraph(value = "get-item-class-graph")
    List<ItemClass> findAll();

    @EntityGraph(value = "get-item-class-graph")
    Optional<ItemClass> findById(@NotNull final Short id);
}
