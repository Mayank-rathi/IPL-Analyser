package iplCricketGame;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {
    List<CricketCsvDto> iplCSVList;
    Map<SortField, Comparator<CricketCsvDto>> sortedMap;
    Map<String, CricketCsvDto> cricketCsvDtoMap;

    public enum Cricket {
        RUNS, WICKETS;
    }

    public IPLAnalyser() {
        this.iplCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();

        this.sortedMap.put(SortField.PLAYER_NAME, Comparator.comparing(ipldata -> ipldata.playerName));
        this.sortedMap.put(SortField.AVERAGE, Comparator.comparing(ipldata -> ipldata.average));
        this.sortedMap.put(SortField.STRIKE_RATE, Comparator.comparing(ipldata -> ipldata.strikeRate));
        this.sortedMap.put(SortField.NO_OF_4S_AND_6S, Comparator.comparing(ipldata -> ipldata.fours + ipldata.sixes));

        Comparator<CricketCsvDto> foursAndSix = Comparator.comparing(ipldata -> ipldata.fours + ipldata.fours);
        this.sortedMap.put(SortField.STRIKE_RATE_WITH_6S_4S, foursAndSix.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<CricketCsvDto> average = Comparator.comparing(ipldata -> ipldata.average);
        this.sortedMap.put(SortField.GREAT_STRIKE_RATE_AND_BEST_AVG, average.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<CricketCsvDto> runsWithAvg = Comparator.comparing(ipldata -> ipldata.runs);
        this.sortedMap.put(SortField.BEST_RUNS_WITH_BEST_AVG, runsWithAvg.thenComparing(ipldata -> ipldata.average));
        this.sortedMap.put(SortField.ECONOMY_RATE, Comparator.comparing(ipldata -> ipldata.economyRate));
        Comparator<CricketCsvDto> bestStrikeRateWith4And5Wickets = Comparator.comparing(ipldata -> ipldata.fourWickets + ipldata.fiveWickets);
        this.sortedMap.put(SortField.STRIKE_RATE_WITH_4_AND_5_WICKET, bestStrikeRateWith4And5Wickets.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<CricketCsvDto> bestBowlingAverageWithBestStrikeRate = Comparator.comparing(ipldata -> ipldata.average);
        this.sortedMap.put(SortField.BEST_BOWLING_AVG_WITH_BEST_STRIKE_RATE, bestBowlingAverageWithBestStrikeRate.thenComparing(ipldata -> ipldata.strikeRate));
        Comparator<CricketCsvDto> maximumWicketsWithBestBowlingAverage = Comparator.comparing(ipldata -> ipldata.wickets);
        this.sortedMap.put(SortField.MAX_WICKETS_WITH_BEST_BOWLING_AVERAGE, maximumWicketsWithBestBowlingAverage.thenComparing(ipldata -> ipldata.average));
    }


    public int loadIplData(Cricket cricket, String... csvFilePath) {

        cricketCsvDtoMap = IplAdapterFactory.getIPLData(cricket, csvFilePath);

        return cricketCsvDtoMap.size();

    }

    public String getSortedCricketData(SortField sortedField) {
        iplCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
        if (iplCSVList == null || iplCSVList.size() == 0) {
            throw new IPLAnalyserException("No Data", IPLAnalyserException.ExceptionType.IPL_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(iplCSVList);
        String sortedDataJson = new Gson().toJson(iplCSVList);
        return sortedDataJson;
    }

    private void sort(Comparator<CricketCsvDto> iplComparator) {
        for (int i = 0; i < iplCSVList.size() - 1; i++) {
            for (int j = 0; j < iplCSVList.size() - i - 1; j++) {
                CricketCsvDto run1 = this.iplCSVList.get(j);
                CricketCsvDto run2 = this.iplCSVList.get(j + 1);
                if (iplComparator.compare(run1, run2) > 0) {
                    iplCSVList.set(j, run2);
                    iplCSVList.set(j + 1, run1);
                }
            }
        }
    }

}



