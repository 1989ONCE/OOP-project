# OOP-project
An UML editor based on the concept of object-oriented programming.

![uml](https://github.com/1989ONCE/OOP-project/assets/92381825/83aabb31-ef21-4eaf-b141-65c65cbb34b5)



# App Installer:
Users can download my Java App with these installer, and run it on your own computer!

MacOS: [UMLeditor-1.0.pkg.zip](https://drive.google.com/uc?export=download&id=1AZZnhmmU1qI3YFeHj3xBx7E31b292cIH)

Windows:

(thanks to this [tutorial](https://centerkey.com/mac/java/), it really helps me a lot~)

# Project Introduction
[Canvas PPT](https://www.canva.com/design/DAGBu9GFLZs/DWc_viJ2ZSE7Ph-LJ8OZjw/view?utm_content=DAGBu9GFLZs&utm_campaign=designshare&utm_medium=link&utm_source=editor)

[Use Case Demo Videos](https://drive.google.com/drive/folders/1SElxxMVZs0fi8iVZ7WNX5i9h1zRFODqI?usp=drive_link)

## Folder Structure(Dependency Management)

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
