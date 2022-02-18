package yoda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading and writing data to the disk.
 * @author Tonishka Singh
 */
public class Storage {
    private File data;
    private final String dir;
    private String filePath;

    public Storage() {
        this.dir = System.getProperty("user.dir");  // Gets the current directory
        this.filePath = dir + File.separator + "YodaData" + File.separator + "data.txt";
        this.data = new File(this.filePath);
    }

    /**
     * Loads data from the hard drive when user starts up the application.
     * @return The loaded data in an ArrayList.
     */
    public ArrayList<Quest> load() {
        Path path = Paths.get(dir, "YodaData");
        boolean directoryExists = Files.exists(path);

        // Creates directory if it does not exist
        if (!directoryExists) {
            try {
                Files.createDirectories(path);
            } catch (IOException ioException){
                System.err.println("Failed to create directory!" + ioException.getMessage());
            }
        }

        // Creates data file if it does not exist
        try {
            this.data.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }

        // Reads data from file and stores it in an ArrayList
        Scanner fileReader = null;
        ArrayList<Quest> quests = new ArrayList<>();
        try {
            fileReader = new Scanner(data);
        } catch (FileNotFoundException e) {
            System.out.println("Whoops! File not found!" + e.getMessage());
        }

        if(data.length() != 0) {
            while (fileReader.hasNextLine()) {
                String readData = fileReader.nextLine();
                Quest q = processQuest(readData);
                quests.add(q);
            }
        }

        fileReader.close();
        return quests;
    }

    /**
     * Saves the changes made by the user to the hard disk.
     * @param questList The data to write back.
     */
    public void save(QuestList questList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (int i = 0; i < questList.numQuests(); i++) {
                String s = processQuest(questList.getQuest(i)) + "\n";
                fileWriter.write(s);
            }
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Converts the input into a Quest-type object.
     * @param input String type data loaded from disk.
     * @return Quest.
     */
    public Quest processQuest(String input) {
        String[] tokens = input.split("\\|");
        String type = tokens[0];
        int status = Integer.parseInt(tokens[1]);
        String description = tokens[2];
        String date;
        String time;
        if (type.equals("T")) {
            return new ToDo(description, status);
        } else if (type.equals("D")) {
            date = tokens[3];
            time = tokens[4];
            return new Deadline(description, date, time, status);
        } else {
            time = tokens[3];
            return new Event(description, time, status);
        }
    }

    /**
     * Coverts Quest into a String representation to store it in the disk.
     * @param quest Quest to convert to String for storage.
     * @return The String to write back to the storage.
     */
    public String processQuest(Quest quest) {
        String toWrite = quest.getType();
        toWrite += "|";
        toWrite += quest.getStatus();
        toWrite += "|";
        toWrite += quest.getDescription();
        if (quest.getType().equals("E")) {
            toWrite += "|";
            toWrite += quest.getPeriod();
        }
        if (quest.getType().equals("D")) {
            toWrite += "|";
            toWrite += quest.dateToStore();
            toWrite += "|";
            toWrite += quest.timeToStore();
        }
        return toWrite;
    }
}
