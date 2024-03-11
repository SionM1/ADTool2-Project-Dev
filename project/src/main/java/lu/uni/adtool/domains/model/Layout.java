package lu.uni.adtool.domains.model;

public class Layout {
    private String layout;
    private String aggregateFunction;
    private boolean showID;
    private boolean showName;

    // Getter for layout
    public String getLayout() {
        return layout;
    }

    // Setter for layout
    public void setLayout(String layout) {
        this.layout = layout;
    }

    // Getter for aggregateFunction
    public String getAggregateFunction() {
        return aggregateFunction;
    }

    // Setter for aggregateFunction
    public void setAggregateFunction(String aggregateFunction) {
        this.aggregateFunction = aggregateFunction;
    }

    // Getter for showID
    public boolean isShowID() {
        return showID;
    }

    // Setter for showID
    public void setShowID(boolean showID) {
        this.showID = showID;
    }

    // Getter for showName
    public boolean isShowName() {
        return showName;
    }

    // Setter for showName
    public void setShowName(boolean showName) {
        this.showName = showName;
    }
}
