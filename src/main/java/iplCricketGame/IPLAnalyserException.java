package iplCricketGame;

public class IPLAnalyserException extends RuntimeException {
    public enum ExceptionType {
        IPL_FILE_PROBLEM, IPL_DATA_NOT_FOUND, INVALID_FILE_DATA;
    }

    public ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}

