package br.com.bluesoft.alucar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Vendedor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendedorKey;
    private String nome;
    private Long CPF;
    private LocalDate dataAdmissao;

    public Vendedor(String nome, Long CPF) {
        this.nome = nome;
        this.CPF = CPF;
        this.dataAdmissao = LocalDate.now();
    }

    public Vendedor() {
    }

    public Integer getVendedorKey() {
        return vendedorKey;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeCompleto) {
        this.nome = nomeCompleto;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }


}
