package iplCricketGame;

import com.opencsv.bean.CsvBindByName;

public class IplBowlingCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "Econ",required = true)
    public double economyRate;
}