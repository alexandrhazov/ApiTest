package api.Discover.SupportedLanguages.utils;

public class Result {
    public String name;
    public String code;
    public int id;
    public String slug;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public Result() {
    }

    public Result(String name, String code, int id, String slug) {
        this.name = name;
        this.code = code;
        this.id = id;
        this.slug = slug;
    }
}
