package hello.pojos;

import java.util.Objects;

public class Rates {

    private String year_month;
    private Float rate;

    public Rates(String year_month, Float rate) {
        this.year_month = year_month;
        this.rate = rate;
    }

    public String getYear_month() {
        return year_month;
    }

    public void setYear_month(String year_month) {
        this.year_month = year_month;
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
        Rates rates = (Rates) o;
        return year_month.equals(rates.year_month) &&
                rate.equals(rates.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year_month, rate);
    }

    @Override
    public String toString() {
        return "Rates{" +
                "year_month='" + year_month + '\'' +
                ", rate=" + rate +
                '}';
    }
}
