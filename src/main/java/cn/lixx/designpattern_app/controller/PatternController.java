package cn.lixx.designpattern_app.controller;

import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;
import cn.lixx.designpattern_app.repository.PatternRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatternController {
    private final PatternRepository patternRepository;

    public PatternController(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("patterns", patternRepository.findAll());
        model.addAttribute("categories", PatternCategory.values());
        model.addAttribute("difficulties", PatternDifficulty.values());
        return "index";
    }

    @GetMapping("/pattern/{id}")
    public String patternDetail(@PathVariable String id, Model model) {
        return patternRepository.findById(id)
                .map(pattern -> {
                    model.addAttribute("pattern", pattern);
                    return "pattern-detail";
                })
                .orElse("redirect:/");
    }

    @GetMapping("/category/{category}")
    public String byCategory(@PathVariable PatternCategory category, Model model) {
        model.addAttribute("patterns", patternRepository.findByCategory(category));
        model.addAttribute("categories", PatternCategory.values());
        model.addAttribute("difficulties", PatternDifficulty.values());
        model.addAttribute("selectedCategory", category);
        return "index";
    }

    @GetMapping("/difficulty/{difficulty}")
    public String byDifficulty(@PathVariable PatternDifficulty difficulty, Model model) {
        model.addAttribute("patterns", patternRepository.findByDifficulty(difficulty));
        model.addAttribute("categories", PatternCategory.values());
        model.addAttribute("difficulties", PatternDifficulty.values());
        model.addAttribute("selectedDifficulty", difficulty);
        return "index";
    }
}
