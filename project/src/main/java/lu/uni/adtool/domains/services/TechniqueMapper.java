package lu.uni.adtool.domains.services;

import lu.uni.adtool.domains.model.Technique;
import lu.uni.adtool.tree.Node;
import lu.uni.adtool.tree.ADTNode; // Import ADTNode or your specific subclass
import lu.uni.adtool.tree.GuiNode;
import java.util.List;
import java.util.ArrayList;

public class TechniqueMapper {
    /**
     * Maps techniques to nodes based on matching node labels to technique IDs.
     *
     * @param nodes      the list of nodes in the attack tree
     * @param techniques the list of techniques parsed from the ATT&CK data
     */
    public void mapTechniquesToNodes(List<Node> nodes, List<Technique> techniques) {
        for (Node node : nodes) {
            String nodeLabel = node.getComment();
            for (Technique technique : techniques) {
                if (nodeLabel.equalsIgnoreCase(technique.getTechniqueID())) {
                    node.setTechnique(technique);
                    break; // Break if a match is found
                }
            }
        }
    }
}