package com.example.test.charts;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.PlotOptionsColumn;
import com.vaadin.flow.component.charts.model.Stacking;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ChartsView")
public class ChartsView extends VerticalLayout {

    public ChartsView() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();
        conf.setTitle("Reindeer Kills by Predators");
        conf.setSubTitle("Kills Grouped by Counties");

        ListSeries series = new ListSeries("Diameter");
        series.setData(4900, 12100, 12800, 6800, 143000, 125000, 51100, 49500);
        conf.addSeries(series);

        ListSeries series2 = new ListSeries("Diameter2");
        series2.setData(4900, 12100, 12800, 6800, 143000, 125000, 51100, 49500);
        conf.addSeries(series2);

        XAxis xaxis = new XAxis();
        xaxis.setCategories("Mercury", "Venus", "Earth", "Mars", "Jupiter",
                "Saturn", "Uranus", "Neptune");
        xaxis.setTitle("Planet");
        conf.addxAxis(xaxis);

        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Diameter");
        yaxis.getLabels().setFormatter(
                "function() {return Math.floor(this.value/1000) + \'Mm\';}");
        yaxis.getLabels().setStep(2);
        conf.addyAxis(yaxis);

        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setStacking(Stacking.NORMAL);
        conf.setPlotOptions(plotOptionsColumn);

        add(chart);
    }
}
