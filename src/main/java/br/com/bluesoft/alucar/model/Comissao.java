package br.com.bluesoft.alucar.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Comissao {

    private Vendedor vendedor;
    private BigDecimal valorTotal;


    public Comissao(Vendedor vendedor, BigDecimal valorTotal) {
        this.vendedor = vendedor;
        this.valorTotal = valorTotal;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    private BigDecimal validaTempoServico(Vendedor vendedor){
        LocalDate dataAdmissao = vendedor.getDataAdmissao();
        long tempoDeServico = dataAdmissao.until(LocalDate.now(), ChronoUnit.YEARS);
        if(tempoDeServico > 5){
            return BigDecimal.valueOf(0.13);
        }
        return BigDecimal.valueOf(0.1);
    }

    public BigDecimal calculaComissao(){
        return valorTotal.multiply(validaTempoServico(vendedor)).setScale(2, RoundingMode.HALF_UP);
    }

}
