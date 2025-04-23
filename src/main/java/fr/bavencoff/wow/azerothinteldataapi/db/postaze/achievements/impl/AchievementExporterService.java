package fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements.AchievementCategoryNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.Achievement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AchievementExporterService {
    private final AchievementRepository repository;

    @Autowired
    public AchievementExporterService(final AchievementRepository repository) {
        this.repository = repository;
    }

    public Optional<Achievement> findOptionalById(
            Integer id
    ) {
        return this.repository.findById(id);
    }

    public Achievement findById(
            Integer id
    ) {
        final Optional<Achievement> optionalById = this.findOptionalById(id);
        if (optionalById.isPresent()) {
            return optionalById.get();
        }
        throw new AchievementCategoryNotFoundException(id);
    }

    public List<Achievement> findByIdsIn(
            List<Integer> ids
    ) {
        return this.repository.findByIdIn(ids);
    }

    public Achievement save(
            Achievement achievement
    ) {
        return this.repository.save(achievement);
    }

    public List<Achievement> saveAll(
            List<Achievement> achievements
    ) {
        return this.repository.saveAll(achievements);
    }

    public List<Achievement> findAll() {
        return this.repository.findAll();
    }
}
