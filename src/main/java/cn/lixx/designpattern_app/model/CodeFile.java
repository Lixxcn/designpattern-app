package cn.lixx.designpattern_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示单个代码文件的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeFile {
    /**
     * 文件名（不含路径）
     */
    private String fileName;

    /**
     * 代码内容
     */
    private String content;
}
