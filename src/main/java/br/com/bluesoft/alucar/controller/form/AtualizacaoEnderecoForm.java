package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Endereco;
import br.com.bluesoft.alucar.repository.EnderecoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AtualizacaoEnderecoForm {
    @NotBlank
    private String rua;
    @NotNull
    private Integer numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Endereco atualizar(EnderecoRepository enderecoRepository, Cliente cliente){
        Endereco endereco = enderecoRepository.findByCliente(cliente);
         endereco.setRua(rua);
         endereco.setNumero(numero);
         endereco.setComplemento(complemento);
         endereco.setBairro(bairro);
         endereco.setCidade(cidade);
         endereco.setEstado(estado);

        return endereco;
    }
}
