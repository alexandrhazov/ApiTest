package api.Discover.SupportedExpertise.utils;

public class Status {
    public int code;
    public String msg;
    public String request_uuid;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getRequest_uuid() {
        return request_uuid;
    }

    public Status() {
    }

    public Status(int code, String msg, String request_uuid) {
        this.code = code;
        this.msg = msg;
        this.request_uuid = request_uuid;
    }
}
