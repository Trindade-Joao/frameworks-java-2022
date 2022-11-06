package br.com.bluesoft.alucar.controller.dto;


import br.com.bluesoft.alucar.model.Aluguel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetalheAluguelDto {

    private String nomeCliente;
    private String modeloCarro;
    private String placaCarro;
    private String vendedor;
    private Integer diasAlugado;
    private BigDecimal valorTotal;
    private LocalDate dataAluguel;



    public DetalheAluguelDto(Aluguel aluguel) {
        this.nomeCliente = aluguel.getCliente().getNome();
        this.modeloCarro = aluguel.getCarro().getModelo();
        this.placaCarro = aluguel.getCarro().getPlaca();
        this.vendedor = aluguel.getVendedor().getNome();
        this.diasAlugado = aluguel.getDiasAlugado();
        this.valorTotal = aluguel.getValorTotal();
        this.dataAluguel = aluguel.getDataAluguel();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getDiasAlugado() {
        return diasAlugado;
    }

    public void setDiasAlugado(Integer diasAlugado) {
        this.diasAlugado = diasAlugado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }
}
