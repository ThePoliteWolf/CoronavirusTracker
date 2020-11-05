package com.springboot.covidtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.covidtracker.Services.CoronaDataService;
import com.springboot.covidtracker.models.DeathStats;
import com.springboot.covidtracker.models.RecoveredStats;
import com.springboot.covidtracker.models.TotalCasesStats;

@Controller
public class Covid19TrackerController {
	
	@Autowired
	CoronaDataService coronaVirusDataService = new CoronaDataService();
	
	@RequestMapping({"/cases", "/"})
	public String getCovidData(Model model) {
		List<TotalCasesStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<DeathStats> deathStats = coronaVirusDataService.getAllDeaths();
		int totaldeaths = deathStats.stream().mapToInt(stat -> stat.getLatestDeathCases()).sum();
		int totalNewDeaths = deathStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<RecoveredStats> recoveredStats = coronaVirusDataService.getRecoveredStats();
		int totalRecovered = recoveredStats.stream().mapToInt(stat -> stat.getLatestRecoveredCases()).sum();
		int totalNewRecovered = recoveredStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("allStats", coronaVirusDataService.getAllStats());
		model.addAttribute("deathStats", coronaVirusDataService.getAllDeaths());
		model.addAttribute("recoveredStats", coronaVirusDataService.getRecoveredStats());
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("totalDeaths", totaldeaths);
		model.addAttribute("totalNewDeaths", totalNewDeaths);
		model.addAttribute("totalRecovered", totalRecovered);
		model.addAttribute("totalNewRecovered", totalNewRecovered);
		return "home";
	}
	
	@GetMapping("/death")
	public String getDeathStats(Model model) {
		List<TotalCasesStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<DeathStats> deathStats = coronaVirusDataService.getAllDeaths();
		int totaldeaths = deathStats.stream().mapToInt(stat -> stat.getLatestDeathCases()).sum();
		int totalNewDeaths = deathStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<RecoveredStats> recoveredStats = coronaVirusDataService.getRecoveredStats();
		int totalRecovered = recoveredStats.stream().mapToInt(stat -> stat.getLatestRecoveredCases()).sum();
		int totalNewRecovered = recoveredStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("allStats", coronaVirusDataService.getAllStats());
		model.addAttribute("deathStats", coronaVirusDataService.getAllDeaths());
		model.addAttribute("recoveredStats", coronaVirusDataService.getRecoveredStats());
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("totalDeaths", totaldeaths);
		model.addAttribute("totalNewDeaths", totalNewDeaths);
		model.addAttribute("totalRecovered", totalRecovered);
		model.addAttribute("totalNewRecovered", totalNewRecovered);
		return "death";
	}
	
	@GetMapping("/recovered")
	public String getRecoveredStats(Model model) {
		List<TotalCasesStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<DeathStats> deathStats = coronaVirusDataService.getAllDeaths();
		int totaldeaths = deathStats.stream().mapToInt(stat -> stat.getLatestDeathCases()).sum();
		int totalNewDeaths = deathStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<RecoveredStats> recoveredStats = coronaVirusDataService.getRecoveredStats();
		int totalRecovered = recoveredStats.stream().mapToInt(stat -> stat.getLatestRecoveredCases()).sum();
		int totalNewRecovered = recoveredStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("allStats", coronaVirusDataService.getAllStats());
		model.addAttribute("deathStats", coronaVirusDataService.getAllDeaths());
		model.addAttribute("recoveredStats", coronaVirusDataService.getRecoveredStats());
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("totalDeaths", totaldeaths);
		model.addAttribute("totalNewDeaths", totalNewDeaths);
		model.addAttribute("totalRecovered", totalRecovered);
		model.addAttribute("totalNewRecovered", totalNewRecovered);
		return "recovered";
	}
	
}
