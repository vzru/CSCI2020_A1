import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        GridPane pane = new GridPane();
        VBox textDescriptions = new VBox(10);
        textDescriptions.setAlignment(Pos.TOP_LEFT);
        VBox inputFields = new VBox(0);
        inputFields.setAlignment(Pos.TOP_RIGHT);
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        float FutureValue;

        TextField invest = new TextField();
        invest.setAlignment(Pos.CENTER_RIGHT);
        TextField years = new TextField();
        years.setAlignment(Pos.CENTER_RIGHT);
        TextField interestRate = new TextField();
        interestRate.setAlignment(Pos.CENTER_RIGHT);
        TextField future = new TextField("0.00");
        future.setAlignment(Pos.CENTER_RIGHT);
        future.setDisable(true);

        Button btCalc = new Button("Calculate");

        btCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                float InvestmentAmount = Float.parseFloat(invest.getText());
                float Years = Float.parseFloat(years.getText());
                float InterestRate = Float.parseFloat(interestRate.getText()) / 100;
                float MonthlyInterest = InterestRate / 12;
                float Months = Years * 12;
                double TotalInterest = Math.pow(1 + MonthlyInterest, Months);
                float result = (float) (InvestmentAmount * TotalInterest);
                future.setText(String.valueOf(result));
            }
        });

        textDescriptions.getChildren().add(new Label("Investment Amount ($)"));
        textDescriptions.getChildren().add(new Label("Years"));
        textDescriptions.getChildren().add(new Label("Annual Interest Rate (%)"));
        textDescriptions.getChildren().add(new Label("Future Value ($)"));

        inputFields.getChildren().add(invest);
        inputFields.getChildren().add(years);
        inputFields.getChildren().add(interestRate);
        inputFields.getChildren().add(future);

        buttons.getChildren().add(btCalc);

        pane.add(textDescriptions, 0, 0);
        pane.add(inputFields, 1, 0);
        pane.add(buttons, 1, 1);

        Scene scene = new Scene(pane);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
