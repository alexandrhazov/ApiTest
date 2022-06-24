package api.Discover.SupportedLanguagesPairs.utils;

public class Target {
    public String name;
    public String code;
    public String slug;
    public String availability;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSlug() {
        return slug;
    }

    public String getAvailability() {
        return availability;
    }

    public Target() {
    }

    public Target(String name, String code, String slug, String availability) {
        this.name = name;
        this.code = code;
        this.slug = slug;
        this.availability = availability;
    }
}
