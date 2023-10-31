package it.nadr.EsercizioDebito.domain;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
public class Rilevamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate data_Rilevamento;
    private double debito;

    @ManyToOne
    private Organizzazione organizzazione;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData_Rilevamento() {
        return data_Rilevamento;
    }

    public void setData_Rilevamento(LocalDate data_Rilevamento) {
        this.data_Rilevamento = data_Rilevamento;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public Organizzazione getOrganizzazione() {
        return organizzazione;
    }

    public void setOrganizzazione(Organizzazione organizzazione) {
        this.organizzazione = organizzazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rilevamento that = (Rilevamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
