package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Vendedor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class VendedorForm {
    @NotBlank
    private String nome;
    @NotNull
    private Long CPF;

    private ContaCorrenteForm contaCorrenteForm;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public ContaCorrenteForm getContaCorrenteForm() {
        return contaCorrenteForm;
    }

    public void setContaCorrenteForm(ContaCorrenteForm contaCorrenteForm) {
        this.contaCorrenteForm = contaCorrenteForm;
    }

    public Vendedor converter() {

        return new Vendedor(nome, CPF);
    }
}
