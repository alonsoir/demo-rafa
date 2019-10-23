package hello.pojos;

import java.util.Objects;

public class Cards {

    private String card_name;
    private Float rate;

    public Cards(String card_name, Float rate) {
        this.card_name = card_name;
        this.rate = rate;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return card_name.equals(cards.card_name) &&
                rate.equals(cards.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_name, rate);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "card_name='" + card_name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
