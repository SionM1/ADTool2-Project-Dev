package lu.uni.adtool.domains.model;

public class Technique {
    private String techniqueID;
    private String tactic;
    private String color;
    private String comment;
    private boolean enabled;
    private boolean showSubtechniques;
    private String description;
    private String url;
    private String mitigation;

    // Constructor for testing
    public Technique(String techniqueID, String tactic) {
        this.techniqueID = techniqueID;
        this.tactic = tactic;
    }

    // Getter and setter for techniqueID
    public String getTechniqueID() {
        return techniqueID;
    }

    public void setTechniqueID(String techniqueID) {
        this.techniqueID = techniqueID;
    }

    // Getter and setter for tactic
    public String getTactic() {
        return tactic;
    }

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }

    // Getter and setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Getter and setter for comment
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // Getter and setter for enabled
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Getter and setter for showSubtechniques
    public boolean isShowSubtechniques() {
        return showSubtechniques;
    }

    public void setShowSubtechniques(boolean showSubtechniques) {
        this.showSubtechniques = showSubtechniques;
    }

    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }
}
