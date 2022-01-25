import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File data;
    private final String dir;
    private String filePath;

    public Storage() {
        this.dir = System.getProperty("user.dir");
        this.filePath = dir + File.separator + "YodaData" + File.separator + "data.txt";
        this.data = new File(this.filePath);
    }

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
            System.out.println("An error occurred.");
        }

        // Reads data from file and stores it in an ArrayList
        Scanner fileReader = null;
        ArrayList<Quest> quests = new ArrayList<>();
        try {
            fileReader = new Scanner(data);
        } catch (FileNotFoundException e) {
            System.out.println("Whoops! File not found!");
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

    public Quest processQuest(String input) {
        String[] tokens = input.split("\\|");
        String type = tokens[0];
        int status = Integer.parseInt(tokens[1]);
        String description = tokens[2];
        String time;
        if (type.equals("T")) {
            return new ToDo(description, status);
        } else if (type.equals("D")) {
            time = tokens[3];
            return new Deadline(description, time, status);
        } else {
            time = tokens[3];
            return new Event(description, time, status);
        }
    }

    public String processQuest(Quest quest) {
        String toWrite = quest.getType();
        toWrite += "|";
        toWrite += quest.getStatus();
        toWrite += "|";
        toWrite += quest.getDescription();
        if (!quest.getType().equals("T")) {
            toWrite += "|";
            toWrite += quest.getPeriod();
        }
        return toWrite;
    }
}
