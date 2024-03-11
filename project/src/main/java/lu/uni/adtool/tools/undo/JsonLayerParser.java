package lu.uni.adtool.tools.undo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import lu.uni.adtool.domains.model.Layer;

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

    // Method to invoke parsing and debugging
    public static void main(String[] args) {
        String jsonFilePath = "JsonFile/ReconnaissanceLayer.json";
        JsonLayerParser parser = new JsonLayerParser();
        Layer layer = parser.parseLayer(jsonFilePath);
        // Assuming there's at least one technique
        if (layer != null && layer.getTechniques() != null && !layer.getTechniques().isEmpty()) {
            System.out.println("First Technique ID: " + layer.getTechniques().get(0).getTechniqueID());
        }
    }
}
