package nus.trackme.data;

import nus.trackme.ui.UITask;
import nus.trackme.common.EnumList;

import java.io.*;

public class LoadFile {

    private static final String filename = ".\\src\\main\\java\\nus\\trackme\\data\\storage.txt";
    private static final File tempFile = new File(".\\src\\main\\java\\nus\\trackme\\data\\tmp2.txt");

    public LoadFile(UITask task){

        File originalFile = new File(filename);

        if (originalFile.exists()) {

            //Reference: https://stackoverflow.com/questions/54126403/show-stdout-in-file-and-in-console-with-system-setoutnew-printstreamnew-file
            // Temporarily disable System.out.println
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(new OutputStream() { public void write(int b) {}}));


            //Duplicate the data
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
                 BufferedReader reader = new BufferedReader(new FileReader(filename))){

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }

            } catch (IOException e) {
            e.printStackTrace();
            }

            //Delete original file
            originalFile.delete();

            //Load data
            try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))){
                String line;
                int index = 0;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                    EnumList.TYPE loadCMD = EnumList.TYPE.valueOf(parts[0]);

                    switch (loadCMD){
                        case D:
                            task.deadlineTask(parts[1], parts[3]);
                            break;
                        case E:
                            task.eventTask(parts[1], parts[4], parts[5]);
                            break;
                        case T:
                            task.toDoTask(parts[1]);
                            break;
                    }

                    if(parts[2].equals("1")){
                        task.markTaskAsDone(index);
                    }

                    index++;
                }

            }catch (IOException e) {
                e.printStackTrace();
            }

            // Restore System.out.println
            System.setOut(originalOut);

        }

        //Delete old tmp file
        tempFile.delete();

    }

}
