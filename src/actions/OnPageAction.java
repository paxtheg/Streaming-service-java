package actions;

public abstract class OnPageAction extends Action {
    private String feature;

    public OnPageAction(String type, String page, String feature) {
        super(type, page);
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
