package src.util;

import processing.core.PApplet;
import processing.core.PImage;

public class Button {

    private int x, y;
    private int width, height;
    private PImage image;
    private String label;
    private int baseColor;
    private int highlightColor;
    private int alpha;
    private boolean isOver;
    private boolean isPressed;
    private boolean isEnabled;

    public Button(int x, int y, int width, int height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.baseColor = 200;
        this.highlightColor = 255;
        this.alpha = 0;
        this.isPressed = false;
        this.isEnabled = true;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public void setColor(int baseColor, int highlightColor, int alpha) {
        this.baseColor = baseColor;
        this.highlightColor = highlightColor;
        this.alpha = alpha;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void display(PApplet sketch) {
        sketch.noStroke(); // Menghilangkan garis hitam

        if (isEnabled) {
            if (isOver) {
                sketch.fill(highlightColor, alpha);
            } else {
                sketch.fill(baseColor, alpha);
            }
        } else {
            sketch.fill(100, alpha);
        }
        sketch.rect(x, y, width, height);

        if (image != null) {
            sketch.image(image, x, y, width, height);
        }

        sketch.fill(0);
        sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
        sketch.text(label, x + width / 2, y + height / 2);
    }

    public void update(int mouseX, int mouseY, boolean isMousePressed) {
        if (isEnabled) {
            isOver = isOver(mouseX, mouseY);
            if (isOver && isMousePressed) {
                isPressed = true;
            } else {
                isPressed = false;
            }
        } else {
            isOver = false;
            isPressed = false;
        }
    }

    public boolean isOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public boolean isClicked() {
        boolean clicked = isPressed;
        isPressed = false;
        return clicked;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setX(int x) {
        this.x = x;
    }
}
