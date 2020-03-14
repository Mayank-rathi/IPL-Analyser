import com.google.gson.Gson;
import iplCricketGame.IPLAnalyser;
import iplCricketGame.IPLAnalyserException;
import iplCricketGame.IPLBatsmanCSV;
import iplCricketGame.SortField;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IplCricketAnalyserTest {
    private static IPLAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIplCSVFileReturnsCorrectRecords() throws IPLAnalyserException {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        int numOfRecords = iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        Assert.assertEquals(101, numOfRecords);
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() throws IPLAnalyserException {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLAnalyserException.class);
            iplAnalyser.loadIplData(WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCricketMostRunData_WhenSorted_ShouldReturnMostRun() {
        try {
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortField.AVERAGE);
            IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IPLBatsmanCSV[].class);
            Assert.assertEquals(83.2, mostRunCsv[0].battingAvg, 0.0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketMostStrikingRatesData_WhenSorted_ShouldReturnplayerName() {
        try {
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortField.STRIKE_RATE);
            IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IPLBatsmanCSV[].class);
            Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playeName);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sHittingCount() throws IPLAnalyserException {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortField.NO_OF_4S_AND_6S);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IPLBatsmanCSV[].class);
        Assert.assertEquals(83, mostRunCsv[0].fours + mostRunCsv[0].sixes);

    }

    @Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBestStrikeRateWith4sAnd6sHittingCount() throws IPLAnalyserException {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortField.STRIKE_RATE_WITH_6S_4S);
        IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IPLBatsmanCSV[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playeName);

    }


    @Test
    public void givenCricketerWithgreatBattingAvgAndBestStrikeRate_WhenSorted_ShouldReturnMostStrikingRatesWithGreatAvg() throws IPLAnalyserException {
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortField.AVERAGE);
            IPLBatsmanCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IPLBatsmanCSV[].class);
            Assert.assertEquals(83.2, mostRunCsv[0].battingAvg,0.0);

    }

}

