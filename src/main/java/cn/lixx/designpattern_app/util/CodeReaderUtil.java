package cn.lixx.designpattern_app.util;

import cn.lixx.designpattern_app.model.CodeFile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
     * @return 所有 Java 文件的内容列表
     */
    public List<CodeFile> readCodeFromPackage(String packageName) {
        return readCodeFromPackage(packageName, new String[0]);
    }

    /**
     * 根据包名读取该包下所有 Java 文件的内容（排除指定类）
     *
     * @param packageName 包名
     * @param excludeClasses 要排除的类名列表
     * @return 所有 Java 文件的内容列表
     */
    public List<CodeFile> readCodeFromPackage(String packageName, String... excludeClasses) {
        String packagePath = packageName.replace('.', '/');
        Path sourceDir = Paths.get("src/main/java", packagePath);

        List<CodeFile> result = new ArrayList<>();

        if (!Files.exists(sourceDir)) {
            result.add(CodeFile.builder()
                    .fileName("// 错误")
                    .content("// 包不存在: " + packageName)
                    .build());
            return result;
        }

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
                    result.add(CodeFile.builder()
                            .fileName(fileName)
                            .content(content)
                            .build());
                } catch (IOException e) {
                    result.add(CodeFile.builder()
                            .fileName(fileName)
                            .content("// 无法读取文件: " + fileName)
                            .build());
                }
            }

            if (javaFiles.isEmpty()) {
                result.add(CodeFile.builder()
                        .fileName("// 提示")
                        .content("// 该包下没有 Java 文件")
                        .build());
            }

        } catch (IOException e) {
            result.add(CodeFile.builder()
                    .fileName("// 错误")
                    .content("// 读取包内容时出错: " + e.getMessage())
                    .build());
        }

        return result;
    }
}
