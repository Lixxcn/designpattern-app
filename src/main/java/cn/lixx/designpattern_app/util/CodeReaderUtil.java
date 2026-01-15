package cn.lixx.designpattern_app.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 代码读取工具类
 * 通过包名扫描并读取该包下的所有 Java 源代码文件
 */
@Component
public class CodeReaderUtil {

    /**
     * 根据包名读取该包下所有 Java 文件的内容
     *
     * @param packageName 包名，如 "cn.lixx.designpattern_app.service.pattern.creational.singleton"
     * @return 所有 Java 文件的内容合并字符串
     */
    public String readCodeFromPackage(String packageName) {
        // 将包名转换为文件路径
        String packagePath = packageName.replace('.', '/');

        // 构建完整的源代码目录路径
        Path sourceDir = Paths.get("src/main/java", packagePath);

        if (!Files.exists(sourceDir)) {
            return "// 包不存在: " + packageName;
        }

        StringBuilder result = new StringBuilder();

        try (Stream<Path> paths = Files.walk(sourceDir)) {
            List<Path> javaFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .sorted(Comparator.naturalOrder())
                    .toList();

            for (Path javaFile : javaFiles) {
                String fileName = javaFile.getFileName().toString();
                try {
                    String content = Files.readString(javaFile);
                    result.append("// ").append(fileName).append("\n");
                    result.append(content).append("\n\n");
                } catch (IOException e) {
                    result.append("// 无法读取文件: ").append(fileName).append("\n\n");
                }
            }

            if (javaFiles.isEmpty()) {
                result.append("// 该包下没有 Java 文件\n");
            }

        } catch (IOException e) {
            return "// 读取包内容时出错: " + e.getMessage();
        }

        return result.toString();
    }

    /**
     * 根据包名读取该包下所有 Java 文件的内容（排除指定类）
     *
     * @param packageName 包名
     * @param excludeClasses 要排除的类名列表
     * @return 所有 Java 文件的内容合并字符串
     */
    public String readCodeFromPackage(String packageName, String... excludeClasses) {
        String packagePath = packageName.replace('.', '/');
        Path sourceDir = Paths.get("src/main/java", packagePath);

        if (!Files.exists(sourceDir)) {
            return "// 包不存在: " + packageName;
        }

        StringBuilder result = new StringBuilder();

        try (Stream<Path> paths = Files.walk(sourceDir)) {
            List<Path> javaFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .filter(p -> {
                        String fileName = p.getFileName().toString();
                        for (String exclude : excludeClasses) {
                            if (fileName.equals(exclude + ".java")) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .sorted(Comparator.naturalOrder())
                    .toList();

            for (Path javaFile : javaFiles) {
                String fileName = javaFile.getFileName().toString();
                try {
                    String content = Files.readString(javaFile);
                    result.append("// ").append(fileName).append("\n");
                    result.append(content).append("\n\n");
                } catch (IOException e) {
                    result.append("// 无法读取文件: ").append(fileName).append("\n\n");
                }
            }

            if (javaFiles.isEmpty()) {
                result.append("// 该包下没有 Java 文件\n");
            }

        } catch (IOException e) {
            return "// 读取包内容时出错: " + e.getMessage();
        }

        return result.toString();
    }
}
