package fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements.AchievementCategoryAlreadyExistingException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.AchievementCategory;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.impl.AchievementCategoryServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.impl.AchievementExporterService;
import fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model.AchievementApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model.AchievementCategoryApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AchievementApiServiceImpl implements AchievementApiService {
    private final AchievementExporterService achievementExporterService;
    private final AchievementCategoryServiceExporter achievementCategoryServiceExporter;
    private final AchievementsApiMapper mapper;
    private final AchievementsCategoryApiMapper achiCategoryMapper;

    @Autowired
    public AchievementApiServiceImpl(
            final AchievementCategoryServiceExporter achievementCategoryServiceExporter,
            final AchievementExporterService achievementExporterService,
            final AchievementsApiMapper mapper,
            final AchievementsCategoryApiMapper achiCategoryMapper
    ) {
        this.achievementCategoryServiceExporter = achievementCategoryServiceExporter;
        this.achievementExporterService = achievementExporterService;
        this.mapper = mapper;
        this.achiCategoryMapper = achiCategoryMapper;
    }

    @Override
    public AchievementCategoryApi addCategoryAchievement(
            Integer idCategory,
            Integer idParentCategory,
            AchievementCategoryApi information
    ) {
        final Optional<AchievementCategory> achievementCategoryOptional = this.achievementCategoryServiceExporter.findOptionalById(idCategory);
        if (achievementCategoryOptional.isPresent()) {
            throw new AchievementCategoryAlreadyExistingException(idCategory);
        }
        AchievementCategory parentCategory = this.achievementCategoryServiceExporter.findNullableById(idParentCategory);
        AchievementCategory category = new AchievementCategory();
        category.setId(idCategory);
        return achiCategoryMapper.daoToApi(putCategoryAchievement(
                category,
                parentCategory,
                information
        ));
    }

    @Override
    public AchievementCategoryApi putCategoryAchievement(
            Integer idAchievementCategory,
            Integer idParentCategory,
            AchievementCategoryApi information
    ) {
        AchievementCategory parentCategory = this.achievementCategoryServiceExporter.findNullableById(idParentCategory);
        Optional<AchievementCategory> achievementCategoryOptional = this.achievementCategoryServiceExporter.findOptionalById(idAchievementCategory);

        if (achievementCategoryOptional.isPresent()) {
            return achiCategoryMapper.daoToApi(putCategoryAchievement(achievementCategoryOptional.get(), parentCategory, information));
        } else {
            AchievementCategory category = new AchievementCategory();
            category.setId(idAchievementCategory);
            return achiCategoryMapper.daoToApi(putCategoryAchievement(category, parentCategory, information));
        }
    }

    @Override
    public List<AchievementCategoryApi> findAllCategoryAchievement() {
        return List.of();
    }

    private AchievementCategory putCategoryAchievement(
            AchievementCategory category,
            AchievementCategory parentCategory,
            AchievementCategoryApi information
    ) {
//        this.mapper.partialUpdate(information, parentCategory, category);
        return this.achievementCategoryServiceExporter.save(category);
    }

    @Override
    public List<AchievementApi> findAllAchievement() {
        return List.of();
    }
}
