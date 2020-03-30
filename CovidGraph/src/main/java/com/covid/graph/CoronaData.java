package com.covid.graph;

public class CoronaData {

	private String country;
	private double confirmedCases;
	private double recovered;
	private double deaths;
	private double casesPerMillion;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getConfirmedCases() {
		return confirmedCases;
	}

	public void setConfirmedCases(double confirmedCases) {
		this.confirmedCases = confirmedCases;
	}

	public double getRecovered() {
		return recovered;
	}

	public void setRecovered(double recovered) {
		this.recovered = recovered;
	}

	public double getDeaths() {
		return deaths;
	}

	public void setDeaths(double deaths) {
		this.deaths = deaths;
	}

	public double getCasesPerMillion() {
		return casesPerMillion;
	}

	public void setCasesPerMillion(double casesPerMillion) {
		this.casesPerMillion = casesPerMillion;
	}

	public double get(String key)
	{
		switch (key)
		{
			case Constants.CONFIRMED_CASES:
				return this.confirmedCases;
			case Constants.CASES_PER_MILLION:
				return this.casesPerMillion;
			case Constants.DEATH_CASES:
				return this.deaths;
			case Constants.RECOVERED_CASES:
				return this.recovered;
		}
		return 0;
	}
	
	public CoronaData(String country, String confirmedCases, String casesPerMillion, String resovered, String deaths) {
		super();
		this.country = country;
		this.confirmedCases = Double.parseDouble(sanitize(confirmedCases));
		this.recovered = Double.parseDouble(sanitize(resovered));
		this.deaths = Double.parseDouble(sanitize(deaths));
		this.casesPerMillion = Double.parseDouble(sanitize(casesPerMillion));
	}

	public String sanitize(String s) {
		return s.replaceAll(",", "").replace("—", "0");
	}

	@Override
	public String toString() {
		return String.format("Country %s, Confirmed cases %s, Recovered %s, Deaths %s, Cases per Million people %s",
				this.country, this.confirmedCases, this.recovered, this.deaths, this.casesPerMillion);

	}

}
