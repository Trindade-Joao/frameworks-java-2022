package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.VendedorRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class AtualizacaoVendedorForm {

    @NotBlank
    private String nome;
    @NotNull
    private Long CPF;
    @NotNull
    private LocalDate dataAdmissao;
    private AtualizacaoContaCorrenteForm atualizacaoContaCorrenteForm;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public void setAtualizacaoContaCorrenteForm(AtualizacaoContaCorrenteForm atualizacaoContaCorrenteForm) {
        this.atualizacaoContaCorrenteForm = atualizacaoContaCorrenteForm;
    }

    public AtualizacaoContaCorrenteForm getAtualizacaoContaCorrenteForm() {
        return atualizacaoContaCorrenteForm;
    }

    public Vendedor atualizar(Integer vendedorKey, VendedorRepository vendedorRepository){
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(vendedorKey);
        Vendedor vendedor = optionalVendedor.get();
        vendedor.setNome(nome);
        vendedor.setCPF(CPF);
        vendedor.setDataAdmissao(dataAdmissao);

        return vendedor;
    }
}
