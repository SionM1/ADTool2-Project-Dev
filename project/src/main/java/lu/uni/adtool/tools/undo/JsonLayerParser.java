package lu.uni.adtool.tools.undo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;

import lu.uni.adtool.domains.model.Filters;
import lu.uni.adtool.domains.model.Gradient;
import lu.uni.adtool.domains.model.Layer;
import lu.uni.adtool.domains.model.Layout;
import lu.uni.adtool.domains.model.Technique;
import lu.uni.adtool.domains.model.Versions;

public class JsonLayerParser {

    private Gson gson = new Gson();

    public Layer parseLayer(String jsonFilePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found in resources: " + jsonFilePath);
            }
            InputStreamReader reader = new InputStreamReader(inputStream);
            Layer layer = gson.fromJson(reader, Layer.class);
            // Debugging lines
            System.out.println("Layer Name: " + layer.getName());
            System.out.println("Domain: " + layer.getDomain());
            return layer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // // Method to invoke parsing and debugging
    // public static void main(String[] args) {
    // String jsonFilePath = "JsonFile/ReconnaissanceLayer.json";
    // JsonLayerParser parser = new JsonLayerParser();
    // Layer layer = parser.parseLayer(jsonFilePath);
    // if (layer != null) {
    // // Layer information
    // System.out.println("Layer Name: " + layer.getName());
    // System.out.println("Domain: " + layer.getDomain());

    // // Versions information
    // Versions versions = layer.getVersions();
    // System.out.println("ATT&CK Version: " + versions.getAttack());
    // System.out.println("Navigator Version: " + versions.getNavigator());
    // System.out.println("Layer Version: " + versions.getLayer());

    // // Filters information (if applicable)
    // Filters filters = layer.getFilters();
    // if (filters != null) {
    // System.out.println("Platforms: " + String.join(", ",
    // filters.getPlatforms()));
    // }

    // // Layout information
    // Layout layout = layer.getLayout();
    // System.out.println("Layout: " + layout.getLayout());
    // System.out.println("Aggregate Function: " + layout.getAggregateFunction());
    // System.out.println("Show ID: " + layout.isShowID());
    // System.out.println("Show Name: " + layout.isShowName());

    // // Gradient information
    // Gradient gradient = layer.getGradient();
    // System.out.println("Gradient Colors: " + String.join(", ",
    // gradient.getColors()));
    // System.out.println("Gradient Min Value: " + gradient.getMinValue());
    // System.out.println("Gradient Max Value: " + gradient.getMaxValue());

    // // Techniques information
    // List<Technique> techniques = layer.getTechniques();
    // System.out.println("Techniques Count: " + techniques.size());
    // for (Technique technique : techniques) {
    // System.out.println("\nTechnique ID: " + technique.getTechniqueID());
    // System.out.println("Tactic: " + technique.getTactic());
    // System.out.println("Color: " + technique.getColor());
    // System.out.println("Comment: " + technique.getComment());
    // System.out.println("Enabled: " + technique.isEnabled());
    // System.out.println("Show Subtechniques: " + technique.isShowSubtechniques());
    // // Add more fields as necessary
    // }
    // } else {
    // System.out.println("Layer parsing failed or layer is null.");
    // }
    // }

}
