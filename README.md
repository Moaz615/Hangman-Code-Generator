# Hangman Code Generator

## Overview

**Hangman Code Generator** is a web application that allows you to generate, view, edit, copy, and download Hangman game code in your favorite programming language. It supports Python, HTML/JavaScript, C++, and Java. You can also compare implementations side-by-side and preview HTML code live.

---

## Features

- **Language Selection:** Choose between Python, HTML, C++, and Java.
- **Code Editor:** Edit code with syntax highlighting using CodeMirror.
- **Copy & Download:** Easily copy code to clipboard or download as a file.
- **Reset:** Restore the default Hangman code for the selected language.
- **Format:** Remove all leading indentation from each line for cleaner code.
- **Compare:** View and compare two languages' Hangman implementations side-by-side.
- **Live Preview:** Instantly preview HTML code in an embedded iframe.

---

## How to Use

1. **Visit the app**: In your browser, open [Hangman Code Generator](hangman-code-generator.moaz.site)
2. **Select a Language:** Use the dropdown to choose your desired programming language.
3. **Edit Code:** Modify the code in the editor as needed.
4. **Copy or Download:** Use the buttons to copy the code or download it as a file.
5. **Reset Code:** Click "Reset" to restore the original Hangman code for the selected language.
6. **Format Code:** Click "Format" to remove all leading spaces/tabs from each line.
7. **Compare Languages:** Click "Compare" to open the comparison section and select two languages to view their code side-by-side.
8. **Live HTML Preview:** When HTML is selected, view the live output in the preview section.

---

## File Structure

```
Hangman Code Generator/
│
├── index.html         # Main HTML file
├── style.css          # Stylesheet
├── script.js          # Main JavaScript logic
├── hangman/
│   ├── hangman_python.py
│   ├── hangman_html.html
│   ├── hangman_cpp.cpp
│   └── hangman_java.java
```

---

## Dependencies

- [CodeMirror](https://codemirror.net/) for code editing.
- [js-beautify](https://cdnjs.com/libraries/js-beautify) (optional, for HTML formatting).
- Google Fonts (Fira Mono, Roboto).

All dependencies are loaded via CDN in `index.html`.

---

## Customization

- To add more languages, update the language dropdowns in `index.html` and add corresponding code files in the `hangman/` folder.
- You can modify the default code templates in the `hangman/` folder.

---

## License

This project is for educational purposes.  
Feel free to use, modify, and
