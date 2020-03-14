package iplCricketGame;

public class CricketCsvDto {

    public int runs;
    public double average;
    public double strikeRate;
    public int fours;
    public int sixes;
    public String playerName;


    public CricketCsvDto(IPLBatsmanCSV iplBatsmanCSV) {
        runs = iplBatsmanCSV.runs;
        average = iplBatsmanCSV.average;
        strikeRate = iplBatsmanCSV.strikRate;
        fours = iplBatsmanCSV.fours;
        sixes = iplBatsmanCSV.sixes;
        playerName =iplBatsmanCSV.playerName;
    }

    public CricketCsvDto(IplBowlingCSV mostBowlingCsv) {
        average = mostBowlingCsv.average;
        playerName=mostBowlingCsv.playerName;

    }
}