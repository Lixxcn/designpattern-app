package cn.lixx.designpattern_app.service.pattern.creational;

import cn.lixx.designpattern_app.model.CodeFile;
import cn.lixx.designpattern_app.util.CodeReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SingletonService 单元测试")
class SingletonServiceTest {

    private final SingletonService singletonService = new SingletonService(new CodeReaderUtil());

    @Test
    @DisplayName("应该成功执行单例模式演示")
    void testExecuteExample() {
        // When
        String output = singletonService.executeExample();

        // Then
        assertThat(output).isNotEmpty();
        assertThat(output).contains("单例模式演示");
        assertThat(output).contains("饿汉式单例");
        assertThat(output).contains("懒汉式单例");
        assertThat(output).contains("线程安全懒汉式");
        assertThat(output).contains("静态内部类");
    }

    @Test
    @DisplayName("应该返回代码示例")
    void testGetCodeExample() {
        // When
        List<CodeFile> codeExample = singletonService.getCodeExample();

        // Then
        assertThat(codeExample).isNotEmpty();
        String combinedCode = codeExample.stream()
                .map(CodeFile::getContent)
                .reduce("", (a, b) -> a + b);
        assertThat(combinedCode).contains("public class Singleton");
        assertThat(combinedCode).contains("getInstance()");
        assertThat(combinedCode).contains("饿汉式实现");
        assertThat(combinedCode).contains("懒汉式实现");
        assertThat(combinedCode).contains("双重检查锁");
        assertThat(combinedCode).contains("静态内部类");
    }

    @Test
    @DisplayName("应该返回Mermaid类图")
    void testGetMermaidDiagram() {
        // When
        String diagram = singletonService.getMermaidDiagram();

        // Then
        assertThat(diagram).isNotEmpty();
        assertThat(diagram).contains("classDiagram");
        assertThat(diagram).contains("class Singleton");
        assertThat(diagram).contains("getInstance()");
    }

    @Test
    @DisplayName("代码示例应该包含完整的单例实现")
    void testCodeExampleCompleteness() {
        // When
        List<CodeFile> codeExample = singletonService.getCodeExample();

        // Then
        String combinedCode = codeExample.stream()
                .map(CodeFile::getContent)
                .reduce("", (a, b) -> a + b);
        // 饿汉式
        assertThat(combinedCode).contains("private static final Singleton INSTANCE");
        // 懒汉式
        assertThat(combinedCode).contains("private static LazySingleton instance");
        // 双重检查锁
        assertThat(combinedCode).contains("synchronized");
        assertThat(combinedCode).contains("volatile");
        // 静态内部类
        assertThat(combinedCode).contains("private static class Holder");
    }
}
