package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.repository.ClienteRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualizacaoClienteForm {
    @NotBlank
    private String nome;
    @NotNull
    private Long CPF;
    @NotBlank
    private String email;
    @NotNull
    private Long celular;
    private AtualizacaoEnderecoForm atualizacaoEnderecoForm;

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

    public AtualizacaoEnderecoForm getAtualizacaoEnderecoForm() {
        return atualizacaoEnderecoForm;
    }

    public void setAtualizacaoEnderecoForm(AtualizacaoEnderecoForm atualizacaoEnderecoForm) {
        this.atualizacaoEnderecoForm = atualizacaoEnderecoForm;
    }

    public Cliente atualizar(Integer clienteKey, ClienteRepository clienteRepository){
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteKey);
        Cliente cliente = optionalCliente.get();
        cliente.setNome(nome);
        cliente.setCPF(CPF);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        return cliente;
    }
}
