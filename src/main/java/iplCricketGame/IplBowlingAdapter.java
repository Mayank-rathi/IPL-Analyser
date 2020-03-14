package iplCricketGame;

import java.util.Map;

public class IplBowlingAdapter extends IplAdapter {

    @Override
    public Map<String, CricketCsvDto> loadIplData(String... csvFilePath) {
        Map<String,CricketCsvDto> cricketCsvDtoMap=super.loadIplData(IplBowlingCSV.class,csvFilePath[0]);
        return cricketCsvDtoMap;
    }
}