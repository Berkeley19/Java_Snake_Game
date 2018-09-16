package com.company;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import java.awt.*;


public class Main extends Application {


    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int dotSize = 30;
    public static final int appHeight = 30 * dotSize;
    public static final int appWidth = 20 * dotSize;
    public static double difficulty = 0.2;


    private Direction direction = Direction.RIGHT;
    private boolean hasMoved = false;
    private boolean isRunning = false;

    private Timeline timeline = new Timeline();

    private ObservableList<Node> snake;

    private Parent makeGame(){
        Pane root = new Pane();
        root.setPrefSize(appWidth, appHeight);

        Group snakeBody = new Group();
        snake = snakeBody.getChildren();

        Rectangle food = new Rectangle(dotSize, dotSize);
        food.setFill(Color.RED);
        food.setTranslateX((int)(Math.random() * (appWidth-dotSize)) / dotSize * dotSize);
        food.setTranslateY((int)(Math.random() * (appHeight-dotSize)) / dotSize * dotSize);

        KeyFrame frame = new KeyFrame(Duration.seconds(difficulty), event -> {

            if(!isRunning){
                return;
            }

            boolean toRemove = snake.size() > 1;

            Node tailBecomesHead = toRemove ? snake.remove(snake.size()-1) : snake.get(0);

            double tailX = tailBecomesHead.getTranslateX();
            double tailY = tailBecomesHead.getTranslateY();

            
        });


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
