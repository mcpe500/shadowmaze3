package src.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {

    public static int[] openFile() {
        ArrayList<String> input = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("../src/Save.txt"));
            String line;
            while((line=in.readLine())!=null) {
                input.add(line);
            }
        } catch (IOException ex) {
            System.out.println("File not found");
        }

        return decodeFile(input);
    }

    public static int[] decodeFile(ArrayList<String> file) {
        int[] output = new int[]{0,0,0,0,0,0};
        for (int i=0; i<file.size(); i++) {
            output[i] = Integer.parseInt(file.get(i));
        }
        return output;
    }

    public static void writeToFile(int[] output) {
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("../src/Save.txt"), true);
            for (int i : output) {
                out.println(i);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }

}
