package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteForm {
    @NotBlank
    private String nome;
    @NotNull
    private Long CPF;
    @NotBlank
    private String email;
    @NotNull
    private Long celular;
    private EnderecoForm enderecoForm;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public EnderecoForm getEnderecoForm() {
        return enderecoForm;
    }

    public void setEnderecoForm(EnderecoForm enderecoForm) {
        this.enderecoForm = enderecoForm;
    }

    public Cliente converter(){
        return new Cliente(nome, CPF, email, celular);
    }
}
