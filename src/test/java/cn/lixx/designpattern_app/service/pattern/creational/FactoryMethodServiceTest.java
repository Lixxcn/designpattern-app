package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.model.CodeFile;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FactoryMethodService 单元测试")
class FactoryMethodServiceTest {

    private final FactoryMethodService factoryMethodService = new FactoryMethodService(new CodeReaderUtil());

    @Test
    @DisplayName("应该成功执行工厂方法模式演示")
    void testExecuteExample() {
        // When
        String output = factoryMethodService.executeExample();

        // Then
        assertThat(output).isNotEmpty();
        assertThat(output).contains("工厂方法模式演示");
        assertThat(output).contains("使用工厂A创建产品");
        assertThat(output).contains("使用工厂B创建产品");
    }

    @Test
    @DisplayName("应该返回代码示例")
    void testGetCodeExample() {
        // When
        List<CodeFile> codeExample = factoryMethodService.getCodeExample();

        // Then
        assertThat(codeExample).isNotEmpty();
        String combinedCode = codeExample.stream()
                .map(CodeFile::getContent)
                .reduce("", (a, b) -> a + b);
        assertThat(combinedCode).contains("interface Product");
        assertThat(combinedCode).contains("interface Factory");
        assertThat(combinedCode).contains("createProduct()");
    }

    @Test
    @DisplayName("应该返回Mermaid类图")
    void testGetMermaidDiagram() {
        // When
        String diagram = factoryMethodService.getMermaidDiagram();

        // Then
        assertThat(diagram).isNotEmpty();
        assertThat(diagram).contains("classDiagram");
        assertThat(diagram).contains("class Product");
        assertThat(diagram).contains("class Factory");
    }
}
