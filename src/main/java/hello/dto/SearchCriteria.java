package hello.dto;

import javax.validation.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "tarjeta can't empty!")
    String tarjeta;

    @NotBlank(message = "año can't empty!")
    String año;

    @NotBlank(message = "mes can't empty!")
    String mes;

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}