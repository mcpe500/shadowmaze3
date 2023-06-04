package src;

import src.util.Button;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    private PImage background;
    private Button playButton;
    private Button settingsButton;

    private static final int mainmenu = 0;
    private static final int settings = 1;
    private static final int playmenu = 2;

    private int currentPage;
    private int previousPage;

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
        playButton = new Button(100, 100, 100, 100, "Start");
        playButton.setImage(loadImage("../assets/sprites/start_button.png"));
        settingsButton = new Button(200, 200, 200, 100, "Settings");
        settingsButton.setImage(loadImage("../assets/sprites/settings_button.png"));

        currentPage = mainmenu;
        previousPage = mainmenu;
    }

    public void draw() {
        background(background);

        if (currentPage == mainmenu) {
            playButton.update(mouseX, mouseY, mousePressed);
            playButton.display(this);
            settingsButton.update(mouseX, mouseY, mousePressed);
            settingsButton.display(this);

            if (playButton.isClicked()) {
                System.out.println("playButton pressed");
                // Logic to go to the game screen (goToGame method)
                goToGame();
            }
            if (settingsButton.isClicked()) {
                System.out.println("settingsButton pressed");
                // Logic to go to the settings screen (goToSettings method)
                goToSettings();
            }
        } else if (currentPage == settings) {
            // Display the settings page
            displaySettings();
        } else if (currentPage == playmenu) {
            // Display the game page
            displayGameMenu();
        }
    }

    public void goToSettings() {
        previousPage = currentPage;
        currentPage = settings;
    }

    public void goToPreviousPage() {
        currentPage = previousPage;
    }

    public void goToGame() {
        // Logic to go to the game screen
        currentPage = playmenu;
    }

    public void displaySettings() {
        Button backButton = new Button(50, 50, 100, 50, "Back");
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);

        if (backButton.isClicked()) {
            System.out.println("backButton pressed");
            goToPreviousPage();
        }
    }

    public void displayGameMenu() {
        Button backButton = new Button(50, 50, 100, 50, "Back");
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        Button adventureButton = new Button(300, 300, 100, 50, "Adventure");
        adventureButton.setImage(loadImage("../assets/sprites/adventure_button.png"));
        adventureButton.display(this);
        adventureButton.update(mouseX, mouseY, mousePressed);
        Button versusButton = new Button(300, 400, 100, 50, "Versus");
        versusButton.setImage(loadImage("../assets/sprites/versus_button.png"));
        versusButton.display(this);
        versusButton.update(mouseX, mouseY, mousePressed);
        // adventureButton.setImage(loadImage("../assets/sprites/adventure_button.png"));

        if (backButton.isClicked()) {
            System.out.println("backButton pressed");
            goToPreviousPage();
        } else if (adventureButton.isClicked()) {
            System.out.println("adventureButton pressed");
            // goToPreviousPage();
        } else if (versusButton.isClicked()) {
            System.out.println("versusButton pressed");
            // goToPreviousPage();
        }
    }
    public void goToAdventureMenu(){

    }
}
