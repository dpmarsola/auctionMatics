package br.com.auctionMatics.main;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DadosLotes")
public class DadosLotes implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @Column(name = "LoteID", unique = true, nullable = false)
    private Integer LoteID;
    
    @Column(name = "numLeilao", nullable = false)
    private Integer numLeilao;
    
    @Column(name = "marcaModeloVeiculo", nullable = false)
    private String marcaModeloVeiculo;
    
    @Column(name = "anoVeiculo", nullable = false)
    private String anoVeiculo;
    
    @Column(name = "qtdeKilometros", nullable = false)
    private String qtdeKilometros;

    @Column(name = "corVeiculo", nullable = false)
    private String corVeiculo;

    @Column(name = "combustivelVeiculo", nullable = false)
    private String combustivelVeiculo;

    @Column(name = "linkLote", nullable = false)
    private String linkLote;

    // Accessors and mutators for all four fields


    public Integer getLoteID() {
        return LoteID;
    }

	public void setLoteID(Integer loteID) {
        LoteID = loteID;
    }

    public Integer getNumLeilao() {
		return numLeilao;
	}

	public void setNumLeilao(Integer numLeilao) {
		this.numLeilao = numLeilao;
	}
	
	public String getMarcaModeloVeiculo() {
        return marcaModeloVeiculo;
    }

    public void setMarcaModeloVeiculo(String marcaModeloVeiculo) {
        this.marcaModeloVeiculo = marcaModeloVeiculo;
    }

    public String getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(String anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getQtdeKilometros() {
        return qtdeKilometros;
    }

    public void setQtdeKilometros(String qtdeKilometros) {
        this.qtdeKilometros = qtdeKilometros;
    }

    public String getCorVeiculo() {
        return corVeiculo;
    }

    public void setCorVeiculo(String corVeiculo) {
        this.corVeiculo = corVeiculo;
    }

    public String getCombustivelVeiculo() {
        return combustivelVeiculo;
    }

    public void setCombustivelVeiculo(String combustivelVeiculo) {
        this.combustivelVeiculo = combustivelVeiculo;
    }

    public String getLinkLote() {
        return linkLote;
    }

    public void setLinkLote(String linkLote) {
        this.linkLote = linkLote;
    }
}
