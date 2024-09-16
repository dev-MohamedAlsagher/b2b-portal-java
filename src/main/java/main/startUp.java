package main;

import domein.DomeinController;
import gui.AanmeldenController;
import javafx.application.Application;
import javafx.stage.Stage;

public class startUp extends Application {
    private static DomeinController dc;
    private static AanmeldenController start;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dc = new DomeinController();
        start = new AanmeldenController(dc, primaryStage);
        start.start();
    }
}