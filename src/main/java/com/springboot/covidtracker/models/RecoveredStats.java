package com.springboot.covidtracker.models;

public class RecoveredStats {
	private String state;
    private String country;
    private int latestRecoveredCases;
    private int diffFromPrevDay;
    
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public int getLatestRecoveredCases() {
		return latestRecoveredCases;
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
	public void setLatestRecoveredCases(int latestRecoveredCases) {
		this.latestRecoveredCases = latestRecoveredCases;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	@Override
	public String toString() {
		return "RecoveredStats [" + (state != null ? "state=" + state + ", " : "")
				+ (country != null ? "country=" + country + ", " : "") + "latestRecoveredCases=" + latestRecoveredCases
				+ ", diffFromPrevDay=" + diffFromPrevDay + "]";
	}
    
    
}
