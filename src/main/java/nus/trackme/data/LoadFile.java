package nus.trackme.data;

import nus.trackme.ui.UITask;
import nus.trackme.common.EnumList;

import java.io.*;

public class LoadFile {

    private static final String FILE_PATH = "storage.txt";
    private static final File TEMP_FILE_PATH = new File("tmp.txt");

    /**
     * Check the storage.txt and add the existing task into the program.
     *
     * @param task The storage location designated for storing tasks.
     */
    public LoadFile(UITask task){

        File originalFile = new File(FILE_PATH);

        if (originalFile.exists()) {

            /* Reference: https://stackoverflow.com/questions/54126403/show-stdout-in-file-and-in-console-with-system-setoutnew-printstreamnew-file */
            /* Temporarily disable System.out.println */
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(new OutputStream() { public void write(int b) {}}));

            /*Delete any tmp file that still exist*/
            TEMP_FILE_PATH.delete();

            /* Duplicate the data */
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_PATH, true));
                 BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }

            } catch (IOException e) {
            e.printStackTrace();
            }

            /* Delete original file */
            originalFile.delete();

            /* Load data */
            try (BufferedReader reader = new BufferedReader(new FileReader(TEMP_FILE_PATH))){
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

                    if(!parts[6].equals("-")){
                        String [] tags = parts[6].split("\\|");
                        for(String tag:tags){
                            task.tagTask(index, tag);
                        }
                    }

                    index++;
                }

            }catch (IOException e) {
                e.printStackTrace();
            }

            /* Restore System.out.println */
            System.setOut(originalOut);



        }

        /* Delete old tmp file */
        TEMP_FILE_PATH.delete();

    }

}
