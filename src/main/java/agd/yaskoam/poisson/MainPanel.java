package agd.yaskoam.poisson;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

/**
 * @author Q-YAA
 */
public class MainPanel extends BaseComponent {

    @FXML
    private StatusPanel statusPanel;

    @FXML
    private SettingsPanel settingsPanel;

    @FXML
    private ProbabilityFunctionChartPanel probabilityChartPanel;

    @FXML
    private DistributionFunctionChartPanel distributionChartPanel;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {

        probabilityChartPanel.nProperty().bind(settingsPanel.nProperty());
        probabilityChartPanel.λProperty().bind(settingsPanel.pProperty());

        distributionChartPanel.nProperty().bind(settingsPanel.nProperty());
        distributionChartPanel.λProperty().bind(settingsPanel.pProperty());

        statusPanel.nProperty().bind(settingsPanel.nProperty());
        statusPanel.pProperty().bind(settingsPanel.pProperty());
    }
}
