import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class LogParser {
    private ArrayList<Log> array = new ArrayList<Log>();

    public LogParser(Path logDir) throws IOException, ParseException {
        parseFile(logDir);
    }

    private void parseFile(Path logDir) throws IOException, ParseException {
        String buf;
        BufferedReader reader;
        String[] str;
        for (File file : logDir.toFile().listFiles())
            if (Pattern.compile("\\w*\\.log").matcher(file.getName()).matches()) {
                FileReader localFile = new FileReader(file.getAbsolutePath());
                reader = new BufferedReader(localFile);
                while ((buf = reader.readLine()) != null) {
                    str = buf.split("\\t");
                    if (str.length != 5)
                        throw new RuntimeException("Error number arguments\n");
                    array.add(new Log(str[0], str[1], new SimpleDateFormat("dd.mm.y HH:mm:ss").parse(str[2]), str[3], str[4]));
                }
                localFile.close();
            }
    }

    public void CreateFile(Path logDir) throws IOException {
        FileWriter writer = new FileWriter(logDir + "/result.log");
        for (Log l : array) {
            if (Pattern.compile("^(DONE_TASK)").matcher(l.get_event()).find() && Pattern.compile("OK").matcher(l.get_status()).find())
                writer.write(l.toString() + "\n");
        }
        writer.close();
    }
}
