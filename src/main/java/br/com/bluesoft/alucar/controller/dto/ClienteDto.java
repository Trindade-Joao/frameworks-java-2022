package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Cliente;
import org.springframework.data.domain.Page;

public class ClienteDto {
    private Integer clienteKey;
    private String nome;
    private Long CPF;
    private String email;
    private Long celular;

    public Integer getClienteKey() {
        return clienteKey;
    }

    public void setClienteKey(Integer clienteKey) {
        this.clienteKey = clienteKey;
    }

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

    public ClienteDto(Cliente cliente) {
        this.clienteKey = cliente.getClienteKey();
        this.nome = cliente.getNome();
        this.CPF = cliente.getCPF();
        this.email = cliente.getEmail();
        this.celular = cliente.getCelular();
    }

    public static Page<ClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDto::new);
    }
}
