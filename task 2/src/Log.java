import java.util.Date;

public class Log {
    private String _ip;
    private String _user;
    private Date   _date;
    private String _event;
    private String _status;

    public Log(String ip, String user, Date date, String event, String status) {
        _ip = ip;
        _user = user;
        _date = date;
        _event = event;
        _status = status;
    }

    public  String toString() {
        return (_ip + "\t" + _user + "\t" + _date + "\t" + _event + "\t" + _status);
    }

    public String get_event() {
        return _event;
    }

    public String get_status() {
        return _status;
    }
}
