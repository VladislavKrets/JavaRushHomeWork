package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.*;

/**
 * Created by lollipop on 17.07.2016.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view)
    {
        this.view = view;

    }

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();

    }
    public void createNewDocument() {
       view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;

    }
    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        chooser.setDialogTitle("Open File");
        int a = chooser.showOpenDialog(view);
        if (a == chooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader reader = new FileReader(currentFile)) {
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.read(reader, document, 0);
            }
            catch (IOException | BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }
    public void saveDocument() {

        if (currentFile != null)
        {
            view.selectHtmlTab();
            try (FileWriter writer = new FileWriter(currentFile);)
            {
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(writer, document, 0, document.getLength());
            }
            catch (IOException | BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
        else saveDocumentAs();
    }
    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        chooser.setDialogTitle("Save File");
        int a = chooser.showSaveDialog(view);
        if (a == chooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter writer = new FileWriter(currentFile);)
            {
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(writer, document, 0, document.getLength());
            }
            catch (IOException | BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }
    public String getPlainText() {
        StringWriter writer = new StringWriter();
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try
        {
            editorKit.write(writer, document, 0, document.getLength());
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }
    public void setPlainText(String text) {

            resetDocument();
            StringReader reader = new StringReader(text);
            HTMLEditorKit editorKit = new HTMLEditorKit();
        try
        {
            editorKit.read(reader, document, 0);
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }
    public void resetDocument() {
        if (document != null)
        {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit editorKit = new HTMLEditorKit();
        document = (HTMLDocument) editorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public HTMLDocument getDocument()
    {
        return document;
    }

    public void init() {
        createNewDocument();
    }
    public void exit() {
        System.exit(0);
    }
}
