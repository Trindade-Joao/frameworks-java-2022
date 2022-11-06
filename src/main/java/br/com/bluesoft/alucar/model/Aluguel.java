package br.com.bluesoft.alucar.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Aluguel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aluguelKey;
    @ManyToOne
    @JoinColumn(name = "cliente_key")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "vendedor_key")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "placa_carro")
    private Carro carro;
    private BigDecimal valorTotal;

    private LocalDate dataAluguel;
    private Integer diasAlugado;

    public Aluguel(Cliente cliente, Vendedor vendedor, Carro carro, Integer diasAlugado) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.carro = carro;
        this.dataAluguel = LocalDate.now();
        this.valorTotal = calculaValorTotal(diasAlugado, carro.getDiaria());
        this.diasAlugado = diasAlugado;
    }
    @Deprecated
    public Aluguel() {
    }

    public Integer getAluguelKey() {
        return aluguelKey;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
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

    public Integer getDiasAlugado() {
        return diasAlugado;
    }

    public void setDiasAlugado(Integer diasAlugado) {
        this.diasAlugado = diasAlugado;
    }

    private BigDecimal calculaValorTotal(Integer diasAlugado, BigDecimal valorDiaria){
        return valorDiaria.multiply(BigDecimal.valueOf(diasAlugado));
    }
}
