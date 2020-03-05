import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Q2  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Setting up the base pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5,5,5,5));
        // Setting up the Pane for the text pane
        VBox textDescriptions = new VBox(10);
        textDescriptions.setAlignment(Pos.TOP_LEFT);
        // Setting up the pane for the inputs
        VBox inputFields = new VBox(0);
        inputFields.setAlignment(Pos.TOP_RIGHT);
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        // Setting up the description texts and adding it to the pane
        textDescriptions.getChildren().add(new Label("Investment Amount ($)"));
        textDescriptions.getChildren().add(new Label("Years"));
        textDescriptions.getChildren().add(new Label("Annual Interest Rate (%)"));
        textDescriptions.getChildren().add(new Label("Future Value ($)"));

        // Setting up the input fields
        TextField invest = new TextField();
        invest.setAlignment(Pos.CENTER_RIGHT);
        TextField years = new TextField();
        years.setAlignment(Pos.CENTER_RIGHT);
        TextField interestRate = new TextField();
        interestRate.setAlignment(Pos.CENTER_RIGHT);
        TextField future = new TextField("0.00");
        future.setAlignment(Pos.CENTER_RIGHT);
        future.setDisable(true);

        // Calculation button that displays the results
        Button btCalc = new Button("Calculate");

        // Action handler for the button press to calculate
        btCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                float InvestmentAmount = Float.parseFloat(invest.getText());
                float Years = Float.parseFloat(years.getText());
                float InterestRate = Float.parseFloat(interestRate.getText()) / 100;
                float MonthlyInterest = InterestRate / 12;
                float Months = Years * 12;
                double TotalInterest = Math.pow(1 + MonthlyInterest, Months);
                float result = (float) Math.round(InvestmentAmount * TotalInterest * 100) / 100;
                future.setText(String.valueOf(result));
            }
        });

        // Adding the inputs fields into the VBox pane
        inputFields.getChildren().add(invest);
        inputFields.getChildren().add(years);
        inputFields.getChildren().add(interestRate);
        inputFields.getChildren().add(future);

        // Add button to the pane
        buttons.getChildren().add(btCalc);

        // Adding the sub-panes into the main pane
        pane.add(textDescriptions, 0, 0);
        pane.add(inputFields, 1, 0);
        pane.add(buttons, 1, 1);

        // Displaying the pane
        Scene scene = new Scene(pane);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
