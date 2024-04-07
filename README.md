# OOP-project
An UML editor based on the concept of object-oriented programming.


## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

```
OOP-project
├─ .vscode
├─ README.md
├─ lib
└─ src
   ├─ canvas
   │  ├─ MyCanvas.java
   │  ├─ btnAction
   │  │  ├─ ButtonAction.java
   │  │  ├─ CreateFigureAction.java
   │  │  ├─ CreateLineAction.java
   │  │  └─ SelectAction.java
   │  ├─ line
   │  │  ├─ AssociateLine.java
   │  │  ├─ CompositionLine.java
   │  │  ├─ GeneralizationLine.java
   │  │  └─ Line.java
   │  └─ shape
   │     ├─ ClassFigure.java
   │     ├─ Figure.java
   │     ├─ GroupFigure.java
   │     ├─ Port.java
   │     └─ UseCaseFigure.java
   ├─ init
   │  ├─ MyFrame.java
   │  └─ MyUmlBase.java
   ├─ menu
   │  ├─ Menu.java
   │  ├─ MenuItem.java
   │  └─ menuAction
   │     ├─ CloseWindowAction.java
   │     ├─ GroupAction.java
   │     ├─ MenuAction.java
   │     ├─ OpenNewCanvasAction.java
   │     ├─ RenameObjectAction.java
   │     ├─ SaveAsAction.java
   │     └─ UnGroupAction.java
   └─ toolbar
      ├─ FunctionBtn.java
      ├─ MyToolbar.java
      └─ btnImg
         ├─ assoLine.png
         ├─ assoLine_antiwhite.png
         ├─ class.png
         ├─ class_antiwhite.png
         ├─ compo.png
         ├─ compo_antiwhite.png
         ├─ general.png
         ├─ general_antiwhite.png
         ├─ select.png
         ├─ select_antiwhite.png
         ├─ usecase.png
         └─ usecase_antiwhite.png
```