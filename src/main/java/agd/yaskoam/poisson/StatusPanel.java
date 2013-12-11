package agd.yaskoam.poisson;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author Q-YAA
 */
public class StatusPanel extends BaseComponent {

    @FXML
    private Label nLabel;

    @FXML
    private Label λLabel;

    private DoubleProperty nProperty = new SimpleDoubleProperty();

    private DoubleProperty λProperty = new SimpleDoubleProperty();

    public StatusPanel() {
        nProperty.addListener(new ParametersChangedListener());
        λProperty.addListener(new ParametersChangedListener());
    }

    public DoubleProperty nProperty() {
        return nProperty;
    }

    public DoubleProperty pProperty() {
        return λProperty;
    }

    private class ParametersChangedListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> value, Number number, Number number2) {
            nLabel.setText(Integer.toString(nProperty().intValue()));
            λLabel.setText(pProperty().getValue().toString());
        }
    }
}
