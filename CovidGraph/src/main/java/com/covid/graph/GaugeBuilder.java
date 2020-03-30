package com.covid.graph;

import io.prometheus.client.Gauge;

public class GaugeBuilder {

	public static Gauge gauge;

	public static Gauge register(String key, String label) {
		gauge = Gauge.build().name(key).help(key).labelNames(label).register();
		return gauge;
	}

	public static void addLabel(String label, double value) {
		gauge.labels(label).set(value);
	}

	public static void setGauge(Gauge g)
	{
		gauge = g;
	}
	
	public static void updateValues(CoronaData coronaData) {
		gauge.labels(Constants.CONFIRMED_CASES).set(coronaData.getConfirmedCases());
		gauge.labels(Constants.CASES_PER_MILLION).set(coronaData.getCasesPerMillion());
		gauge.labels(Constants.RECOVERED_CASES).set(coronaData.getRecovered());
		gauge.labels(Constants.DEATH_CASES).set(coronaData.getDeaths());
	}

}
