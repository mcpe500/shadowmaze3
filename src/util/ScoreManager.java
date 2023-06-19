package src.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoreManager {

    public static ArrayList<Node> openFile() {
        ArrayList<String> input = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("../src/Highscore.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                input.add(line);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("File not found");
            createFile();
            return new ArrayList<>();
        }
        ArrayList<Node> output = new ArrayList<>();
        for (String string : input) {
            String[] temp = string.split("-");
            output.add(new Node(Long.parseLong(temp[1]), ""));
        }
        return output;
    }

    public static ArrayList<Node> sort(ArrayList<Node> input, Node newNode) {
        ArrayList<Node> sorted = new ArrayList<>(input);
        sorted.add(newNode);
        sorted.sort(Comparator.comparingLong(Node::getScore));
        return sorted;
    }

    public static void createFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("../src/Highscore.txt"));
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to create file");
        }
    }

    public static void writeToFile(ArrayList<Node> output) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("../src/Highscore.txt"));
            for (Node node : output) {
                writer.write(node.getIndentifier() + "-" + node.getScore());
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to write to file");
        }
    }
}
