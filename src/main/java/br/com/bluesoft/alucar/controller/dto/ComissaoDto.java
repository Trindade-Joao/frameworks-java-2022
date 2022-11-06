package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Comissao;
import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.repository.ContaCorrenteRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ComissaoDto {
    private String vendedor;
    private Long CPF;
    private BigDecimal valor;
    private ContaCorrenteDto contaCorrenteDto;

    public ComissaoDto(Comissao comissao, ContaCorrenteRepository contaCorrenteRepository) {
        this.vendedor = comissao.getVendedor().getNome();
        this.CPF = comissao.getVendedor().getCPF();
        this.valor = comissao.calculaComissao();
        ContaCorrente contaCorrente = contaCorrenteRepository.findByVendedor(comissao.getVendedor());
        this.contaCorrenteDto = new ContaCorrenteDto(contaCorrente);
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ContaCorrenteDto getContaCorrenteDto() {
        return contaCorrenteDto;
    }

    public void setContaCorrenteDto(ContaCorrenteDto contaCorrenteDto) {
        this.contaCorrenteDto = contaCorrenteDto;
    }

    public static List<ComissaoDto> converter(List<Comissao> comissoes, ContaCorrenteRepository contaCorrenteRepository) {
        List<ComissaoDto> comissoesDto = new ArrayList<>();
        comissoes.forEach(comissao -> comissoesDto.add(new ComissaoDto(comissao, contaCorrenteRepository)));
        return comissoesDto;
    }

    public static ComissaoDto converter(Comissao comissao, ContaCorrenteRepository contaCorrenteRepository) {
        return new ComissaoDto(comissao, contaCorrenteRepository);
    }


}
