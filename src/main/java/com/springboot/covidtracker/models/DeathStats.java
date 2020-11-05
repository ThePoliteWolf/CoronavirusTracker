package com.springboot.covidtracker.models;

public class DeathStats {
	private String state;
    private String country;
    private int latestDeathCases;
    private int diffFromPrevDay;
    
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public int getLatestDeathCases() {
		return latestDeathCases;
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setLatestDeathCases(int latestDeathCases) {
		this.latestDeathCases = latestDeathCases;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	@Override
	public String toString() {
		return "DeathStats [" + (state != null ? "state=" + state + ", " : "")
				+ (country != null ? "country=" + country + ", " : "") + "latestDeathCases=" + latestDeathCases
				+ ", diffFromPrevDay=" + diffFromPrevDay + "]";
	}
}
