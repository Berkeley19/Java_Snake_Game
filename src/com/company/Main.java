package com.company;

import javafx.application.Application;
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
    private boolean moved = false;
    private boolean running = false;




    private Parent makePage(){
        Pane root = new Pane();

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(makePage());
        primaryStage.setTitle("Snake game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
