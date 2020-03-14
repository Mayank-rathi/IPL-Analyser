package iplCricketGame;

import java.util.Map;

public class IplAdapterFactory {
    public static Map<String, CricketCsvDto> getIPLData(IPLAnalyser.Cricket cricket, String... csvFilePath) {
        if(cricket.equals(IPLAnalyser.Cricket.RUNS))
            return new IplRunsAdapter().loadIplData(csvFilePath);
        else if (cricket.equals(IPLAnalyser.Cricket.WICKETS))
            return new IplBowlingAdapter().loadIplData(csvFilePath);
        else
            throw new IPLAnalyserException("NO FILE FOUND!!!!",IPLAnalyserException.ExceptionType.IPL_DATA_NOT_FOUND);
    }
}