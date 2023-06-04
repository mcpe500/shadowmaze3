package src;

import src.util.Button;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    private PImage background;
    private Button button;

    public static void main(String[] args) {
        Main app = new Main();
        String[] main = { "Main" };
        PApplet.runSketch(main, app);
    }

    public void settings() {
        size(1280, 720);
    }

    public void setup() {
        background = loadImage("../assets/sprites/SL_menu.png");
        background.resize(1280, 720);

        button = new Button(width/2, height/2, 100, 50);
        button.setImage(loadImage("../assets/button.png"));
        button.setColor(color(255), color(0));

        // Initialize objects and set up initial configurations
    }

    public void draw() {
        background(background);
        button.update(mouseX, mouseY);
        button.display(this);
    }

    public void keyPressed() {
        // Logic executed when a key is pressed
    }

    public void keyReleased() {
        // Logic executed when a key is released
    }

    public void mousePressed() {
        // Logic executed when the mouse button is pressed
    }

    public void mouseReleased() {
        // Logic executed when the mouse button is released
    }
}
