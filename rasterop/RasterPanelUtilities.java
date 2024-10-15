package rasterop;

import objectdata.Point;

import javax.swing.*;
import java.util.ArrayList;

public class RasterPanelUtilities {
 public void updatePixelSize(JLabel label, int pixelSize){
     label.setText("Pixel Size: " + String.valueOf(pixelSize));
 }



public void updateMousePosition(JLabel label, int x, int y){
   label.setText("Mouse Position:  x=" + x + "  y=" + y);


}
public void updateLinePointCount(JLabel label, ArrayList point){
     label.setText("Number of line points:  " + point.size());
}

public void drawLine(int x1, int y1, int x2, int y2, JPanel panel){

}

}