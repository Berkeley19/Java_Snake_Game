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

    /*public enum translateType{
        X, Y
    }*/

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

            switch(direction){
                case UP:
                    tailBecomesHead.setTranslateX(snake.get(0).getTranslateX());
                    tailBecomesHead.setTranslateY(snake.get(0).getTranslateY() - dotSize);
                    break;
                case DOWN:
                    tailBecomesHead.setTranslateX(snake.get(0).getTranslateX());
                    tailBecomesHead.setTranslateY(snake.get(0).getTranslateY() + dotSize);
                    break;
                case RIGHT:
                    tailBecomesHead.setTranslateX(snake.get(0).getTranslateX() + dotSize);
                    tailBecomesHead.setTranslateY(snake.get(0).getTranslateY());
                    break;
                case LEFT:
                    tailBecomesHead.setTranslateX(snake.get(0).getTranslateX() - dotSize);
                    tailBecomesHead.setTranslateY(snake.get(0).getTranslateY());
                    break;
            }
            hasMoved = true;

            if(toRemove)snake.add(0, tailBecomesHead);

            //hits itself restart
            for(Node rect: snake){
                if(rect != tailBecomesHead && tailX == rect.getTranslateX()
                        && tailY == rect.getTranslateY()){
                    restartGame();
                    break;
                }
            }

            //hits edge or goes out of bounds
            if(tailX < 0 || tailX >= appWidth
                || tailY < 0 || tailY >= appHeight){
                restartGame();
            }

            //snake eats food, food goes to another area, snake body enlarges by dot value
            if(tailX == food.getTranslateX() && tailY == food.getTranslateY()){
                food.setTranslateX((int)(Math.random() * (appWidth-dotSize)) / dotSize * dotSize);
                food.setTranslateY((int)(Math.random() * (appHeight-dotSize)) / dotSize * dotSize);

                Rectangle rect = new Rectangle(dotSize, dotSize);
                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);

                snake.add(rect);

            }
            });

        //only one frame, always run
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        //add nodes to appear
        root.getChildren().addAll(food, snakeBody);
        return root;
    }

    public void restartGame(){
        //stopGame function
        //startGame function
    }

    public void stopGame(){

    }

    public void startGame(){
        
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
