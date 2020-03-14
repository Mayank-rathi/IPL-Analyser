package iplCricketGame;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsmanCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikRate;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;
}
