package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Endereco;
import br.com.bluesoft.alucar.repository.EnderecoRepository;

public class DetalheClienteDto {
    private Integer clienteKey;
    private String nome;
    private Long CPF;
    private String email;
    private Long celular;
    private EnderecoDto enderecoDto;

    public DetalheClienteDto(Cliente cliente, EnderecoRepository enderecoRepository) {
        this.clienteKey = cliente.getClienteKey();
        this.nome = cliente.getNome();
        this.CPF = cliente.getCPF();
        this.email = cliente.getEmail();
        this.celular = cliente.getCelular();
        Endereco endereco = enderecoRepository.findByCliente(cliente);
        this.enderecoDto = new EnderecoDto(endereco);
    }

    public Integer getClienteKey() {
        return clienteKey;
    }

    public String getNome() {
        return nome;
    }

    public Long getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }

    public Long getCelular() {
        return celular;
    }

    public EnderecoDto getEnderecoDto() {
        return enderecoDto;
    }
}
