package lu.uni.adtool.domains.model;

import java.util.List;

public class Gradient {
    private List<String> colors;
    private int minValue;
    private int maxValue;

    // Getter for colors
    public List<String> getColors() {
        return colors;
    }

    // Setter for colors
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    // Getter for minValue
    public int getMinValue() {
        return minValue;
    }

    // Setter for minValue
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    // Getter for maxValue
    public int getMaxValue() {
        return maxValue;
    }

    // Setter for maxValue
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

}
