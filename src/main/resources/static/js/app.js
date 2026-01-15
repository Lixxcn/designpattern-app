// 应用全局配置和工具函数

console.log('设计模式学习应用已加载');

// 工具函数：格式化代码
function formatCode(code) {
    return code;
}

// 工具函数：执行完成后显示结果
function showOutput(output) {
    const outputDiv = document.getElementById('output');
    if (outputDiv) {
        outputDiv.textContent = output;
        outputDiv.classList.remove('hidden');
    }
}
