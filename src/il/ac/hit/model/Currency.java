package il.ac.hit.model;

public class Currency {

    private String symbol;
    private double rate;

    public Currency(String symbol, double rate) {
        setSymbol(symbol);
        setRate(rate);
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private void setRate(double rate) {
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getRate() {
        return rate;
    }
}
