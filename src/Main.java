import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {

    public static String DEFAULT_FOLDER = "D://User//Games//";
    private static StringBuilder logBuilder = new StringBuilder();

    public static void writeLog(File file, Boolean succs) {
        String kind = "Folder ";

        if (!file.isDirectory() && file.getName().contains(".")) {
            kind = "File ";
        }
        String result = " created ";
        if (!succs) {
            result = " not created ";
        }

        logBuilder.append(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + " "
                + LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)) + " "
                + kind + "\"" + file.getName() + "\"" + " (" + file.getPath() + ")" + result + '\n');
    }

    public static void CreateDir(String path) {
        File newdir = new File(path);
        if (newdir.mkdir()) {
            writeLog(newdir, true);
        } else {
            writeLog(newdir, false);
        }
    }

    public static void CreateFile(String path, String fileName) {
        String log;
        File newFile = new File(path, fileName);
        try {
            if (newFile.createNewFile()) {
                writeLog(newFile, true);
            } else {
                writeLog(newFile, false);
            }
        } catch (IOException e) {
            writeLog(newFile, false);
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        CreateDir(DEFAULT_FOLDER + "temp");
        CreateFile(DEFAULT_FOLDER + "temp//", "temp.txt");
        CreateDir(DEFAULT_FOLDER + "src");
        CreateDir(DEFAULT_FOLDER + "res");
        CreateDir(DEFAULT_FOLDER + "savegames");
        CreateDir(DEFAULT_FOLDER + "src//main");
        CreateDir(DEFAULT_FOLDER + "src//test");
        CreateFile(DEFAULT_FOLDER + "src//main//", "Main.java");
        CreateFile(DEFAULT_FOLDER + "src//main//", "Utils.java");
        CreateDir(DEFAULT_FOLDER + "res//drawables");
        CreateDir(DEFAULT_FOLDER + "res//vectors");
        CreateDir(DEFAULT_FOLDER + "res//icons");

        String puth = DEFAULT_FOLDER + "temp//temp.txt";
        try (FileWriter writer = new FileWriter(puth, true)) {
            writer.write(logBuilder.toString());
            //writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
