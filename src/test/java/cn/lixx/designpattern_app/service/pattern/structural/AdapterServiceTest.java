package cn.lixx.designpattern_app.service.pattern.structural;

import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AdapterService 单元测试")
class AdapterServiceTest {

    private final AdapterService adapterService = new AdapterService(new CodeReaderUtil());

    @Test
    @DisplayName("应该成功执行适配器模式演示")
    void testExecuteExample() {
        // When
        String output = adapterService.executeExample();

        // Then
        assertThat(output).isNotEmpty();
        assertThat(output).contains("适配器模式演示");
        assertThat(output).contains("通过适配器调用被适配者的方法");
    }

    @Test
    @DisplayName("应该返回代码示例")
    void testGetCodeExample() {
        // When
        String codeExample = adapterService.getCodeExample();

        // Then
        assertThat(codeExample).isNotEmpty();
        assertThat(codeExample).contains("interface Target");
        assertThat(codeExample).contains("class Adaptee");
        assertThat(codeExample).contains("class Adapter");
    }

    @Test
    @DisplayName("应该返回Mermaid类图")
    void testGetMermaidDiagram() {
        // When
        String diagram = adapterService.getMermaidDiagram();

        // Then
        assertThat(diagram).isNotEmpty();
        assertThat(diagram).contains("classDiagram");
        assertThat(diagram).contains("class Target");
        assertThat(diagram).contains("class Adapter");
        assertThat(diagram).contains("class Adaptee");
    }
}
