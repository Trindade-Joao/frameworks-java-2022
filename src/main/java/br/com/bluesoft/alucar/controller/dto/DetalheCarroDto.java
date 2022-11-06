package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Carro;

import java.math.BigDecimal;
import java.util.List;

public class DetalheCarroDto {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;
    private Integer quilometragem;
    private BigDecimal diaria;

    public DetalheCarroDto(Carro carro){
        this.placa = carro.getPlaca();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.cor = carro.getCor();
        this.ano = carro.getAno();
        this.quilometragem = carro.getQuilometragem();
        this.diaria = carro.getDiaria();
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public Integer getAno() {
        return ano;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public BigDecimal getDiaria() {
        return diaria;
    }

    public static List<CarroDto> converter(List<Carro> carros){
        return carros.stream().map(CarroDto::new).toList();
    }
}
