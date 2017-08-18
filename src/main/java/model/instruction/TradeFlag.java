package main.java.model.instruction;

public enum TradeFlag {
    BUY("B"),
    SELL("S");

    private String text;

    TradeFlag(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TradeFlag fromString(String text) {

        if (text != null) {
            for (TradeFlag tmp : TradeFlag.values()) {
                if (text.equalsIgnoreCase(tmp.text)) {
                    return tmp;
                }
            }

            throw new IllegalArgumentException("No valid value " + text + " found!");
        } else {
            throw new NullPointerException("Null pointer");
        }
    }
}
