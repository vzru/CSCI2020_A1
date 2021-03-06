import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q4 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Setting up the main pane
        BorderPane pane = new BorderPane();

        // Setting up the bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart chart = new BarChart(xAxis, yAxis);
        chart.setLegendVisible(false);
        chart.setCategoryGap(0);
        chart.setBarGap(1);
        XYChart.Series letters = new XYChart.Series();
        int[] alphabetCounter = new int[26]; // Counter for every occurences

        // Setting up bottom nodes (Label, Textfield and Button)
        HBox inputs = new HBox(0);
        inputs.setAlignment(Pos.CENTER);
        inputs.setSpacing(5);
        TextField path = new TextField();
        path.setPrefWidth(400);
        Button btView = new Button("View");

        inputs.getChildren().add(new Label("Filename:"));
        inputs.getChildren().add(path);
        inputs.getChildren().add(btView);

        // Fill default Bar CHart with default values
        char c;
        for(c = 'A'; c <= 'Z'; ++c) {
            letters.getData().add(new XYChart.Data(String.valueOf(c), 0));
        }

        // Check for Enter Key Press
        path.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent key)
            {
                if (key.getCode().equals(KeyCode.ENTER))
                {
                    // read file from textfield input
                    java.io.File file = new java.io.File(path.getText());
                    Scanner input = null;
                    try {
                        input = new Scanner(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // Loop through every word in the file and increment the individually letter count
                    while(input.hasNext()) {
                        // Convert all words to lower case
                        String line = input.next().toLowerCase();

                        alphabetCounter[0] += (int)line.chars().filter(ch -> ch == 'a').count();
                        alphabetCounter[1] += (int)line.chars().filter(ch -> ch == 'b').count();
                        alphabetCounter[2] += (int)line.chars().filter(ch -> ch == 'c').count();
                        alphabetCounter[3] += (int)line.chars().filter(ch -> ch == 'd').count();
                        alphabetCounter[4] += (int)line.chars().filter(ch -> ch == 'e').count();
                        alphabetCounter[5] += (int)line.chars().filter(ch -> ch == 'f').count();
                        alphabetCounter[6] += (int)line.chars().filter(ch -> ch == 'g').count();
                        alphabetCounter[7] += (int)line.chars().filter(ch -> ch == 'h').count();
                        alphabetCounter[8] += (int)line.chars().filter(ch -> ch == 'i').count();
                        alphabetCounter[9] += (int)line.chars().filter(ch -> ch == 'j').count();
                        alphabetCounter[10] += (int)line.chars().filter(ch -> ch == 'k').count();
                        alphabetCounter[11] += (int)line.chars().filter(ch -> ch == 'l').count();
                        alphabetCounter[12] += (int)line.chars().filter(ch -> ch == 'm').count();
                        alphabetCounter[13] += (int)line.chars().filter(ch -> ch == 'n').count();
                        alphabetCounter[14] += (int)line.chars().filter(ch -> ch == 'o').count();
                        alphabetCounter[15] += (int)line.chars().filter(ch -> ch == 'p').count();
                        alphabetCounter[16] += (int)line.chars().filter(ch -> ch == 'q').count();
                        alphabetCounter[17] += (int)line.chars().filter(ch -> ch == 'r').count();
                        alphabetCounter[18] += (int)line.chars().filter(ch -> ch == 's').count();
                        alphabetCounter[19] += (int)line.chars().filter(ch -> ch == 't').count();
                        alphabetCounter[20] += (int)line.chars().filter(ch -> ch == 'u').count();
                        alphabetCounter[21] += (int)line.chars().filter(ch -> ch == 'v').count();
                        alphabetCounter[22] += (int)line.chars().filter(ch -> ch == 'w').count();
                        alphabetCounter[23] += (int)line.chars().filter(ch -> ch == 'x').count();
                        alphabetCounter[24] += (int)line.chars().filter(ch -> ch == 'y').count();
                        alphabetCounter[25] += (int)line.chars().filter(ch -> ch == 'z').count();
                    }

                    // Insert data into the bar chart
                    char c;
                    int counter = 0;
                    for(c = 'A'; c <= 'Z'; ++c) {
                        letters.getData().add(new XYChart.Data(String.valueOf(c), alphabetCounter[counter]));
                        //System.out.println(c + ": " + alphabetCounter[counter]);
                        ++counter;
                    }

                    input.close();
                }
            }
        });

        // Call action for the 'View' Button
        btView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // read file from textfield input
                java.io.File file = new java.io.File(path.getText());
                Scanner input = null;
                try {
                    input = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Read File");

                // Loop through every word in the file and increment the individually letter count
                while(input.hasNext()) {
                    // Convert all words to lower case
                    String line = input.next().toLowerCase();

                    alphabetCounter[0] += (int)line.chars().filter(ch -> ch == 'a').count();
                    alphabetCounter[1] += (int)line.chars().filter(ch -> ch == 'b').count();
                    alphabetCounter[2] += (int)line.chars().filter(ch -> ch == 'c').count();
                    alphabetCounter[3] += (int)line.chars().filter(ch -> ch == 'd').count();
                    alphabetCounter[4] += (int)line.chars().filter(ch -> ch == 'e').count();
                    alphabetCounter[5] += (int)line.chars().filter(ch -> ch == 'f').count();
                    alphabetCounter[6] += (int)line.chars().filter(ch -> ch == 'g').count();
                    alphabetCounter[7] += (int)line.chars().filter(ch -> ch == 'h').count();
                    alphabetCounter[8] += (int)line.chars().filter(ch -> ch == 'i').count();
                    alphabetCounter[9] += (int)line.chars().filter(ch -> ch == 'j').count();
                    alphabetCounter[10] += (int)line.chars().filter(ch -> ch == 'k').count();
                    alphabetCounter[11] += (int)line.chars().filter(ch -> ch == 'l').count();
                    alphabetCounter[12] += (int)line.chars().filter(ch -> ch == 'm').count();
                    alphabetCounter[13] += (int)line.chars().filter(ch -> ch == 'n').count();
                    alphabetCounter[14] += (int)line.chars().filter(ch -> ch == 'o').count();
                    alphabetCounter[15] += (int)line.chars().filter(ch -> ch == 'p').count();
                    alphabetCounter[16] += (int)line.chars().filter(ch -> ch == 'q').count();
                    alphabetCounter[17] += (int)line.chars().filter(ch -> ch == 'r').count();
                    alphabetCounter[18] += (int)line.chars().filter(ch -> ch == 's').count();
                    alphabetCounter[19] += (int)line.chars().filter(ch -> ch == 't').count();
                    alphabetCounter[20] += (int)line.chars().filter(ch -> ch == 'u').count();
                    alphabetCounter[21] += (int)line.chars().filter(ch -> ch == 'v').count();
                    alphabetCounter[22] += (int)line.chars().filter(ch -> ch == 'w').count();
                    alphabetCounter[23] += (int)line.chars().filter(ch -> ch == 'x').count();
                    alphabetCounter[24] += (int)line.chars().filter(ch -> ch == 'y').count();
                    alphabetCounter[25] += (int)line.chars().filter(ch -> ch == 'z').count();
                }

                // Insert Data into Bar Chart
                char c;
                int counter = 0;
                for(c = 'A'; c <= 'Z'; ++c) {
                    letters.getData().add(new XYChart.Data(String.valueOf(c), alphabetCounter[counter]));
                    //System.out.println(c + ": " + alphabetCounter[counter]);
                    ++counter;
                }

                input.close();
            }
        });

        // Add the data to the BarChart
        chart.getData().add(letters);
        VBox charts = new VBox(chart);

        pane.setCenter(charts);
        pane.setBottom(inputs);

        // Display Screen
        Scene scene = new Scene(pane);
        stage.setTitle("Histogram");
        stage.setScene(scene);
        stage.show();
    }
}
