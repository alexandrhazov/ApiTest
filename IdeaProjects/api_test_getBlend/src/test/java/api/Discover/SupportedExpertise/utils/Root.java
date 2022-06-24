package api.Discover.SupportedExpertise.utils;

import java.util.ArrayList;

public class Root {
    public Status status;
    public ArrayList<Result> results;
    public ArrayList<Object> errors;

    public Root() {
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public ArrayList<Object> getErrors() {
        return errors;
    }

    public Root(Status status, ArrayList<Result> results, ArrayList<Object> errors) {
        this.status = status;
        this.results = results;
        this.errors = errors;
    }
}
