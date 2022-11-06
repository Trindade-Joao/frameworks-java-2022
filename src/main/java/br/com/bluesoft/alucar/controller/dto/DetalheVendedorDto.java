package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.ContaCorrenteRepository;

import java.time.LocalDate;

public class DetalheVendedorDto {

    private Integer vendedorKey;
    private String nomeCompleto;
    private Long CPF;
    private LocalDate dataAdmissao;

    private ContaCorrenteDto contaCorrenteDto;

    public DetalheVendedorDto(Vendedor vendedor, ContaCorrenteRepository contaCorrenteRepository) {
        this.vendedorKey = vendedor.getVendedorKey();
        this.nomeCompleto = vendedor.getNome();
        this.CPF = vendedor.getCPF();
        this.dataAdmissao = vendedor.getDataAdmissao();
        ContaCorrente contaCorrente = contaCorrenteRepository.findByVendedor(vendedor);
        this.contaCorrenteDto = ContaCorrenteDto.converter(contaCorrente);
    }

    public Integer getVendedorKey() {
        return vendedorKey;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Long getCPF() {
        return CPF;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public ContaCorrenteDto getContaCorrenteDto() {
        return contaCorrenteDto;
    }
}
