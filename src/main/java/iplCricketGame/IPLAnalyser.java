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

    public IPLAnalyser() {
        this.iplCSVList = new ArrayList<>();
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

    public String getSortedCricketData() {

        if (iplCSVList == null || iplCSVList.size() == 0) {
            throw new IPLAnalyserException("No Data", IPLAnalyserException.ExceptionType.IPL_DATA_NOT_FOUND);
        }
        Comparator<IPLBatsmanCSV> cricketComparator = Comparator.comparing(census -> census.battingAvg);
        this.sort(iplCSVList, cricketComparator);
        Collections.reverse(iplCSVList);
        String sortedStateCensus = new Gson().toJson(iplCSVList);
        return sortedStateCensus;
    }

    private void sort(List<IPLBatsmanCSV> cricketCSVList, Comparator<IPLBatsmanCSV> censusComparator) {
        for (int i = 0; i < cricketCSVList.size() - 1; i++) {
            for (int j = 0; j < cricketCSVList.size() - i - 1; j++) {
                IPLBatsmanCSV run1 = cricketCSVList.get(j);
                IPLBatsmanCSV run2 = cricketCSVList.get(j + 1);
                if (censusComparator.compare(run1, run2) > 0) {
                    cricketCSVList.set(j, run2);
                    cricketCSVList.set(j + 1, run1);
                }
            }
        }
    }
}
