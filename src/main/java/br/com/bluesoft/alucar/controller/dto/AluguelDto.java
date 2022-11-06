package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Aluguel;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelDto {
    private Integer aluguelKey;
    private String nomeCliente;
    private String nomeVendedor;
    private String placaCarro;
    private BigDecimal valorTotal;
    private LocalDate dataAluguel;

    public AluguelDto(Aluguel aluguel) {
        this.aluguelKey = aluguel.getAluguelKey();
        this.nomeCliente = aluguel.getCliente().getNome();
        this.nomeVendedor = aluguel.getVendedor().getNome();
        this.placaCarro = aluguel.getCarro().getPlaca();
        this.valorTotal = aluguel.getValorTotal();
        this.dataAluguel = aluguel.getDataAluguel();
    }

    public Integer getAluguelKey() {
        return aluguelKey;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public static Page<AluguelDto> converter(Page<Aluguel> alugueis){
        return alugueis.map(AluguelDto::new);
    }
}
