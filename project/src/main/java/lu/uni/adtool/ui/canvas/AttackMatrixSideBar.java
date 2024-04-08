package lu.uni.adtool.ui.canvas;

import lu.uni.adtool.ui.PermaDockable;
import bibliothek.util.Path;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class AttackMatrixSideBar extends PermaDockable {
    private JLabel contentLabel;

    public AttackMatrixSideBar(String id, String title) {
        // Pass the unique ID, path, and title for this dockable
        super(new Path("attack_matrix_sidebar"), id, title);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        contentLabel = new JLabel("Select a node to view ATT&CK data");
        JScrollPane scrollPane = new JScrollPane(contentLabel);

        mainPanel.add(scrollPane);
        getContentPane().add(mainPanel); // Use getContentPane() for adding components
        // setting icon for future use
        // this.setTitleIcon(/* Icon for the sidebar */);
        this.setCloseable(true);
    }

    public void updateContent(String content) {
        contentLabel.setText(content);
    }
}