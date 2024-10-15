package rasterop;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;


public class StatPanel extends JPanel {

    JLabel pixelSizeLabel = new JLabel("Pixel Size: 9");
    JLabel mousePositionLabel = new JLabel("Mouse Position:  x=0  y=0");
    JLabel linePointCountLabel = new JLabel("Number of line points:  0");
    public StatPanel(){

        add(pixelSizeLabel);
        add(mousePositionLabel);
        add(linePointCountLabel);
        pixelSizeLabel.setForeground(new Color(211, 211, 211));
        mousePositionLabel.setForeground(new Color(211, 211, 211));
        linePointCountLabel.setForeground(new Color(211, 211, 211));

        setLayout(new FlowLayout());
        setBounds(10,10,200,100);
        setBackground(new Color(0));
        SoftBevelBorder border = new SoftBevelBorder(BevelBorder.RAISED);
        setBorder(border);


    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(64, 64, 64));

        int cornerRadius = 30;
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        g.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
    }


    public void updatePixelSize(int pixelSize){
        pixelSizeLabel.setText("Pixel Size:  " + String.valueOf(pixelSize));
    }
    public void updateMousePosition(int x, int y){
        mousePositionLabel.setText("Mouse Position:  X = " + String.valueOf(x) + "  Y = " + String.valueOf(y));
    }
    public void updatePointCount(int vertexCount){
        linePointCountLabel.setText("Number of line vertices:  " + vertexCount);
    }

}
