package iplCricketGame;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class IPLAnalyser {
    List<IPLBatsmanCSV> iplCSVList = null;
    Map<SortField, Comparator<IPLBatsmanCSV>> sortedMap;

    public IPLAnalyser() {
        this.iplCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();

        this.sortedMap.put(SortField.PLAYER_NAME, Comparator.comparing(ipldata -> ipldata.playeName));
        this.sortedMap.put(SortField.AVERAGE, Comparator.comparing(ipldata -> ipldata.battingAvg));
        this.sortedMap.put(SortField.STRIKE_RATE, Comparator.comparing(ipldata -> ipldata.strikRate));
        this.sortedMap.put(SortField.NO_OF_4S_AND_6S, Comparator.comparing(ipldata -> ipldata.fours + ipldata.sixes));
        this.sortedMap.put(SortField.STRIKE_RATE_WITH_6S_4S, Comparator.comparing(ipldata -> ipldata.strikRate + ipldata.sixes + ipldata.fours));
        this.sortedMap.put(SortField.GREAT_STRIKE_RATE_AND_BEST_AVG, Comparator.comparing(ipldata -> ipldata.strikRate + ipldata.battingAvg));
    }


    public int loadIplData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IPLBatsmanCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLBatsmanCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLBatsmanCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLBatsmanCSV> censusCSVIterator = csvToBean.iterator();
            int numOfEateries = 0;
            while (censusCSVIterator.hasNext()) {
                numOfEateries++;
                IPLBatsmanCSV runsData = censusCSVIterator.next();
                iplCSVList.add(runsData);
            }
            return numOfEateries;
        } catch (IOException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    public String getSortedCricketData(SortField sortedField) {

        if (iplCSVList == null || iplCSVList.size() == 0) {
            throw new IPLAnalyserException("No Data", IPLAnalyserException.ExceptionType.IPL_DATA_NOT_FOUND);
        }
        //Comparator<IPLBatsmanCSV> cricketComparator = Comparator.comparing(census -> census.battingAvg);
        this.sort(iplCSVList, this.sortedMap.get(sortedField));
        Collections.reverse(iplCSVList);
        String sortedStateCensus = new Gson().toJson(iplCSVList);
        return sortedStateCensus;
    }

    private void sort(List<IPLBatsmanCSV> iplCSVList, Comparator<IPLBatsmanCSV> iplBatsmanCSVComparator) {
        for (int i = 0; i < iplCSVList.size() - 1; i++) {
            for (int j = 0; j < iplCSVList.size() - i - 1; j++) {
                IPLBatsmanCSV run1 = iplCSVList.get(j);
                IPLBatsmanCSV run2 = iplCSVList.get(j + 1);
                if (iplBatsmanCSVComparator.compare(run1, run2) > 0) {
                    iplCSVList.set(j, run2);
                    iplCSVList.set(j + 1, run1);
                }
            }
        }
    }
}



