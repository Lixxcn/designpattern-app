package cn.lixx.designpattern_app.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PatternRepository 单元测试")
class PatternRepositoryTest {

    private final PatternRepository patternRepository = new PatternRepository();

    @Test
    @DisplayName("应该返回所有23个设计模式")
    void testFindAll() {
        // When
        List<Pattern> patterns = patternRepository.findAll();

        // Then
        assertThat(patterns).hasSize(23);
    }

    @Test
    @DisplayName("应该按类别查找设计模式 - 创建型")
    void testFindByCategory_Creational() {
        // When
        List<Pattern> patterns = patternRepository.findByCategory(PatternCategory.CREATIONAL);

        // Then
        assertThat(patterns).hasSize(5);
        assertThat(patterns).allMatch(p -> p.getCategory() == PatternCategory.CREATIONAL);
        assertThat(patterns).extracting("id")
                .containsExactlyInAnyOrder("singleton", "factory-method", "abstract-factory", "builder", "prototype");
    }

    @Test
    @DisplayName("应该按类别查找设计模式 - 结构型")
    void testFindByCategory_Structural() {
        // When
        List<Pattern> patterns = patternRepository.findByCategory(PatternCategory.STRUCTURAL);

        // Then
        assertThat(patterns).hasSize(7);
        assertThat(patterns).allMatch(p -> p.getCategory() == PatternCategory.STRUCTURAL);
    }

    @Test
    @DisplayName("应该按类别查找设计模式 - 行为型")
    void testFindByCategory_Behavioral() {
        // When
        List<Pattern> patterns = patternRepository.findByCategory(PatternCategory.BEHAVIORAL);

        // Then
        assertThat(patterns).hasSize(11);
        assertThat(patterns).allMatch(p -> p.getCategory() == PatternCategory.BEHAVIORAL);
    }

    @Test
    @DisplayName("应该按难度查找设计模式 - 初级")
    void testFindByDifficulty_Beginner() {
        // When
        List<Pattern> patterns = patternRepository.findByDifficulty(PatternDifficulty.BEGINNER);

        // Then
        assertThat(patterns).isNotEmpty();
        assertThat(patterns).allMatch(p -> p.getDifficulty() == PatternDifficulty.BEGINNER);
        assertThat(patterns).extracting("id")
                .contains("singleton", "factory-method", "prototype", "adapter", "observer", "strategy", "template", "facade", "iterator");
    }

    @Test
    @DisplayName("应该按难度查找设计模式 - 中级")
    void testFindByDifficulty_Intermediate() {
        // When
        List<Pattern> patterns = patternRepository.findByDifficulty(PatternDifficulty.INTERMEDIATE);

        // Then
        assertThat(patterns).isNotEmpty();
        assertThat(patterns).allMatch(p -> p.getDifficulty() == PatternDifficulty.INTERMEDIATE);
    }

    @Test
    @DisplayName("应该按难度查找设计模式 - 高级")
    void testFindByDifficulty_Advanced() {
        // When
        List<Pattern> patterns = patternRepository.findByDifficulty(PatternDifficulty.ADVANCED);

        // Then
        assertThat(patterns).isNotEmpty();
        assertThat(patterns).allMatch(p -> p.getDifficulty() == PatternDifficulty.ADVANCED);
    }

    @Test
    @DisplayName("应该按ID查找设计模式 - 单例模式")
    void testFindById_Singleton() {
        // When
        Optional<Pattern> pattern = patternRepository.findById("singleton");

        // Then
        assertThat(pattern).isPresent();
        assertThat(pattern.get().getId()).isEqualTo("singleton");
        assertThat(pattern.get().getName()).isEqualTo("单例模式");
        assertThat(pattern.get().getNameEn()).isEqualTo("Singleton Pattern");
        assertThat(pattern.get().getCategory()).isEqualTo(PatternCategory.CREATIONAL);
        assertThat(pattern.get().getDifficulty()).isEqualTo(PatternDifficulty.BEGINNER);
    }

    @Test
    @DisplayName("应该按ID查找设计模式 - 工厂方法模式")
    void testFindById_FactoryMethod() {
        // When
        Optional<Pattern> pattern = patternRepository.findById("factory-method");

        // Then
        assertThat(pattern).isPresent();
        assertThat(pattern.get().getId()).isEqualTo("factory-method");
        assertThat(pattern.get().getName()).isEqualTo("工厂方法模式");
    }

    @Test
    @DisplayName("查找不存在的ID应该返回空Optional")
    void testFindById_NotFound() {
        // When
        Optional<Pattern> pattern = patternRepository.findById("nonexistent");

        // Then
        assertThat(pattern).isEmpty();
    }

    @Test
    @DisplayName("设计模式应该包含所有必需的字段")
    void testPatternFields() {
        // When
        List<Pattern> patterns = patternRepository.findAll();

        // Then
        assertThat(patterns).allMatch(p ->
                p.getId() != null && !p.getId().isEmpty() &&
                p.getName() != null && !p.getName().isEmpty() &&
                p.getNameEn() != null && !p.getNameEn().isEmpty() &&
                p.getCategory() != null &&
                p.getDifficulty() != null &&
                p.getDefinition() != null && !p.getDefinition().isEmpty() &&
                p.getIntent() != null && !p.getIntent().isEmpty() &&
                p.getUseCases() != null && !p.getUseCases().isEmpty() &&
                p.getParticipants() != null && !p.getParticipants().isEmpty() &&
                p.getCollaboration() != null && !p.getCollaboration().isEmpty() &&
                p.getProsCons() != null && !p.getProsCons().isEmpty() &&
                p.getRelatedPatterns() != null && !p.getRelatedPatterns().isEmpty() &&
                p.getSpringExample() != null && !p.getSpringExample().isEmpty() &&
                p.getJdkExample() != null && !p.getJdkExample().isEmpty() &&
                p.getRealWorldExample() != null && !p.getRealWorldExample().isEmpty()
        );
    }

    @Test
    @DisplayName("所有设计模式应该有唯一的ID")
    void testUniqueIds() {
        // When
        List<Pattern> patterns = patternRepository.findAll();

        // Then
        List<String> ids = patterns.stream().map(Pattern::getId).toList();
        assertThat(ids).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("所有设计模式应该有唯一的中文名称")
    void testUniqueNames() {
        // When
        List<Pattern> patterns = patternRepository.findAll();

        // Then
        List<String> names = patterns.stream().map(Pattern::getName).toList();
        assertThat(names).doesNotHaveDuplicates();
    }
}
