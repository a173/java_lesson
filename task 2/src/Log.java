import com.sun.tools.corba.se.idl.constExpr.Equal;

import java.util.Date;
import java.util.Objects;

public class Log {
    private String _ip;
    private String _user;
    private Date   _date;
    private Event _event;
    private Status _status;

    public Log(String ip, String user, Date date, String event, String status) {
        _ip = ip;
        _user = user;
        _date = date;
        _event = new Event(event);
        _status = new Status(status);
    }

    public  String toString() {
        return (_ip + "\t" + _user + "\t" + _date + "\t" + _event + "\t" + _status);
    }

    public Status get_status() {
        return _status;
    }

    public Event get_event() {
        return _event;
    }

    public Date get_date() {
        return _date;
    }

    public String get_ip() {
        return _ip;
    }

    public String get_user() {
        return _user;
    }
}

class Event {
    private String _event;

    public Event(String event) {
        _event = event;
    }

    public String get_event() {
        return _event;
    }

    @Override
    public String toString() {
        return _event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(_event, event._event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_event);
    }
}

class Status {
    private String _status;

    public Status(String status) {
        _status = status;
    }

    public String get_status() {
        return _status;
    }

    @Override
    public String toString() {
        return _status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(_status, status._status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_status);
    }
}
