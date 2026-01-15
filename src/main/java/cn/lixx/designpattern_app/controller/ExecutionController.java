package cn.lixx.designpattern_app.controller;

import cn.lixx.designpattern_app.service.pattern.behavioral.*;
import cn.lixx.designpattern_app.service.pattern.creational.*;
import cn.lixx.designpattern_app.service.pattern.structural.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/execute")
public class ExecutionController {

    private final SingletonService singletonService;
    private final FactoryMethodService factoryMethodService;
    private final AbstractFactoryService abstractFactoryService;
    private final BuilderService builderService;
    private final PrototypeService prototypeService;
    private final AdapterService adapterService;
    private final DecoratorService decoratorService;
    private final ProxyService proxyService;
    private final ObserverService observerService;
    private final StrategyService strategyService;
    private final ChainService chainService;
    private final TemplateService templateService;

    public ExecutionController(
            SingletonService singletonService,
            FactoryMethodService factoryMethodService,
            AbstractFactoryService abstractFactoryService,
            BuilderService builderService,
            PrototypeService prototypeService,
            AdapterService adapterService,
            DecoratorService decoratorService,
            ProxyService proxyService,
            ObserverService observerService,
            StrategyService strategyService,
            ChainService chainService,
            TemplateService templateService) {
        this.singletonService = singletonService;
        this.factoryMethodService = factoryMethodService;
        this.abstractFactoryService = abstractFactoryService;
        this.builderService = builderService;
        this.prototypeService = prototypeService;
        this.adapterService = adapterService;
        this.decoratorService = decoratorService;
        this.proxyService = proxyService;
        this.observerService = observerService;
        this.strategyService = strategyService;
        this.chainService = chainService;
        this.templateService = templateService;
    }

    @GetMapping("/{patternId}")
    public Map<String, String> execute(@PathVariable String patternId) {
        String output = switch (patternId) {
            // 创建型模式
            case "singleton" -> singletonService.executeExample();
            case "factory-method" -> factoryMethodService.executeExample();
            case "abstract-factory" -> abstractFactoryService.executeExample();
            case "builder" -> builderService.executeExample();
            case "prototype" -> prototypeService.executeExample();
            // 结构型模式
            case "adapter" -> adapterService.executeExample();
            case "decorator" -> decoratorService.executeExample();
            case "proxy" -> proxyService.executeExample();
            // 行为型模式
            case "observer" -> observerService.executeExample();
            case "strategy" -> strategyService.executeExample();
            case "chain" -> chainService.executeExample();
            case "template" -> templateService.executeExample();
            default -> "未找到模式: " + patternId;
        };
        return Map.of("output", output);
    }
}
