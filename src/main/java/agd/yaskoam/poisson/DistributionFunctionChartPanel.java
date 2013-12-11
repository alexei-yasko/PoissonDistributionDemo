package agd.yaskoam.poisson;

import org.apache.commons.math3.distribution.PoissonDistribution;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * @author Q-YAA
 */
public class DistributionFunctionChartPanel extends BaseComponent {

    @FXML
    private ScatterChart<String, Double> distributionChart;

    private DoubleProperty nProperty = new SimpleDoubleProperty();

    private DoubleProperty λProperty = new SimpleDoubleProperty();

    public DistributionFunctionChartPanel() {
        nProperty.addListener(new ParametersChangedListener());
        λProperty.addListener(new ParametersChangedListener());
    }

    public DoubleProperty nProperty() {
        return nProperty;
    }

    public DoubleProperty λProperty() {
        return λProperty;
    }

    private void updateChart(int n, double λ) {
        PoissonDistribution poissonDistribution = new PoissonDistribution(Math.max(λ, 0.0001));

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i <= n; i++) {

            double probability = poissonDistribution.cumulativeProbability(i);
            series.getData().add(new XYChart.Data<>(Integer.toString(i), probability));
        }

        distributionChart.getData().clear();
        distributionChart.getData().add(series);
    }

    private class ParametersChangedListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> value, Number number, Number number2) {
            updateChart(nProperty.intValue(), λProperty.get());
        }
    }
}
