package cn.lixx.designpattern_app.controller;

import cn.lixx.designpattern_app.model.CodeFile;
import cn.lixx.designpattern_app.model.Pattern;
import cn.lixx.designpattern_app.model.PatternCategory;
import cn.lixx.designpattern_app.model.PatternDifficulty;
import cn.lixx.designpattern_app.repository.PatternRepository;

import java.util.List;
import cn.lixx.designpattern_app.service.pattern.behavioral.*;
import cn.lixx.designpattern_app.service.pattern.creational.*;
import cn.lixx.designpattern_app.service.pattern.structural.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatternController {
    private final PatternRepository patternRepository;

    // 创建型模式 Services
    private final SingletonService singletonService;
    private final FactoryMethodService factoryMethodService;
    private final AbstractFactoryService abstractFactoryService;
    private final BuilderService builderService;
    private final PrototypeService prototypeService;

    // 结构型模式 Services
    private final AdapterService adapterService;
    private final BridgeService bridgeService;
    private final CompositeService compositeService;
    private final DecoratorService decoratorService;
    private final FacadeService facadeService;
    private final FlyweightService flyweightService;
    private final ProxyService proxyService;

    // 行为型模式 Services
    private final ChainService chainService;
    private final CommandService commandService;
    private final InterpreterService interpreterService;
    private final IteratorService iteratorService;
    private final MediatorService mediatorService;
    private final MementoService mementoService;
    private final ObserverService observerService;
    private final StateService stateService;
    private final StrategyService strategyService;
    private final TemplateService templateService;
    private final VisitorService visitorService;

    public PatternController(
            PatternRepository patternRepository,
            SingletonService singletonService,
            FactoryMethodService factoryMethodService,
            AbstractFactoryService abstractFactoryService,
            BuilderService builderService,
            PrototypeService prototypeService,
            AdapterService adapterService,
            BridgeService bridgeService,
            CompositeService compositeService,
            DecoratorService decoratorService,
            FacadeService facadeService,
            FlyweightService flyweightService,
            ProxyService proxyService,
            ChainService chainService,
            CommandService commandService,
            InterpreterService interpreterService,
            IteratorService iteratorService,
            MediatorService mediatorService,
            MementoService mementoService,
            ObserverService observerService,
            StateService stateService,
            StrategyService strategyService,
            TemplateService templateService,
            VisitorService visitorService) {
        this.patternRepository = patternRepository;
        this.singletonService = singletonService;
        this.factoryMethodService = factoryMethodService;
        this.abstractFactoryService = abstractFactoryService;
        this.builderService = builderService;
        this.prototypeService = prototypeService;
        this.adapterService = adapterService;
        this.bridgeService = bridgeService;
        this.compositeService = compositeService;
        this.decoratorService = decoratorService;
        this.facadeService = facadeService;
        this.flyweightService = flyweightService;
        this.proxyService = proxyService;
        this.chainService = chainService;
        this.commandService = commandService;
        this.interpreterService = interpreterService;
        this.iteratorService = iteratorService;
        this.mediatorService = mediatorService;
        this.mementoService = mementoService;
        this.observerService = observerService;
        this.stateService = stateService;
        this.strategyService = strategyService;
        this.templateService = templateService;
        this.visitorService = visitorService;
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
                    // 动态获取代码示例和类图
                    pattern.setCodeExample(getCodeExample(id));
                    pattern.setMermaidDiagram(getMermaidDiagram(id));
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

    /**
     * 根据模式 ID 获取代码示例
     */
    private List<CodeFile> getCodeExample(String patternId) {
        return switch (patternId) {
            // 创建型模式
            case "singleton" -> singletonService.getCodeExample();
            case "factory-method" -> factoryMethodService.getCodeExample();
            case "abstract-factory" -> abstractFactoryService.getCodeExample();
            case "builder" -> builderService.getCodeExample();
            case "prototype" -> prototypeService.getCodeExample();
            // 结构型模式
            case "adapter" -> adapterService.getCodeExample();
            case "bridge" -> bridgeService.getCodeExample();
            case "composite" -> compositeService.getCodeExample();
            case "decorator" -> decoratorService.getCodeExample();
            case "facade" -> facadeService.getCodeExample();
            case "flyweight" -> flyweightService.getCodeExample();
            case "proxy" -> proxyService.getCodeExample();
            // 行为型模式
            case "chain" -> chainService.getCodeExample();
            case "command" -> commandService.getCodeExample();
            case "interpreter" -> interpreterService.getCodeExample();
            case "iterator" -> iteratorService.getCodeExample();
            case "mediator" -> mediatorService.getCodeExample();
            case "memento" -> mementoService.getCodeExample();
            case "observer" -> observerService.getCodeExample();
            case "state" -> stateService.getCodeExample();
            case "strategy" -> strategyService.getCodeExample();
            case "template" -> templateService.getCodeExample();
            case "visitor" -> visitorService.getCodeExample();
            default -> List.of(new CodeFile("// 提示", "// 未找到代码示例"));
        };
    }

    /**
     * 根据模式 ID 获取 Mermaid 类图
     */
    private String getMermaidDiagram(String patternId) {
        return switch (patternId) {
            // 创建型模式
            case "singleton" -> singletonService.getMermaidDiagram();
            case "factory-method" -> factoryMethodService.getMermaidDiagram();
            case "abstract-factory" -> abstractFactoryService.getMermaidDiagram();
            case "builder" -> builderService.getMermaidDiagram();
            case "prototype" -> prototypeService.getMermaidDiagram();
            // 结构型模式
            case "adapter" -> adapterService.getMermaidDiagram();
            case "bridge" -> bridgeService.getMermaidDiagram();
            case "composite" -> compositeService.getMermaidDiagram();
            case "decorator" -> decoratorService.getMermaidDiagram();
            case "facade" -> facadeService.getMermaidDiagram();
            case "flyweight" -> flyweightService.getMermaidDiagram();
            case "proxy" -> proxyService.getMermaidDiagram();
            // 行为型模式
            case "chain" -> chainService.getMermaidDiagram();
            case "command" -> commandService.getMermaidDiagram();
            case "interpreter" -> interpreterService.getMermaidDiagram();
            case "iterator" -> iteratorService.getMermaidDiagram();
            case "mediator" -> mediatorService.getMermaidDiagram();
            case "memento" -> mementoService.getMermaidDiagram();
            case "observer" -> observerService.getMermaidDiagram();
            case "state" -> stateService.getMermaidDiagram();
            case "strategy" -> strategyService.getMermaidDiagram();
            case "template" -> templateService.getMermaidDiagram();
            case "visitor" -> visitorService.getMermaidDiagram();
            default -> "classDiagram\n    // 未找到类图";
        };
    }
}
