package lu.uni.adtool.ui;

import bibliothek.util.Path;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import lu.uni.adtool.tools.Options;
import lu.uni.adtool.tools.IconFactory;
import javax.swing.ImageIcon;
import lu.uni.adtool.ui.canvas.AbstractTreeCanvas;
import lu.uni.adtool.ui.canvas.AbstractDomainCanvas;
import lu.uni.adtool.domains.rings.Ring;

public class AttackMatrixSideBarView extends PermaDockable {
    private JLabel text;

    public static final String ID_VIEW = "attack_matrix_view";

    public AttackMatrixSideBarView() {
        super(new Path(ID_VIEW), ID_VIEW, Options.getMsg("windows.attackMatrix.txt"));
        initLayout();
        ImageIcon icon = new IconFactory().createImageIcon("/icons/layout.png",
                Options.getMsg("windows.attackMatrix.txt"));
        this.setTitleIcon(icon);
    }

    private void initLayout() {
        JPanel panel = new JPanel(new BorderLayout());
        text = new JLabel(Options.getMsg("windows.attackMatrix.initialText")) {
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }

            public Dimension getMinimumSize() {
                return new Dimension(400, 300);
            }

            public Dimension getMaximumSize() {
                return new Dimension(400, 300);
            }

            private static final long serialVersionUID = 1L;
        };
        text.setVerticalAlignment(SwingConstants.TOP);
        text.setFont(new Font("Sans", Font.TRUETYPE_FONT, 13));
        text.setHorizontalAlignment(SwingConstants.LEFT);

        JScrollPane matrixPane = new JScrollPane(text);
        matrixPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder(Options.getMsg("windows.attackMatrix.title"))));
        matrixPane.setAutoscrolls(true);
        panel.add(matrixPane);
        add(panel);
    }

    /**
     * Updates the content of the Attack Matrix view.
     *
     * @param content The new content to display.
     */
    public void updateContent(String content) {
        text.setText(content);
    }

    /**
     * Assign canvas to this view.
     *
     * @param canvas the canvas to be assigned.
     */
    @SuppressWarnings("unchecked")
    public void assignCanvas(AbstractTreeCanvas canvas) {
        if (canvas instanceof AbstractDomainCanvas) {
            text.setText(((AbstractDomainCanvas<Ring>) canvas).getDomain().getDescription());
        } else {
            text.setText(Options.getMsg("windows.attackMatrix.nochosen"));
        }
    }
}