
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            LogParser log = new LogParser(Paths.get("."));
            log.CreateFile(Paths.get("."));
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//            System.out.println(log.getNumberOfUniqueIPs(df.parse("30.08.2012 16:08:14"), df.parse("29.2.2028 5:4:7")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException p) {
            p.getErrorOffset();
        }
    }
}
