package com.covid.graph;

import java.util.concurrent.*;

public class MetricList {

	private static MetricList metricList = null;
	private static ConcurrentHashMap<String, Object> metrics = null;

	private MetricList() {
		metrics = new ConcurrentHashMap<>();
	}

	public static MetricList getInstance() {
		if (metricList == null)
			metricList = new MetricList();
		return metricList;
	}

	public void addMetric(String name, Object g) {
		metrics.putIfAbsent(name, g);
	}

	public Object getMetric(String name) {
		return metrics.get(name);
	}
}
