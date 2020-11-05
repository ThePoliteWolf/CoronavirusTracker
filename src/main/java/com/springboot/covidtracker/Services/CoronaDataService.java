package com.springboot.covidtracker.Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.covidtracker.models.DeathStats;
import com.springboot.covidtracker.models.RecoveredStats;
import com.springboot.covidtracker.models.TotalCasesStats;

@Service
public class CoronaDataService {
	
	private String VIRUS_DATA_URl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private String VIRUS_DEATH_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private String VIRUS_RECOVERED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	private List<TotalCasesStats> allStats = new ArrayList<>();
	private List<DeathStats> deathStats = new ArrayList<>();
	private List<RecoveredStats> recoveredStats = new ArrayList<>();
	
	public List<TotalCasesStats> getAllStats() {
		return this.allStats;
	}
	
	public List<DeathStats> getAllDeaths() {
		return this.deathStats;
	}
	
	public List<RecoveredStats> getRecoveredStats() {
		return this.recoveredStats;
	}
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URl)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader CSVreader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(CSVreader);
		List<TotalCasesStats> tempdata = new ArrayList<>();
		for (CSVRecord record : records) {
			
		    String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    Integer latestTotalCases = (int)Double.parseDouble(record.get(record.size() - 1));
		    String PrevDay = record.get(record.size() - 2);
		    Integer diffFromPrevDay = Integer.valueOf(latestTotalCases) - Integer.valueOf(PrevDay);
		    
		    TotalCasesStats locationstats = new TotalCasesStats();
		    locationstats.setCountry(country);
		    locationstats.setState(state);
		    locationstats.setLatestTotalCases(latestTotalCases);
		    locationstats.setDiffFromPrevDay(diffFromPrevDay);
		    tempdata.add(locationstats);
		}
		this.allStats = tempdata;
	}
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	void fecthDeaths() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DEATH_DATA_URL)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader CSVreader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(CSVreader);
		List<DeathStats> tempdeaths = new ArrayList<>();
		for(CSVRecord record : records){
			String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    Integer latestDeathCases = (int)Double.parseDouble((record.get(record.size() - 1)));
		    String PrevDay = record.get(record.size() - 2);
		    Integer diffFromPrevDay = Integer.valueOf(latestDeathCases) - Integer.valueOf(PrevDay);
		    
		    DeathStats deathstats = new DeathStats();
		    deathstats.setCountry(country);
		    deathstats.setState(state);
		    deathstats.setLatestDeathCases(latestDeathCases);
		    deathstats.setDiffFromPrevDay(diffFromPrevDay);
		    tempdeaths.add(deathstats);
		}
		this.deathStats = tempdeaths;
	}
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	void recoveredStats() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_RECOVERED_URL)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader CSVreader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(CSVreader);
		List<RecoveredStats> temprecovered = new ArrayList<>();
		for(CSVRecord record : records){
			String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    Integer latestRecoveredCases = (int)Double.parseDouble(record.get(record.size() - 1));
		    String PrevDay = record.get(record.size() - 2);
		    Integer diffFromPrevDay = Integer.valueOf(latestRecoveredCases) - Integer.valueOf(PrevDay);
		    
		    RecoveredStats recoveredstats = new RecoveredStats();
		    recoveredstats.setCountry(country);
		    recoveredstats.setState(state);
		    recoveredstats.setLatestRecoveredCases(latestRecoveredCases);
		    recoveredstats.setDiffFromPrevDay(diffFromPrevDay);
		    
		    temprecovered.add(recoveredstats);
		}
		this.recoveredStats = temprecovered;
	}
	
}
