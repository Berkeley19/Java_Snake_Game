package com.company;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {


    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int dotSize = 30;
    public static final int appHeight = 30 * dotSize;
    public static final int appWidth = 20 * dotSize;


    private Direction direction = Direction.RIGHT;
    private boolean hasMoved = false;
    private boolean isRunning = false;

    private Timeline timeline = new Timeline();

    private ObservableList<Node> snake;

    private Parent makeGame(){
        Pane root = new Pane();

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(makeGame());
        primaryStage.setTitle("Snake game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
