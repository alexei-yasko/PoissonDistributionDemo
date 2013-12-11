package agd.yaskoam.poisson;

import java.net.URL;
import java.util.ResourceBundle;

import agd.yaskoam.poisson.support.DoubleTextFieldConstraint;
import agd.yaskoam.poisson.support.IntTextFieldConstraint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * @author Q-YAA
 */
public class SettingsPanel extends BaseComponent {

    @FXML
    private Label nSliderMaxValueLabel;

    @FXML
    private Label λSliderMaxValueLabel;

    @FXML
    private TextField maxNTextField;

    @FXML
    private TextField maxΛTextField;

    @FXML
    private Slider nSlider;

    @FXML
    private Slider λSlider;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        nSliderMaxValueLabel.textProperty().bind(nSlider.maxProperty().asString());
        λSliderMaxValueLabel.textProperty().bind(λSlider.maxProperty().asString());

        nSlider.setBlockIncrement(1);

        setTextFieldsEventHandlers();
    }

    public DoubleProperty pProperty() {
        return λSlider.valueProperty();
    }

    public DoubleProperty nProperty() {
        return nSlider.valueProperty();
    }

    private void setTextFieldsEventHandlers() {
        maxNTextField.textProperty().addListener(new IntTextFieldConstraint(maxNTextField));
        maxNTextField.textProperty().addListener(new MaxDoubleTextFieldConstraint(50, maxNTextField));
        maxNTextField.textProperty().addListener(new MaxNFieldOnChangeListener());

        maxΛTextField.textProperty().addListener(new DoubleTextFieldConstraint(maxΛTextField));
        maxΛTextField.textProperty().addListener(new MaxPFieldOnChangeListener());
    }

    private class MaxNFieldOnChangeListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> value, String s, String s2) {
            if (!s2.equals("")) {
                nSlider.setMax(Double.parseDouble(s2));
            }
        }
    }

    private class MaxPFieldOnChangeListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> value, String s, String s2) {
            if (!s2.equals("")) {
                λSlider.setMax(Double.parseDouble(s2));
            }
        }
    }

    private class MaxDoubleTextFieldConstraint implements ChangeListener<String> {

        private double max;

        private TextField textField;

        public MaxDoubleTextFieldConstraint(double max, TextField textField) {
            this.max = max;
            this.textField = textField;
        }

        @Override
        public void changed(ObservableValue<? extends String> value, String s, String s2) {

            if (!s2.equals("") && Double.parseDouble(s2) > max) {
                textField.setText(s);
            }
        }
    }
}
