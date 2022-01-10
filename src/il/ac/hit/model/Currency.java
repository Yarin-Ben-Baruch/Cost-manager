package il.ac.hit.model;

/**
 * A class that maintains the existing currencies in the expense tracking software.
 */
public class Currency {

    private String symbol;
    private double rate;

    /**
     * A constructor that updates the i_Category in DB.
     * @param symbol the symbol of the currency.
     * @param rate the rate of the currency.
     */
    public Currency(String symbol, double rate) {
        setSymbol(symbol);
        setRate(rate);
    }

    /**
     * basic getter.
     * @return string ( symbol).
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * basic getter.
     * @return double(rate).
     */
    public double getRate() {
        return rate;
    }

    // basic setter.
    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // basic setter.
    private void setRate(double rate) {
        this.rate = rate;
    }
}
