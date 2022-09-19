package br.com.auctionMatics.main;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "StatusConsulta")
public class StatusConsulta implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @Column(name = "NumLeilao", unique = true, nullable = false)
    private Integer numleilao;

    @Column(name = "status", nullable = false)
    private String statusconsulta;

	public Integer getNumleilao() {
		return numleilao;
	}

	public void setNumleilao(Integer numleilao) {
		this.numleilao = numleilao;
	}

	public String getStatusconsulta() {
		return statusconsulta;
	}

	public void setStatusconsulta(String statusconsulta) {
		this.statusconsulta = statusconsulta;
	}

}
