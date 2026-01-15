package cn.lixx.designpattern_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Pattern {
    private String id;
    private String name;
    private String nameEn;
    private PatternCategory category;
    private PatternDifficulty difficulty;
    private String definition;
    private String intent;
    private String useCases;
    private String participants;
    private String collaboration;
    private String prosCons;
    private String relatedPatterns;
    private String springExample;
    private String jdkExample;
    private String realWorldExample;
    // 这些字段现在由 Controller 从 Service 动态获取
    private transient List<CodeFile> codeExample;
    private transient String mermaidDiagram;
}
