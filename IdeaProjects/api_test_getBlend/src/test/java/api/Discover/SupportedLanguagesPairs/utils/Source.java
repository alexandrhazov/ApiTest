package api.Discover.SupportedLanguagesPairs.utils;

public class Source {
    public String name;
    public String code;
    public String slug;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSlug() {
        return slug;
    }

    public Source() {
    }

    public Source(String name, String code, String slug) {
        this.name = name;
        this.code = code;
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
