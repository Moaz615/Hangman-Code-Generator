let editor;
let currentLanguage = 'python';

function setupEditor() {
    editor = CodeMirror.fromTextArea(document.getElementById('code-editor'), {
        mode: 'python',
        theme: 'material-darker',
        lineNumbers: true,
        indentUnit: 4,
        tabSize: 4,
        readOnly: false
    });
}

function loadCode(language) {
    let file, mode;
    if (language === 'python') {
        file = 'hangman/hangman_python.py';
        mode = 'python';
    } else if (language === 'html') {
        file = 'hangman/hangman_html.html';
        mode = 'htmlmixed';
    } else if (language === 'cpp') {
        file = 'hangman/hangman_cpp.cpp';
        mode = 'text/x-c++src';
    } else if (language === 'java') {
        file = 'hangman/hangman_java.java';
        mode = 'text/x-java';
    }
    fetch(file)
        .then(response => response.text())
        .then(code => {
            editor.setOption('mode', mode);
            editor.setValue(code);
            updateLivePreview();
        })
        .catch(err => {
            editor.setValue('# Error loading code.');
            console.error(err);
        });
}

const copyBtn = document.getElementById('copy-btn');
copyBtn.addEventListener('click', () => {
    const code = editor.getValue();
    navigator.clipboard.writeText(code).then(() => {
        copyBtn.textContent = 'Copied!';
        setTimeout(() => (copyBtn.textContent = 'Copy Code'), 1200);
    }).catch(() => {
        alert('Failed to copy code.');
    });
});

const downloadBtn = document.getElementById('download-btn');
downloadBtn.addEventListener('click', () => {
    const code = editor.getValue();
    let ext = currentLanguage === 'html' ? 'html' : currentLanguage === 'cpp' ? 'cpp' : currentLanguage === 'java' ? 'java' : 'py';
    let mime = currentLanguage === 'html' ? 'text/html' : currentLanguage === 'cpp' ? 'text/x-c++src' : currentLanguage === 'java' ? 'text/x-java' : 'text/x-python';
    const blob = new Blob([code], { type: mime });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = `hangman.${ext}`;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
});

const languageSelect = document.getElementById('language');
languageSelect.addEventListener('change', (e) => {
    currentLanguage = e.target.value;
    loadCode(currentLanguage);
    setTimeout(loadFromLocalStorage, 200);
    updateLivePreview();
});

const resetBtn = document.getElementById('reset-btn');
resetBtn.addEventListener('click', () => {
    loadCode(currentLanguage);
    setTimeout(updateLivePreview, 200);
});

const formatBtn = document.getElementById('format-btn');
formatBtn.addEventListener('click', () => {
    let code = editor.getValue();
    // Remove all leading spaces/tabs from each line
    code = code.split('\n').map(line => line.replace(/^\s+/, '')).join('\n');
    editor.setValue(code);
    updateLivePreview();
});

const compareSection = document.getElementById('compare-section');
const compareBtn = document.getElementById('compare-btn');
const closeCompare = document.getElementById('close-compare');
const compareLang1 = document.getElementById('compare-lang1');
const compareLang2 = document.getElementById('compare-lang2');
let compareEditor1, compareEditor2;

function getCompareFileAndMode(language) {
    if (language === 'python') {
        return { file: 'hangman/hangman_python.py', mode: 'python' };
    } else if (language === 'html') {
        return { file: 'hangman/hangman_html.html', mode: 'htmlmixed' };
    } else if (language === 'cpp') {
        return { file: 'hangman/hangman_cpp.cpp', mode: 'text/x-c++src' };
    } else if (language === 'java') {
        return { file: 'hangman/hangman_java.java', mode: 'text/x-java' };
    }
}

function loadCompareCode(editor, language) {
    const { file, mode } = getCompareFileAndMode(language);
    fetch(file)
        .then(response => response.text())
        .then(code => {
            editor.setOption('mode', mode);
            editor.setValue(code);
        })
        .catch(() => {
            editor.setValue('// Error loading code');
        });
}

compareBtn.addEventListener('click', () => {
    compareSection.style.display = '';
    if (!compareEditor1) {
        compareEditor1 = CodeMirror.fromTextArea(document.getElementById('compare-code1'), {
            mode: 'python',
            theme: 'material-darker',
            lineNumbers: true,
            readOnly: true,
            viewportMargin: Infinity,
            lineWrapping: true,
            tabSize: 4
        });
    }
    if (!compareEditor2) {
        compareEditor2 = CodeMirror.fromTextArea(document.getElementById('compare-code2'), {
            mode: 'python',
            theme: 'material-darker',
            lineNumbers: true,
            readOnly: true,
            viewportMargin: Infinity,
            lineWrapping: true,
            tabSize: 4
        });
    }
    loadCompareCode(compareEditor1, compareLang1.value);
    loadCompareCode(compareEditor2, compareLang2.value);
});

closeCompare.addEventListener('click', () => {
    compareSection.style.display = 'none';
});

compareLang1.addEventListener('change', () => {
    if (compareEditor1) loadCompareCode(compareEditor1, compareLang1.value);
});
compareLang2.addEventListener('change', () => {
    if (compareEditor2) loadCompareCode(compareEditor2, compareLang2.value);
});

function updateLivePreview() {
    if (currentLanguage === 'html') {
        document.getElementById('preview-section').style.display = '';
        document.getElementById('preview').srcdoc = editor.getValue();
    } else {
        document.getElementById('preview-section').style.display = 'none';
    }
}

function loadFromLocalStorage() {}

window.addEventListener('DOMContentLoaded', () => {
    setupEditor();
    loadCode(currentLanguage);
    setTimeout(loadFromLocalStorage, 200);
});

const lineCount = document.createElement('div');
lineCount.style.textAlign = 'right';
lineCount.style.fontSize = '0.9em';
lineCount.style.color = '#888';
document.querySelector('.code-section').appendChild(lineCount);

editor.on("change", () => {
  const lines = editor.lineCount();
  const chars = editor.getValue().length;
  lineCount.textContent = `Lines: ${lines} | Chars: ${chars}`;
});
