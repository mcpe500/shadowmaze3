package src.interface;

import processing.core.PApplet;
import processing.core.PImage;

public class Button {
    private int x, y; // Position of the button
    private int width, height; // Dimensions of the button
    private PImage image; // Image for the button
    private int baseColor; // Base color of the button
    private int highlightColor; // Color when the button is highlighted
    private boolean isOver; // Flag to indicate if the mouse is over the button

    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public void setColor(int baseColor, int highlightColor) {
        this.baseColor = baseColor;
        this.highlightColor = highlightColor;
    }

    public void display(PApplet sketch) {
        if (isOver) {
            sketch.fill(highlightColor);
        } else {
            sketch.fill(baseColor);
        }
        sketch.rect(x, y, width, height);

        if (image != null) {
            sketch.image(image, x, y, width, height);
        }
    }

    public void update(int mouseX, int mouseY) {
        if (isOver(mouseX, mouseY)) {
            isOver = true;
        } else {
            isOver = false;
        }
    }

    public boolean isOver(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPressed() {
        return isOver;
    }
}
