package iplCricketGame;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsmanCSV {
    @CsvBindByName(column = "PLAYER",required = true)
    public String playeName;

    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "SR",required = true)
    public double strikRate;
}
