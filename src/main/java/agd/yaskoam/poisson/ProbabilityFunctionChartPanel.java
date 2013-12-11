package agd.yaskoam.poisson;

import org.apache.commons.math3.distribution.PoissonDistribution;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * @author Q-YAA
 */
public class ProbabilityFunctionChartPanel extends BaseComponent {

    @FXML
    private BarChart<String, Double> probabilityChart;

    private DoubleProperty nProperty = new SimpleDoubleProperty();

    private DoubleProperty λProperty = new SimpleDoubleProperty();

    public ProbabilityFunctionChartPanel() {
        nProperty.addListener(new ParametersChangedListener());
        λProperty.addListener(new ParametersChangedListener());
    }

    public DoubleProperty nProperty() {
        return nProperty;
    }

    public DoubleProperty λProperty() {
        return λProperty;
    }

    private void updateChart(int n, double p) {
        PoissonDistribution poissonDistribution = new PoissonDistribution(Math.max(p, 0.0001));

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i <= n; i++) {

            double probability = poissonDistribution.probability(i);
            series.getData().add(new XYChart.Data<>(Integer.toString(i), probability));
        }

        probabilityChart.getData().clear();
        probabilityChart.getData().add(series);
    }

    private class ParametersChangedListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> value, Number number, Number number2) {
            updateChart(nProperty.intValue(), λProperty.get());
        }
    }
}
