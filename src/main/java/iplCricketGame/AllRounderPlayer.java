package iplCricketGame;

import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class AllRounderAverage extends IplAdapter{
    public Map<String, CricketCsvDto> loadIplData(String... csvFilePath) {
        Map<String,CricketCsvDto> cricketCsvDtoMap =null;
        cricketCsvDtoMap =super.loadIplData(IPLBatsmanCSV.class,csvFilePath[0]);
        this.loadBowlingData(cricketCsvDtoMap,csvFilePath[1]);
        return cricketCsvDtoMap;
    }

    private void loadBowlingData(Map<String, CricketCsvDto> cricketCsvDtoMap, String csvFilePath) {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder iCsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<IplBowlingCSV> iterator=iCsvBuilder.getCSVFileIterator(reader,IplBowlingCSV.class);
            Iterable<IplBowlingCSV> iterable=() ->iterator;
            StreamSupport.stream(iterable.spliterator(),false)
                    .filter(iplMostWicketsAdapter -> cricketCsvDtoMap.get(iplMostWicketsAdapter.playerName)!=null )
                    .forEach(mergedData->{cricketCsvDtoMap.get(mergedData.playerName).average=mergedData.average;});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}