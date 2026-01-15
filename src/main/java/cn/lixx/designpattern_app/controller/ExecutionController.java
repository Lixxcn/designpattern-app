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
    private final BridgeService bridgeService;
    private final CompositeService compositeService;
    private final FacadeService facadeService;
    private final FlyweightService flyweightService;
    private final CommandService commandService;
    private final IteratorService iteratorService;
    private final MediatorService mediatorService;
    private final MementoService mementoService;
    private final StateService stateService;
    private final VisitorService visitorService;
    private final InterpreterService interpreterService;

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
            TemplateService templateService,
            BridgeService bridgeService,
            CompositeService compositeService,
            FacadeService facadeService,
            FlyweightService flyweightService,
            CommandService commandService,
            IteratorService iteratorService,
            MediatorService mediatorService,
            MementoService mementoService,
            StateService stateService,
            VisitorService visitorService,
            InterpreterService interpreterService) {
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
        this.bridgeService = bridgeService;
        this.compositeService = compositeService;
        this.facadeService = facadeService;
        this.flyweightService = flyweightService;
        this.commandService = commandService;
        this.iteratorService = iteratorService;
        this.mediatorService = mediatorService;
        this.mementoService = mementoService;
        this.stateService = stateService;
        this.visitorService = visitorService;
        this.interpreterService = interpreterService;
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
            case "bridge" -> bridgeService.executeExample();
            case "composite" -> compositeService.executeExample();
            case "decorator" -> decoratorService.executeExample();
            case "facade" -> facadeService.executeExample();
            case "flyweight" -> flyweightService.executeExample();
            case "proxy" -> proxyService.executeExample();
            // 行为型模式
            case "chain" -> chainService.executeExample();
            case "command" -> commandService.executeExample();
            case "interpreter" -> interpreterService.executeExample();
            case "iterator" -> iteratorService.executeExample();
            case "mediator" -> mediatorService.executeExample();
            case "memento" -> mementoService.executeExample();
            case "observer" -> observerService.executeExample();
            case "state" -> stateService.executeExample();
            case "strategy" -> strategyService.executeExample();
            case "template" -> templateService.executeExample();
            case "visitor" -> visitorService.executeExample();
            default -> "未找到模式: " + patternId;
        };
        return Map.of("output", output);
    }
}
