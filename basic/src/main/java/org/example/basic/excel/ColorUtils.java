package org.example.basic.excel;

import javafx.scene.paint.Color;

public class ColorUtils {
    public static void main(String[] args) {
        Color color = Color.web("#FFB400");
        int colorInt = color.hashCode();
        String colorString = color.toString();

        System.out.println(colorInt);
        System.out.println(colorString);
    }
}
