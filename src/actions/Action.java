package actions;

public abstract class Action {
    final private String type;
    final private String page;

    public Action(final String type, final String page) {
        this.type = type;
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public String getPage() {
        return page;
    }

    public abstract app.Error apply();
}



