package br.com.bluesoft.alucar.model;

import javax.persistence.*;

@Entity
public class ContaCorrente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contaCorrenteKey;
    private String banco;
    private Integer agencia;
    private Integer contaCorrente;
    @OneToOne
    @JoinColumn(name = "vendedor_key", nullable = false)
    private Vendedor vendedor;

    public ContaCorrente(String banco, Integer agencia, Integer contaCorrente, Vendedor vendedor) {
        this.banco = banco;
        this.agencia = agencia;
        this.contaCorrente = contaCorrente;
        this.vendedor = vendedor;
    }

    @Deprecated
    public ContaCorrente() {
    }



    public Integer getContaCorrenteKey() {
        return contaCorrenteKey;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(Integer contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
