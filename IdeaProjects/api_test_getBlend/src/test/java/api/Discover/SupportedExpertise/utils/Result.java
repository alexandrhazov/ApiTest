package api.Discover.SupportedExpertise.utils;

public class Result {
    public String name;
    public String code;
    public int expertise_id;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getExpertise_id() {
        return expertise_id;
    }

    public Result() {
    }

    public Result(String name, String code, int expertise_id) {
        this.name = name;
        this.code = code;
        this.expertise_id = expertise_id;
    }
}
