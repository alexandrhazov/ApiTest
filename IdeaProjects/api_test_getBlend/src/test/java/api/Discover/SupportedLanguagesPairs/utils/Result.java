package api.Discover.SupportedLanguagesPairs.utils;

import java.util.ArrayList;

public class Result {
    public Source source;
    public ArrayList<Target> targets;

    public Source getSource() {
        return source;
    }

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public Result() {
    }

    public Result(Source source, ArrayList<Target> targets) {
        this.source = source;
        this.targets = targets;
    }
}
