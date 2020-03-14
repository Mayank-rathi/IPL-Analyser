package iplCricketGame;

public class CricketCsvDto {

    public int runs;
    public double average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public String playerName;
    public double economyRate;
    public int fourWickets;
    public int fiveWickets;
    public int wickets;


    public CricketCsvDto(IPLBatsmanCSV iplBatsmanCSV) {
        runs = iplBatsmanCSV.runs;
        average = iplBatsmanCSV.average;
        strikeRate = iplBatsmanCSV.strikRate;
        fours = iplBatsmanCSV.fours;
        sixes = iplBatsmanCSV.sixes;
        playerName = iplBatsmanCSV.playerName;


    }

    public CricketCsvDto(IplBowlingCSV mostBowlingCsv) {
        average = mostBowlingCsv.average;
        playerName = mostBowlingCsv.playerName;
        strikeRate = mostBowlingCsv.strikeRate;
        economyRate = mostBowlingCsv.economyRate;
        fourWickets = mostBowlingCsv.fourWickets;
        fiveWickets = mostBowlingCsv.fiveWickets;
        wickets=mostBowlingCsv.wickets;


    }
}