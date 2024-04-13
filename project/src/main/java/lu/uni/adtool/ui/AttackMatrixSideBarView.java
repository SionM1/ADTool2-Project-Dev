package lu.uni.adtool.ui;

import bibliothek.util.Path;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lu.uni.adtool.tools.Options;
import lu.uni.adtool.tools.IconFactory;
import javax.swing.ImageIcon;
import lu.uni.adtool.ui.canvas.AbstractTreeCanvas;
import lu.uni.adtool.ui.canvas.AbstractDomainCanvas;
import lu.uni.adtool.domains.rings.Ring;
import lu.uni.adtool.tools.undo.JsonLayerParser;
import lu.uni.adtool.domains.model.Layer;
import lu.uni.adtool.domains.model.Technique;
import lu.uni.adtool.tree.Node;

public class AttackMatrixSideBarView extends PermaDockable {
    private DefaultTableModel tableModel;
    private JTable table;
    private JsonLayerParser jsonLayerParser;
    private List<Layer> layers;

    public static final String ID_VIEW = "attack_matrix_view";

    public AttackMatrixSideBarView() {
        super(new Path(ID_VIEW), ID_VIEW, Options.getMsg("windows.attackMatrix.txt"));
        initLayout();
        ImageIcon icon = new IconFactory().createImageIcon("/icons/layout.png",
                Options.getMsg("windows.attackMatrix.txt"));
        this.setTitleIcon(icon);

        // Initialize JsonLayerParser and load the layers
        jsonLayerParser = new JsonLayerParser();
        List<String> jsonFilePaths = Arrays.asList(
                "JsonFile/ReconnaissanceLayer.json",
                "JsonFile/ResourceDevelopmentLayer.json",
                "JsonFile/InitialAccessLayer.json",
                "JsonFile/CollectionLayer.json",
                "JsonFile/CommandAndControlLayer.json",
                "JsonFile/ExfiltrationLayer.json",
                "JsonFile/DefenseEvasionLayer.json",
                "JsonFile/CredentialAccessLayer.json",
                "JsonFile/DiscoveryLayer.json",
                "JsonFile/LateralMovementLayer.json",
                "JsonFile/PersistenceLayer.json",
                "JsonFile/PrivilegeEscalationLayer.json",
                "JsonFile/ExecutionLayer.json");

        layers = jsonLayerParser.parseLayers(jsonFilePaths);
    }

    private void initLayout() {
        // Initialize table
        initTable();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder(Options.getMsg("windows.attackMatrix.title"))));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(panel);
    }

    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Technique ID");
        tableModel.addColumn("Tactic");
        tableModel.addColumn("Color");
        tableModel.addColumn("Comment");
        tableModel.addColumn("Enabled");
        tableModel.addColumn("Show Subtechniques");
        table = new JTable(tableModel);
        table.setFont(new Font("Sans", Font.TRUETYPE_FONT, 13));
        table.setFillsViewportHeight(true); // Fill the viewport height
    }

    public void assignCanvas(AbstractTreeCanvas canvas) {
        if (canvas instanceof AbstractDomainCanvas) {
            AbstractDomainCanvas<Ring> domainCanvas = (AbstractDomainCanvas<Ring>) canvas;
            List<Node> selectedNodes = domainCanvas.collectSelectedNodes();
            tableModel.setRowCount(0); // Clear previous entries
            displayLayerInfo(selectedNodes);
        }
    }

    private void displayLayerInfo(List<Node> selectedNodes) {
        tableModel.setRowCount(0); // Clear previous entries
        for (Node selectedNode : selectedNodes) {
            for (Layer layer : layers) {
                for (Technique technique : layer.getTechniques()) {
                    if (technique.getTechniqueID().equals(selectedNode.getComment())) {
                        tableModel.addRow(new Object[] {
                                technique.getTechniqueID(),
                                technique.getTactic(),
                                technique.getColor(),
                                technique.getComment(),
                                technique.isEnabled(),
                                technique.isShowSubtechniques()
                        });
                    }
                }
            }
        }
    }
}