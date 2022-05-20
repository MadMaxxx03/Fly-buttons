package com.example.classwork;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        final double[] scene_size = new double[]{600, 500};

        Group group = new Group();
        Scene scene = new Scene(group, scene_size[0], scene_size[1]);

        final int len = 80;

        Button button1 = new Button("b1");
        button1.setMinSize(len, len);
        button1.setLayoutX(50);
        button1.setLayoutY(50);
        group.getChildren().add(button1);

        Button button2 = new Button("b2");
        button2.setMinSize(len, len);
        button2.setLayoutX(200);
        button2.setLayoutY(200);
        group.getChildren().add(button2);
        final double[] delta_coords2 = new double[]{0,0};

        final double[] button1Coords = new double[4];
        double[] Coords = new double[]{50, 50, 200, 200};
        final double[] time1 = new double[4];
        final double[] delta_coords1 = new double[]{0,0};
        button1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button1Coords[0] = mouseEvent.getX();
                button1Coords[1] = mouseEvent.getY();
                button1Coords[2] = mouseEvent.getSceneX();
                button1Coords[3] = mouseEvent.getSceneY();
                time1[0] = System.currentTimeMillis();
            }
        });
        button1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button1.setLayoutX(mouseEvent.getSceneX() - button1Coords[0]);
                button1.setLayoutY(mouseEvent.getSceneY() - button1Coords[1]);
            }
        });

        button1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                time1[1] = System.currentTimeMillis();
                time1[2] = 1;
                time1[3] = time1[1] - time1[0];
                delta_coords1[0] = (mouseEvent.getSceneX() - button1Coords[2]) / (time1[3]);
                delta_coords1[1] = (mouseEvent.getSceneY() - button1Coords[3]) / (time1[3]);
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        button1.setLayoutX(button1.getLayoutX() + delta_coords1[0]);
                        button1.setLayoutY(button1.getLayoutY() + delta_coords1[1]);

                        if (button1.getLayoutX() > scene_size[0] - len | button1.getLayoutX() <= 0){
                            delta_coords1[0] = -delta_coords1[0];
                        }
                        if (button1.getLayoutY() >= scene_size[1] - len | button1.getLayoutY() <= 0){
                            delta_coords1[1] = -delta_coords1[1];
                        }

                        button2.setLayoutX(button2.getLayoutX() + delta_coords2[0]);
                        button2.setLayoutY(button2.getLayoutY() + delta_coords2[1]);

                        if (button2.getLayoutX() > scene_size[0] - len | button2.getLayoutX() <= 0){
                            delta_coords2[0] = -delta_coords2[0];
                        }
                        if (button2.getLayoutY() >= scene_size[1] - len | button2.getLayoutY() <= 0){
                            delta_coords2[1] = -delta_coords2[1];
                        }

                        double crossx = Math.abs(button1.getLayoutX() - button2.getLayoutX());
                        double crossy = Math.abs(button1.getLayoutY() - button2.getLayoutY());
                        if (crossx <= len & crossy <= len){
                            if (delta_coords1[0] == 0 & delta_coords1[1] == 0){
                                delta_coords1[0] = delta_coords2[0] * 0.7;
                                delta_coords1[1] = delta_coords2[1] * 0.7;
                                delta_coords2[0] = 0.3 * -delta_coords2[0];
                                delta_coords2[1] = 0.3 * -delta_coords2[1];
                            }
                            else if (delta_coords2[0] == 0 & delta_coords2[1] == 0){
                                delta_coords2[0] = delta_coords1[0] * 0.7;
                                delta_coords2[1] = delta_coords1[1] * 0.7;
                                delta_coords1[0] = 0.3 * -delta_coords1[0];
                                delta_coords1[1] = 0.3 * -delta_coords1[1];
                            }
                            else {
                                if (Math.pow(delta_coords1[0],2) + Math.pow(delta_coords1[0],2) >= Math.pow(delta_coords2[0],2) + Math.pow(delta_coords2[0],2)){
                                    delta_coords2[0] = delta_coords1[0] * 0.7;
                                    delta_coords2[1] = delta_coords1[1] * 0.7;
                                    delta_coords1[0] = 0.3 * -delta_coords1[0];
                                    delta_coords1[1] = 0.3 * -delta_coords1[1];
                                }
                                else{
                                    delta_coords1[0] = delta_coords2[0] * 0.7;
                                    delta_coords1[1] = delta_coords2[1] * 0.7;
                                    delta_coords2[0] = 0.3 * -delta_coords2[0];
                                    delta_coords2[1] = 0.3 * -delta_coords2[1];
                                }
                            }
                        }
                    }
                };
                timer.start();
            }
        });

        final double[] button2Coords = new double[4];
        final double[] time2 = new double[4];
        button2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button2Coords[0] = mouseEvent.getX();
                button2Coords[1] = mouseEvent.getY();
                button2Coords[2] = mouseEvent.getSceneX();
                button2Coords[3] = mouseEvent.getSceneY();
                time2[0] = System.currentTimeMillis();
            }
        });
        button2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button2.setLayoutX(mouseEvent.getSceneX() - button2Coords[0]);
                button2.setLayoutY(mouseEvent.getSceneY() - button2Coords[1]);
            }
        });

        button2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                time2[1] = System.currentTimeMillis();
                time2[2] = 1;
                time2[3] = time2[1] - time2[0];
                delta_coords2[0] = (mouseEvent.getSceneX() - button2Coords[2]) / (time2[3]);
                delta_coords2[1] = (mouseEvent.getSceneY() - button2Coords[3]) / (time2[3]);
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        button1.setLayoutX(button1.getLayoutX() + delta_coords1[0]);
                        button1.setLayoutY(button1.getLayoutY() + delta_coords1[1]);

                        if (button1.getLayoutX() > scene_size[0] - len | button1.getLayoutX() <= 0){
                            delta_coords1[0] = -delta_coords1[0];
                        }
                        if (button1.getLayoutY() >= scene_size[1] - len | button1.getLayoutY() <= 0){
                            delta_coords1[1] = -delta_coords1[1];
                        }

                        button2.setLayoutX(button2.getLayoutX() + delta_coords2[0]);
                        button2.setLayoutY(button2.getLayoutY() + delta_coords2[1]);

                        if (button2.getLayoutX() > scene_size[0] - len | button2.getLayoutX() <= 0){
                            delta_coords2[0] = -delta_coords2[0];
                        }
                        if (button2.getLayoutY() >= scene_size[1] - len | button2.getLayoutY() <= 0){
                            delta_coords2[1] = -delta_coords2[1];
                        }

                        double crossx = Math.abs(button1.getLayoutX() - button2.getLayoutX());
                        double crossy = Math.abs(button1.getLayoutY() - button2.getLayoutY());
                        if (crossx <= len& crossy <= len){
                            if (delta_coords1[0] == 0 & delta_coords1[1] == 0){
                                delta_coords1[0] = delta_coords2[0] * 0.7;
                                delta_coords1[1] = delta_coords2[1] * 0.7;
                                delta_coords2[0] = 0.3 * -delta_coords2[0];
                                delta_coords2[1] = 0.3 * -delta_coords2[1];
                            }
                            else if (delta_coords2[0] == 0 & delta_coords2[1] == 0){
                                delta_coords2[0] = delta_coords1[0] * 0.7;
                                delta_coords2[1] = delta_coords1[1] * 0.7;
                                delta_coords1[0] = 0.3 * -delta_coords1[0];
                                delta_coords1[1] = 0.3 * -delta_coords1[1];
                            }
                            else {
                                if (Math.pow(delta_coords1[0],2) + Math.pow(delta_coords1[0],2) >= Math.pow(delta_coords2[0],2) + Math.pow(delta_coords2[0],2)){
                                    delta_coords2[0] = delta_coords1[0] * 0.7;
                                    delta_coords2[1] = delta_coords1[1] * 0.7;
                                    delta_coords1[0] = 0.3 * -delta_coords1[0];
                                    delta_coords1[1] = 0.3 * -delta_coords1[1];
                                }
                                else{
                                    delta_coords1[0] = delta_coords2[0] * 0.7;
                                    delta_coords1[1] = delta_coords2[1] * 0.7;
                                    delta_coords2[0] = 0.3 * -delta_coords2[0];
                                    delta_coords2[1] = 0.3 * -delta_coords2[1];
                                }
                            }
                        }
                    }
                };
                timer.start();
            }
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}