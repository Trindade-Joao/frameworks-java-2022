package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Vendedor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaCorrenteForm {
    @NotBlank
    private String banco;
    @NotNull
    private Integer agencia;
    @NotNull
    private Integer contaCorrente;

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public void setContaCorrente(Integer contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public ContaCorrente converter(Vendedor vendedor) {
        return new ContaCorrente(banco, agencia, contaCorrente,vendedor);
    }
}
