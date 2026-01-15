package cn.lixx.designpattern_app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cn.lixx.designpattern_app.service.pattern.creational.SingletonService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExecutionController.class)
class ExecutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SingletonService singletonService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.creational.FactoryMethodService factoryMethodService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.creational.AbstractFactoryService abstractFactoryService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.creational.BuilderService builderService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.creational.PrototypeService prototypeService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.AdapterService adapterService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.BridgeService bridgeService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.CompositeService compositeService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.DecoratorService decoratorService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.FacadeService facadeService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.FlyweightService flyweightService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.structural.ProxyService proxyService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.ChainService chainService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.CommandService commandService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.IteratorService iteratorService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.MediatorService mediatorService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.MementoService mementoService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.ObserverService observerService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.StateService stateService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.StrategyService strategyService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.TemplateService templateService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.VisitorService visitorService;

    @MockBean
    private cn.lixx.designpattern_app.service.pattern.behavioral.InterpreterService interpreterService;

    @Test
    void testExecuteSingletonPattern() throws Exception {
        // Arrange
        String expectedOutput = "=== 单例模式演示 ===\n\n饿汉式单例: cn.lixx.designpattern_app.service.pattern.creational.singleton.EagerSingleton@";
        when(singletonService.executeExample()).thenReturn(expectedOutput);

        // Act & Assert
        mockMvc.perform(get("/execute/singleton"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.output").exists());
    }

    @Test
    void testExecuteFactoryMethodPattern() throws Exception {
        // Arrange
        String expectedOutput = "=== 工厂方法模式演示 ===";
        when(factoryMethodService.executeExample()).thenReturn(expectedOutput);

        // Act & Assert
        mockMvc.perform(get("/execute/factory-method"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.output").exists());
    }

    @Test
    void testExecuteObserverPattern() throws Exception {
        // Arrange
        String expectedOutput = "=== 观察者模式演示 ===";
        when(observerService.executeExample()).thenReturn(expectedOutput);

        // Act & Assert
        mockMvc.perform(get("/execute/observer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.output").exists());
    }

    @Test
    void testExecuteUnknownPattern() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/execute/unknown-pattern"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.output").value("未找到模式: unknown-pattern"));
    }
}
