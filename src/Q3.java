import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class Q3  extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        MultiCircle mainCircle = new MultiCircle(250, 20, 300,300,200,3);

        mainCircle.bigC.setStroke(Color.BLACK);
        mainCircle.bigC.setFill(Color.WHITE);

        Scene scene = new Scene(mainCircle, 600, 600);
        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class MultiCircle extends Pane{

    public static ClickableCircle currentlyMoving = null;
    public Circle bigC = new Circle();
    public ClickableCircle[] cc;
    public Text[] tc;
    public Line[] lc;

    private double br, lr, tr, cx, cy;
    private int pn;
    private int currentInArray = -1;

    public MultiCircle(double bigRad, double littleRad, double centerX, double centerY, double textRad, int pointNum) {
        pn = pointNum;

        cc = new ClickableCircle[pn];
        tc = new Text[pn];
        lc = new Line[pn];

        br = bigRad;
        lr = littleRad;
        tr = textRad;
        cx = centerX;
        cy = centerY;

        bigC.setRadius(br);
        getChildren().add(bigC);
        bigC.setCenterX(cx);
        bigC.setCenterY(cy);

        for (int i = 0; i < pn; ++i) {
            tc[i] = new Text(Double.toString((360f) / pn));
            tc[i].setX(cx + tr * Math.sin(Math.PI * 2f / pn * i));
            tc[i].setY(cy + tr * Math.cos(Math.PI * 2f / pn * i));

            getChildren().add(tc[i]);
        }

        for (int i = 0; i < pn; ++i) {
            lc[i] = new Line(
                    cx + br * Math.sin(Math.PI * 2f / pn * i),
                    cy + br * Math.cos(Math.PI * 2f / pn * i),
                    cx + br * Math.sin(Math.PI * 2f / pn * ((i + 1) % pn)),
                    cy + br * Math.cos(Math.PI * 2f / pn * ((i + 1) % pn))
            );

            getChildren().add(lc[i]);
        }

        for (int i = 0; i < pn; ++i) {
            cc[i] = new ClickableCircle(lr, i);
            cc[i].setCenterX(cx + br * Math.sin(Math.PI * 2f / pn * i));
            cc[i].setCenterY(cy + br * Math.cos(Math.PI * 2f / pn * i));

            getChildren().add(cc[i]);

            cc[i].setOnMouseReleased(e -> {
                if (currentlyMoving == e.getSource()) {
                    currentlyMoving = null;
                    currentInArray = -1;
                }
            });

            cc[i].setOnMouseDragged(e -> {
                if (currentlyMoving == null) {
                    //if (((ClickableCircle) e.getSource()).CheckIfInside(e.getX(), e.getY())) {
                        currentlyMoving = (ClickableCircle) e.getSource();
                        currentInArray = ((ClickableCircle) e.getSource()).arrPos;
                    //}
                }

                if (currentlyMoving == e.getSource()) {
                    double DX = e.getX() - cx;
                    double DY = e.getY() - cy;

                    double newRad = Math.sqrt(DX * DX + DY * DY);

                    if (newRad == 0) {
                        currentlyMoving.setCenterX(cx + 0);
                        currentlyMoving.setCenterY(cy + br);

                        tc[currentInArray].setX(cx + 0);
                        tc[currentInArray].setY(cy + tr);

                        int prev = currentInArray <= 0 ? currentInArray - 1 : cc.length - 1;

                        lc[prev].setEndX(cx + 0);
                        lc[prev].setEndY(cy + br);
                        lc[currentInArray].setStartX(cx + 0);
                        lc[currentInArray].setStartY(cy + br);
                    } else {
                        double newX = cx + DX * br / newRad;
                        double newY = cy + DY * br / newRad;

                        currentlyMoving.setCenterX(newX);
                        currentlyMoving.setCenterY(newY);

                        double newTX = cx + DX * tr / newRad;
                        double newTY = cy + DY * tr / newRad;

                        tc[currentInArray].setX(newTX);
                        tc[currentInArray].setY(newTY);

                        int prev = currentInArray > 0 ? currentInArray - 1 : cc.length - 1;

                        lc[prev].setEndX(newX);
                        lc[prev].setEndY(newY);
                        lc[currentInArray].setStartX(newX);
                        lc[currentInArray].setStartY(newY);
                    }

                    int p1 = currentInArray - 2;
                    p1 = p1 < 0 ? p1 + cc.length : p1;
                    int p2 = currentInArray - 1;
                    p2 = p2 < 0 ? p2 + cc.length : p2;
                    int p3 = currentInArray;
                    int p4 = currentInArray + 1;
                    p4 = p4 >= cc.length ? p4 - cc.length : p4;
                    int p5 = currentInArray + 2;
                    p5 = p5 >= cc.length ? p5 - cc.length : p5;

                    double len1x = cc[p1].getCenterX() - cc[p2].getCenterX();
                    double len1y = cc[p1].getCenterY() - cc[p2].getCenterY();
                    double len2x = cc[p2].getCenterX() - cc[p3].getCenterX();
                    double len2y = cc[p2].getCenterY() - cc[p3].getCenterY();
                    double len3x = cc[p3].getCenterX() - cc[p4].getCenterX();
                    double len3y = cc[p3].getCenterY() - cc[p4].getCenterY();
                    double len4x = cc[p4].getCenterX() - cc[p5].getCenterX();
                    double len4y = cc[p4].getCenterY() - cc[p5].getCenterY();

                    double mid1x = cc[p1].getCenterX() - cc[p3].getCenterX();
                    double mid1y = cc[p1].getCenterY() - cc[p3].getCenterY();
                    double mid2x = cc[p2].getCenterX() - cc[p4].getCenterX();
                    double mid2y = cc[p2].getCenterY() - cc[p4].getCenterY();
                    double mid3x = cc[p3].getCenterX() - cc[p5].getCenterX();
                    double mid3y = cc[p3].getCenterY() - cc[p5].getCenterY();

                    double len1 = Math.sqrt(len1x * len1x + len1y * len1y);
                    double len2 = Math.sqrt(len2x * len2x + len2y * len2y);
                    double len3 = Math.sqrt(len3x * len3x + len3y * len3y);
                    double len4 = Math.sqrt(len4x * len4x + len4y * len4y);

                    double mid1 = Math.sqrt(mid1x * mid1x + mid1y * mid1y);
                    double mid2 = Math.sqrt(mid2x * mid2x + mid2y * mid2y);
                    double mid3 = Math.sqrt(mid3x * mid3x + mid3y * mid3y);

                    tc[p2].setText(Double.toString(calcAngleOutput(mid1, len1, len2)));
                    tc[p3].setText(Double.toString(calcAngleOutput(mid2, len2, len3)));
                    tc[p4].setText(Double.toString(calcAngleOutput(mid3, len3, len4)));
                }
            });
        }
    }

    private double calcAngleOutput(double pMid, double pLeft, double pRight)
    {
        if (2 * pLeft * pRight <= 0)
            return 0;
        return Math.toDegrees(Math.acos((pLeft * pLeft + pRight * pRight - pMid * pMid) / (2 * pLeft * pRight)));
    }
}

class ClickableCircle extends Circle{

    public int arrPos;

    public ClickableCircle(double rad, int arrayPosition)
    {
        arrPos = arrayPosition;
        setRadius(rad);
        setStroke(Color.BLACK);
        setFill(Color.RED);
    }
}
