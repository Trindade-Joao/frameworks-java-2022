package br.com.bluesoft.alucar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteKey;
    private String nome;
    private Long CPF;
    private String email;
    private Long celular;

    public Cliente(String nome, Long CPF, String email, Long celular) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.celular = celular;
    }

    @Deprecated
    public Cliente() {
    }

    public Integer getClienteKey() {
        return clienteKey;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }
}
