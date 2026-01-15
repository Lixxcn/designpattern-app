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

// 创建型模式 Service
import cn.lixx.designpattern_app.service.pattern.creational.SingletonService;
import cn.lixx.designpattern_app.service.pattern.creational.FactoryMethodService;
import cn.lixx.designpattern_app.service.pattern.creational.AbstractFactoryService;
import cn.lixx.designpattern_app.service.pattern.creational.BuilderService;
import cn.lixx.designpattern_app.service.pattern.creational.PrototypeService;

// 结构型模式 Service
import cn.lixx.designpattern_app.service.pattern.structural.AdapterService;
import cn.lixx.designpattern_app.service.pattern.structural.BridgeService;
import cn.lixx.designpattern_app.service.pattern.structural.CompositeService;
import cn.lixx.designpattern_app.service.pattern.structural.DecoratorService;
import cn.lixx.designpattern_app.service.pattern.structural.FacadeService;
import cn.lixx.designpattern_app.service.pattern.structural.FlyweightService;
import cn.lixx.designpattern_app.service.pattern.structural.ProxyService;

// 行为型模式 Service
import cn.lixx.designpattern_app.service.pattern.behavioral.ChainService;
import cn.lixx.designpattern_app.service.pattern.behavioral.CommandService;
import cn.lixx.designpattern_app.service.pattern.behavioral.InterpreterService;
import cn.lixx.designpattern_app.service.pattern.behavioral.IteratorService;
import cn.lixx.designpattern_app.service.pattern.behavioral.MediatorService;
import cn.lixx.designpattern_app.service.pattern.behavioral.MementoService;
import cn.lixx.designpattern_app.service.pattern.behavioral.ObserverService;
import cn.lixx.designpattern_app.service.pattern.behavioral.StateService;
import cn.lixx.designpattern_app.service.pattern.behavioral.StrategyService;
import cn.lixx.designpattern_app.service.pattern.behavioral.TemplateService;
import cn.lixx.designpattern_app.service.pattern.behavioral.VisitorService;

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

    // 创建型模式 Service MockBeans
    @MockBean
    private SingletonService singletonService;

    @MockBean
    private FactoryMethodService factoryMethodService;

    @MockBean
    private AbstractFactoryService abstractFactoryService;

    @MockBean
    private BuilderService builderService;

    @MockBean
    private PrototypeService prototypeService;

    // 结构型模式 Service MockBeans
    @MockBean
    private AdapterService adapterService;

    @MockBean
    private BridgeService bridgeService;

    @MockBean
    private CompositeService compositeService;

    @MockBean
    private DecoratorService decoratorService;

    @MockBean
    private FacadeService facadeService;

    @MockBean
    private FlyweightService flyweightService;

    @MockBean
    private ProxyService proxyService;

    // 行为型模式 Service MockBeans
    @MockBean
    private ChainService chainService;

    @MockBean
    private CommandService commandService;

    @MockBean
    private InterpreterService interpreterService;

    @MockBean
    private IteratorService iteratorService;

    @MockBean
    private MediatorService mediatorService;

    @MockBean
    private MementoService mementoService;

    @MockBean
    private ObserverService observerService;

    @MockBean
    private StateService stateService;

    @MockBean
    private StrategyService strategyService;

    @MockBean
    private TemplateService templateService;

    @MockBean
    private VisitorService visitorService;

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
