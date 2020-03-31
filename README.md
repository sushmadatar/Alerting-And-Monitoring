# Alerting-And-Monitoring Projects

Covid-19 Prometheus Exporter

This is a [custom Prometheus Exporter](https://prometheus.io/docs/instrumenting/writing_exporters/) using [Java Library](https://github.com/prometheus/client_java).

It uses JSOUP to scrape data from web-page. The scraped data is then converted into metrics. These metrics are periodically scraped by [Prometheus](https://prometheus.io/) and stored as time series data.

At the end of the monitoring tool chain is the [Grafana](https://grafana.com/). Using Grafana, we can represent data in different types of graphs. Grafana requires time-series data as input, which is provided by Prometheus.

Below is the Grafana dashboard for Covid-19 data.

[[//images//Covid.PNG|Grafana Dashboard]]

