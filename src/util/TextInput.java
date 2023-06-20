package src.util;
 import processing.core.PApplet;
 public class TextInput {
    private PApplet parent;
    private float x, y, width, height;
    private String text;
    private boolean focused;
     public TextInput(PApplet parent, float x, float y, float width, float height) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "";
        this.focused = false;
    }
     public void update() {
        if (parent.mousePressed) {
            if (parent.mouseX >= x && parent.mouseX <= x + width && parent.mouseY >= y && parent.mouseY <= y + height) {
                focused = true;
            } else {
                focused = false;
            }
        }
    }
     public void display() {
        parent.fill(255);
        parent.rect(x, y, width, height);
         parent.fill(0);
        parent.textAlign(PApplet.LEFT, PApplet.CENTER);
        parent.text(text, x + 5, y + height / 2);
         if (focused) {
            parent.stroke(0);
            parent.line(x, y + height, x + width, y + height);
        }
    }
     public void keyPressed() {
        if (focused) {
            if (parent.key == PApplet.BACKSPACE && text.length() > 0) {
                text = text.substring(0, text.length() - 1);
            } else if (parent.key != PApplet.ENTER && parent.key != PApplet.RETURN) {
                text += parent.key;
            }
        }
    }
     public String getText() {
        return text;
    }
 }