package actions;

public abstract class OnPageAction extends Action {
    private String feature;

    public OnPageAction(final String type, final String page, final String feature) {
        super(type, page);
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }
}
