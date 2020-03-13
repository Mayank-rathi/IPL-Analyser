import iplCricketGame.IPLAnalyser;
import iplCricketGame.IPLAnalyserException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
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
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLAnalyserException.class);
            iplAnalyser.loadIplData(WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

}

