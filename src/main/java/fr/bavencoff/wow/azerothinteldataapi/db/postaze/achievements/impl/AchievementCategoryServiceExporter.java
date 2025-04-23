package fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements.AchievementCategoryNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.AchievementCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AchievementCategoryServiceExporter {
    private final AchievementCategoryRepository repository;

    @Autowired
    public AchievementCategoryServiceExporter(
            final AchievementCategoryRepository repository
    ) {
        this.repository = repository;
    }

    public List<AchievementCategory> findAll() {
        return this.repository.findAll();
    }

    public AchievementCategory findNullableById(Integer id) {
        if (id == null) {
            return null;
        }
        final Optional<AchievementCategory> optionalById = this.findOptionalById(id);
        return optionalById.orElse(null);
    }

    public Optional<AchievementCategory> findOptionalById(
            Integer id
    ) {
        return this.repository.findById(id);
    }

    public AchievementCategory findById(
            Integer id
    ) {
        final Optional<AchievementCategory> optionalById = this.findOptionalById(id);
        if (optionalById.isPresent()) {
            return optionalById.get();
        }
        throw new AchievementCategoryNotFoundException(id);
    }

    public List<AchievementCategory> findByIdsIn(
            List<Integer> ids
    ) {
        return this.repository.findByIdIn(ids);
    }

    public AchievementCategory save(
            AchievementCategory achievementCategory
    ) {
        return this.repository.save(achievementCategory);
    }

    public List<AchievementCategory> saveAll(
            List<AchievementCategory> categories
    ) {
        return this.repository.saveAll(categories);
    }
}
