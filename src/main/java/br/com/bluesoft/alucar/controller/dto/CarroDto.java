package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Carro;
import org.springframework.data.domain.Page;

public class CarroDto {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;


    public CarroDto(Carro carro){
        this.placa = carro.getPlaca();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.cor = carro.getCor();
        this.ano = carro.getAno();
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


    public static Page<CarroDto> converter(Page<Carro> carros){
        return carros.map(CarroDto::new);
    }
}
