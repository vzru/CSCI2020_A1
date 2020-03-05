import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Q1  extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        //Create gridpane for easy ordering
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);

        //Randomly select 3 cards in the Cards folder, add it to the pane at column 'i'
        for (int i = 0; i < 3; ++i)
        {
            int rand = (int)(Math.random() * 54) % 54;

            Image c = new Image("Cards/" + Integer.toString(rand) + ".png");

            pane.add(new ImageView(c), i, 0);
        }

        //Show scene
        Scene scene = new Scene(pane);
        stage.setTitle("Cards");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
