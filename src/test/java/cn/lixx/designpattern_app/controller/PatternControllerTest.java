package cn.lixx.designpattern_app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;
import cn.lixx.designpattern_app.repository.PatternRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatternController.class)
class PatternControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatternRepository patternRepository;

    @Test
    void testIndexPage() throws Exception {
        // Arrange
        Pattern singleton = Pattern.builder()
                .id("singleton")
                .name("单例模式")
                .nameEn("Singleton Pattern")
                .category(PatternCategory.CREATIONAL)
                .difficulty(PatternDifficulty.BEGINNER)
                .definition("保证一个类仅有一个实例")
                .intent("保证一个类仅有一个实例")
                .useCases("当类只能有一个实例时")
                .participants("Singleton, Client")
                .collaboration("Client调用getInstance()")
                .prosCons("优点：受控访问\n缺点：测试不友好")
                .relatedPatterns("工厂方法")
                .springExample("Spring Bean单例")
                .jdkExample("Runtime.getRuntime()")
                .realWorldExample("数据库连接池")
                .mermaidDiagram("classDiagram")
                .codeExample("public class Singleton {}")
                .build();

        when(patternRepository.findAll()).thenReturn(List.of(singleton));

        // Act & Assert
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("patterns"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("difficulties"));
    }

    @Test
    void testPatternDetailPage() throws Exception {
        // Arrange
        Pattern singleton = Pattern.builder()
                .id("singleton")
                .name("单例模式")
                .nameEn("Singleton Pattern")
                .category(PatternCategory.CREATIONAL)
                .difficulty(PatternDifficulty.BEGINNER)
                .definition("保证一个类仅有一个实例")
                .intent("保证一个类仅有一个实例")
                .useCases("当类只能有一个实例时")
                .participants("Singleton, Client")
                .collaboration("Client调用getInstance()")
                .prosCons("优点：受控访问\n缺点：测试不友好")
                .relatedPatterns("工厂方法")
                .springExample("Spring Bean单例")
                .jdkExample("Runtime.getRuntime()")
                .realWorldExample("数据库连接池")
                .mermaidDiagram("classDiagram")
                .codeExample("public class Singleton {}")
                .build();

        when(patternRepository.findById("singleton")).thenReturn(Optional.of(singleton));

        // Act & Assert
        mockMvc.perform(get("/pattern/singleton"))
                .andExpect(status().isOk())
                .andExpect(view().name("pattern-detail"))
                .andExpect(model().attributeExists("pattern"))
                .andExpect(model().attribute("pattern", org.hamcrest.Matchers.hasProperty("id", org.hamcrest.Matchers.is("singleton"))));
    }

    @Test
    void testPatternDetailPage_NotFound() throws Exception {
        // Arrange
        when(patternRepository.findById("nonexistent")).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/pattern/nonexistent"))
                .andExpect(status().isFound()) // 302 redirect
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testFilterByCategory() throws Exception {
        // Arrange
        Pattern singleton = Pattern.builder()
                .id("singleton")
                .name("单例模式")
                .nameEn("Singleton Pattern")
                .category(PatternCategory.CREATIONAL)
                .difficulty(PatternDifficulty.BEGINNER)
                .definition("保证一个类仅有一个实例")
                .intent("保证一个类仅有一个实例")
                .useCases("当类只能有一个实例时")
                .participants("Singleton, Client")
                .collaboration("Client调用getInstance()")
                .prosCons("优点：受控访问\n缺点：测试不友好")
                .relatedPatterns("工厂方法")
                .springExample("Spring Bean单例")
                .jdkExample("Runtime.getRuntime()")
                .realWorldExample("数据库连接池")
                .mermaidDiagram("classDiagram")
                .codeExample("public class Singleton {}")
                .build();

        when(patternRepository.findByCategory(PatternCategory.CREATIONAL)).thenReturn(List.of(singleton));

        // Act & Assert
        mockMvc.perform(get("/category/CREATIONAL"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("patterns"))
                .andExpect(model().attribute("selectedCategory", PatternCategory.CREATIONAL));
    }

    @Test
    void testFilterByDifficulty() throws Exception {
        // Arrange
        Pattern singleton = Pattern.builder()
                .id("singleton")
                .name("单例模式")
                .nameEn("Singleton Pattern")
                .category(PatternCategory.CREATIONAL)
                .difficulty(PatternDifficulty.BEGINNER)
                .definition("保证一个类仅有一个实例")
                .intent("保证一个类仅有一个实例")
                .useCases("当类只能有一个实例时")
                .participants("Singleton, Client")
                .collaboration("Client调用getInstance()")
                .prosCons("优点：受控访问\n缺点：测试不友好")
                .relatedPatterns("工厂方法")
                .springExample("Spring Bean单例")
                .jdkExample("Runtime.getRuntime()")
                .realWorldExample("数据库连接池")
                .mermaidDiagram("classDiagram")
                .codeExample("public class Singleton {}")
                .build();

        when(patternRepository.findByDifficulty(PatternDifficulty.BEGINNER)).thenReturn(List.of(singleton));

        // Act & Assert
        mockMvc.perform(get("/difficulty/BEGINNER"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("patterns"))
                .andExpect(model().attribute("selectedDifficulty", PatternDifficulty.BEGINNER));
    }
}
