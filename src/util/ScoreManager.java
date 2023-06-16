package src.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ScoreManager {

    public static PriorityQueue<Node> openFile() {
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
            return new PriorityQueue<>();
        }

        return decodeFile(input);
    }

    public static void createFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("../src/Highscore.txt"));
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to create file");
        }
    }

    public static PriorityQueue<Node> decodeFile(ArrayList<String> file) {
        PriorityQueue<Node> output = new PriorityQueue<>();
        for (String line : file) {
            String[] data = line.split(",");
            String name = data[0];
            int score = Integer.parseInt(data[1]);
            Node node = new Node(score, name);
            output.add(node);
        }
        return output;
    }

    public static void writeToFile(PriorityQueue<Node> output) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("../src/Highscore.txt"));
            for (Node node : output) {
                writer.write(node.getName() + "-" + node.getScore());
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to write to file");
        }
    }
}
