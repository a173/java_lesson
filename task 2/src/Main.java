
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            LogParser log = new LogParser(Paths.get("."));
            log.CreateFile(Paths.get("."));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException p) {
            p.getErrorOffset();
        }
    }
}
