import com.google.gson.Gson;
import iplCricketGame.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IplCricketAnalyserTest {
    private static IPLAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIplCSVFileReturnsCorrectRecords() throws IPLAnalyserException {
        int numOfRecords = iplAnalyser.loadIplData(IPLAnalyser.Cricket.WICKETS, IPL_MOST_RUNS_FILE_PATH);
        Assert.assertEquals(100, numOfRecords);
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() throws IPLAnalyserException {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLAnalyserException.class);
            iplAnalyser.loadIplData(IPLAnalyser.Cricket.WICKETS, WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnStrikeRate() {

        iplAnalyser.loadIplData(IPLAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortField.STRIKE_RATE);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IPLBatsmanCSV[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playerName);

    }

    @Test
    public void givenCricketMostStrikingRatesData_WhenSorted_ShouldReturnplayerName() throws IPLAnalyserException {
        iplAnalyser.loadIplData(IPLAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortField.STRIKE_RATE);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IPLBatsmanCSV[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playerName);
    }


    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sHittingCount() throws IPLAnalyserException {
        iplAnalyser.loadIplData(IPLAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortField.NO_OF_4S_AND_6S);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].playerName);
    }


    @Test
    public void givenIPLMostRunData_WhenSortedOnAverages_ShouldReturnBestStrikeRate() {
        iplAnalyser.loadIplData(IPLAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortField.AVERAGE);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);
    }

    @Test
    public void givenIPLMostRunData_WhenSortedOnRuns_ShouldReturnBestAverage() {
        iplAnalyser.loadIplData(IPLAnalyser.Cricket.RUNS, IPL_MOST_RUNS_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortField.BEST_RUNS_WITH_BEST_AVG);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
        Assert.assertEquals("David Warner ", mostRunCsv[0].playerName);

    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopBowlingAverage() {

        iplAnalyser.loadIplData(IPLAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortField.AVERAGE);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

    @Test
    public void givenIPLMostBowlingData_WhenSorted_ShouldReturnTopStrikingRate() {

        iplAnalyser.loadIplData(IPLAnalyser.Cricket.WICKETS, IPL_MOST_BOWLING_FILE_PATH);
        String sortedData = iplAnalyser.getSortedCricketData(SortField.STRIKE_RATE);
        IplBowlingCSV[] mostBowlingCsv = new Gson().fromJson(sortedData, IplBowlingCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostBowlingCsv[0].playerName);
    }

}

