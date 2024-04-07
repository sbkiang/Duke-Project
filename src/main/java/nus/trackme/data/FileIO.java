package nus.trackme.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {

    private static final String filename = ".\\src\\main\\java\\nus\\trackme\\data\\storage.txt";

    private static final File tempFile = new File(".\\src\\main\\java\\nus\\trackme\\data\\tmp.txt");

    public void CreateTempFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Empty the temporary file
            writer.write(""); // Write an empty string
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileIO(String cmd, String task, String by, String from, String to) {
        SaveTask(cmd, task, by, from, to);
    }

    public FileIO(String cmd, String isDone, int index) {
        ModifyTask(isDone, index);
    }

    public FileIO(String cmd, int index) {
        DeleteTask(cmd, index);
    }

    public void SaveTask(String cmd, String task, String by, String from, String to){

        //Reference: "https://www.youtube.com/watch?v=ScUJx4aWRi0&ab_channel=CodingwithJohn"
        //File Format: command type, task, isDone, by, from, to
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
            writer.write(cmd + "," + task + "," + "0" + "," + by + "," + from + "," + to);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ModifyTask(String isDone, int index){

        CreateTempFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
             BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line, tmp;
            int idx = 0;

            while ((line = reader.readLine()) != null) {
                if(idx == index){
                    tmp = line;
                    String[] parts = tmp.split(",");
                    parts[2] = isDone;
                    line = String.join(",", parts);
                }
                idx++;

                writer.write(line);
                writer.newLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File originalFile = new File(filename);
        if (originalFile.exists()) {
            originalFile.delete();
        }
        tempFile.renameTo(originalFile);

    }

    public void DeleteTask(String cmd, int index){

        CreateTempFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
             BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;
            int idx = 0;

            while ((line = reader.readLine()) != null) {
                if(idx != index){
                    //skip that task mean delete the task
                    writer.write(line);
                    writer.newLine();
                }
                idx++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File originalFile = new File(filename);
        if (originalFile.exists()) {
            originalFile.delete();
        }
        tempFile.renameTo(originalFile);
    }

}