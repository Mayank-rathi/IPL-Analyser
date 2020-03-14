package iplCricketGame;

import com.opencsv.bean.CsvBindByName;

public class IplBowlingCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Avg", required = true)
    public double average;
}