package com.covid.graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.covid.graph.*;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

public class CovidDataExporterApplication {

	public static void main(String[] args) {
		try {

			TimerTask task = new CoronaDataScraper();
			TimerFactory.createTimerAndExecute(Constants.ZERO, Constants.MILLI_SECONDS, task);
			HTTPServer server = new HTTPServer(8800);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class CoronaDataScraper extends TimerTask {

	public void run() {
		Document doc = null;
		ArrayList<String> alist = new ArrayList<String>( 
				Arrays.asList(
						Constants.CASES_PER_MILLION,
						Constants.CONFIRMED_CASES,
						Constants.DEATH_CASES,
						Constants.RECOVERED_CASES
				)
		);
		
		try {
			doc = Jsoup.connect(Constants.COVID_URL).get();

			Element table = doc.select("table").get(1);
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size(); i++) {
				// first row is the col names so skip it.
				Element row = rows.get(i);
				Elements cols = row.select("td");
				CoronaData coronaData = new CoronaData(cols.get(0).text().toString(), cols.get(1).text().toString(),
						cols.get(2).text().toString(), cols.get(3).text().toString(), cols.get(4).text().toString());

				String countryName = cols.get(0).text().toString().replace(" ", "_").replaceAll("[^a-zA-Z0-9_]", "");
				Gauge gauge = null;

				for(String key : alist)
				{
					gauge = (Gauge) MetricList.getInstance().getMetric(key);
					if (gauge == null)
						gauge = GaugeBuilder.register(key, Constants.COUNTRY);
					else
						GaugeBuilder.setGauge(gauge);
					GaugeBuilder.addLabel(countryName, coronaData.get(key));
					MetricList.getInstance().addMetric(key, gauge);
					
				}
				
				
				// Individual Gauge Per Country
				gauge = (Gauge) MetricList.getInstance().getMetric(countryName);
				if (gauge == null) {
					gauge = GaugeBuilder.register(countryName, countryName);
				}
				GaugeBuilder.updateValues(coronaData);
				MetricList.getInstance().addMetric(countryName, gauge);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
