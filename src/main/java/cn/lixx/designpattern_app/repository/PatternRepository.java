package cn.lixx.designpattern_app.repository;

import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatternRepository {
    private final List<Pattern> patterns = new ArrayList<>();

    public PatternRepository() {
        // 在后续任务中填充数据
    }

    public List<Pattern> findAll() {
        return patterns;
    }

    public List<Pattern> findByCategory(PatternCategory category) {
        return patterns.stream()
                .filter(p -> p.getCategory() == category)
                .toList();
    }

    public List<Pattern> findByDifficulty(PatternDifficulty difficulty) {
        return patterns.stream()
                .filter(p -> p.getDifficulty() == difficulty)
                .toList();
    }

    public Optional<Pattern> findById(String id) {
        return patterns.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
