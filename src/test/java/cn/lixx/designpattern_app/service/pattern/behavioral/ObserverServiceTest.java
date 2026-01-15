package cn.lixx.designpattern_app.service.pattern.behavioral;

import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ObserverService 单元测试")
class ObserverServiceTest {

    private final ObserverService observerService = new ObserverService(new CodeReaderUtil());

    @Test
    @DisplayName("应该成功执行观察者模式演示")
    void testExecuteExample() {
        // When
        String output = observerService.executeExample();

        // Then
        assertThat(output).isNotEmpty();
        assertThat(output).contains("观察者模式演示");
        assertThat(output).contains("发布第一条新闻");
        assertThat(output).contains("订阅者");
    }

    @Test
    @DisplayName("应该返回代码示例")
    void testGetCodeExample() {
        // When
        String codeExample = observerService.getCodeExample();

        // Then
        assertThat(codeExample).isNotEmpty();
        assertThat(codeExample).contains("interface Observer");
        assertThat(codeExample).contains("abstract class Subject");
        assertThat(codeExample).contains("attach(");
        assertThat(codeExample).contains("detach(");
    }

    @Test
    @DisplayName("应该返回Mermaid类图")
    void testGetMermaidDiagram() {
        // When
        String diagram = observerService.getMermaidDiagram();

        // Then
        assertThat(diagram).isNotEmpty();
        assertThat(diagram).contains("classDiagram");
        assertThat(diagram).contains("class Observer");
        assertThat(diagram).contains("class Subject");
    }
}
