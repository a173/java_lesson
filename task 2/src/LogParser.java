import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery {
    private List<Log> array = new ArrayList<Log>();

    public LogParser(Path logDir) throws IOException, ParseException {
        parseFile(logDir);
    }

    private void parseFile(Path logDir) throws IOException, ParseException {
        String buf;
        BufferedReader reader;
        String[] str;
        for (File file : Objects.requireNonNull(logDir.toFile().listFiles())) {
            if (Pattern.compile("\\w*\\.log").matcher(file.getName()).matches()) {
                FileReader localFile = new FileReader(file.getAbsolutePath());
                reader = new BufferedReader(localFile);
                while ((buf = reader.readLine()) != null) {
                    str = buf.split("\\t");
                    if (str.length != 5)
                        throw new RuntimeException("Error number arguments\n");
                    array.add(new Log(str[0], str[1], new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(str[2]), str[3], str[4]));
                }
                localFile.close();
            }
        }
    }

    public void CreateFile(Path logDir) throws IOException {
        FileWriter writer = new FileWriter(logDir + "/res.txt");
        for (Log l : array) {
            if (Pattern.compile("^(DONE_TASK)").matcher(l.get_event().toString()).find() && Pattern.compile("OK").matcher(l.get_status().toString()).find())
                writer.write(l.toString() + "\n");
        }
        writer.close();
    }

    private Date setDateMin(Date after) {
        return after == null ? array.stream().min(Comparator.comparing(Log::get_date)).get().get_date() : after;
    }

    private Date setDateMax(Date before) {
        return before == null ? array.stream().max(Comparator.comparing(Log::get_date)).get().get_date() : before;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Date afterTmp = setDateMin(after);
        Date beforeTmp = setDateMax(before);
        return (int) array.stream().filter(s -> s.get_date().getTime() >= afterTmp.getTime() && s.get_date().getTime() <= beforeTmp.getTime()).count();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Date afterTmp = setDateMin(after);
        Date beforeTmp = setDateMax(before);
        return array.stream().filter(s -> s.get_date().getTime() >= afterTmp.getTime() && s.get_date().getTime() <= beforeTmp.getTime()).map(Log::get_ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Date afterTmp = setDateMin(after);
        Date beforeTmp = setDateMax(before);
        return array.stream().filter(s -> s.get_user().equals(user)).filter(s -> s.get_date().getTime() >= afterTmp.getTime() && s.get_date().getTime() <= beforeTmp.getTime()).map(Log::get_ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Date afterTmp = setDateMin(after);
        Date beforeTmp = setDateMax(before);
        return array.stream().filter(s -> s.get_event().equals(event)).filter(s -> s.get_date().getTime() >= afterTmp.getTime() && s.get_date().getTime() <= beforeTmp.getTime()).map(Log::get_ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Date afterTmp = setDateMin(after);
        Date beforeTmp = setDateMax(before);
        return array.stream().filter(s -> s.get_status().equals(status)).filter(s -> s.get_date().getTime() >= afterTmp.getTime() && s.get_date().getTime() <= beforeTmp.getTime()).map(Log::get_ip).collect(Collectors.toSet());
    }
}
