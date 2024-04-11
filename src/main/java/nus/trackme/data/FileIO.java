package nus.trackme.data;

import nus.trackme.commands.Task;

import java.io.*;
import java.util.List;

public class FileIO {

    private final String FILE_PATH = "storage.txt";
    private final File TEMP_FILE_PATH = new File("tmp.txt");

    /**
     * Create temporary text file.
     */
    public void CreateTempFile(){

        TEMP_FILE_PATH.delete();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_PATH))) {
            // Empty the temporary file
            writer.write(""); // Write an empty string
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replace temporary File to Original File.
     */
    public void replaceFile(String type){

        try (BufferedReader reader = new BufferedReader(new FileReader(TEMP_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (TEMP_FILE_PATH.exists())
            assert TEMP_FILE_PATH.delete() : "Failed to delete the original file: " + FILE_PATH + " on " + type + " method";
    }

    /**
     * Constructs to use saveTask method to save task into text file.
     *
     * @param cmd Type of commands.
     * @param task User input task.
     * @param by when is task end used by deadline command.
     * @param from when the task start used by event command.
     * @param to when the task end used by event command.
     */
    public FileIO(String cmd, String task, String by, String from, String to) {
        SaveTask(cmd, task, by, from, to);
    }

    /**
     * Constructs to use modifyTask method to modify existing task in text file
     *
     * @param cmd Type of commands.
     * @param isDone Is the task completed.
     * @param index To pinpoint which task to modify.
     */
    public FileIO(String cmd, String isDone, int index) {
        ModifyTask(isDone, index);
    }

    /**
     * Constructs to use deleteTask method to delete existing task in text file
     *
     * @param cmd Type of commands.
     * @param index To point which task to delete.
     */
    public FileIO(String cmd, int index) {
        DeleteTask(cmd, index);
    }

    /**
     * Constructs to use tag or untag method on existing task in text file.
     * @param cmd
     * @param index
     * @param tag
     */
    public FileIO(String cmd, int index, String tag){
        if(cmd.equals("TAG")){
            TagTask(index, tag);
        }
        else{
            UntagTask(index, tag);
        }
    }

    /**
     * To save the file into text file
     */
    public void SaveTask(String cmd, String task, String by, String from, String to){

        //Reference: "https://www.youtube.com/watch?v=ScUJx4aWRi0&ab_channel=CodingwithJohn"
        //File Format: command type, task, isDone, by, from, to, tag
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))){
            writer.write(cmd + "," + task + "," + "0" + "," + by + "," + from + "," + to + ",-");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * To modify the existing task in text file
     */
    public void ModifyTask(String isDone, int index){

        CreateTempFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_PATH));
             BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){

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

        replaceFile("Modify");

    }

    /**
     * To delete the existing file in text file
     *
     */
    public void DeleteTask(String cmd, int index){

        CreateTempFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_PATH, true));
             BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){

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

        replaceFile("Delete");
    }

    public void TagTask(int index, String tag){

        CreateTempFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_PATH));
             BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){

            String line, tmp;
            int idx = 0;

            while ((line = reader.readLine()) != null) {
                if(idx == index){
                    tmp = line;
                    String[] parts = tmp.split(",");
                    if(parts[6].equals("-")){
                        parts[6] = tag + "|";
                    }
                    else{
                        parts[6] = parts[6] + tag + "|";
                    }
                    line = String.join(",", parts);
                }
                idx++;

                writer.write(line);
                writer.newLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        replaceFile("Tag");

    }

    public void UntagTask(int index, String untag){

        CreateTempFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_PATH));
             BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){

            String line, tmp;
            int idx = 0;

            while ((line = reader.readLine()) != null) {
                if(idx == index){
                    tmp = line;
                    String[] parts = tmp.split(",");
                    String[] tags = parts[6].split("\\|");
                    parts[6] = "-";
                    for(String tag: tags){
                        if(!tag.equals(untag)){
                            if(parts[6].equals("-")){
                                parts[6] = tag + "|";
                            }
                            else{
                                parts[6] = parts[6] + tag + "|";
                            }
                        }
                    }

                    line = String.join(",", parts);
                }
                idx++;

                writer.write(line);
                writer.newLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        replaceFile("Tag");

    }

}
