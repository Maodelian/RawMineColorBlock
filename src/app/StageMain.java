package app;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class StageMain {
    public Compiler compiler;
    public Button button_exportImage;
    public StageMain(Stage stage) {
        stage.setTitle("IC");
        stage.setResizable(false);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Image Compiler");
        Group group;
        {
            Button button_loadImage = new Button("Load Image");
            button_loadImage.setTranslateX(10);
            button_loadImage.setTranslateY(10);
            button_loadImage.setMinWidth(180);
            button_loadImage.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                fileChooser.getExtensionFilters().clear();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("BMP", "*.bmp"));
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    button_exportImage.setDisable(false);
                    compiler = new Compiler(file);
                    compiler.compile();
                }
                else {
                    button_exportImage.setDisable(true);
                }
            });
            button_exportImage = new Button("Export Image");
            button_exportImage.setTranslateX(10);
            button_exportImage.setTranslateY(50);
            button_exportImage.setMinWidth(180);
            button_exportImage.setDisable(true);
            button_exportImage.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                fileChooser.getExtensionFilters().clear();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("RAW Minecraft Blocks Color", "*.rawminecolor"));
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    compiler.save(file);
                }
            });
            group = new Group(button_loadImage, button_exportImage);
        }
        Scene scene = new Scene(group, 200, 90);
        stage.setScene(scene);
        stage.show();
    }
}
