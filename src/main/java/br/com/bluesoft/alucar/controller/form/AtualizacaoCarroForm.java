package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.repository.CarroRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

public class AtualizacaoCarroForm {

    @NotNull
    private Integer quilometragem;
    @NotNull
    private BigDecimal diaria;
    @NotBlank
    private String cor;

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public BigDecimal getDiaria() {
        return diaria;
    }

    public void setDiaria(BigDecimal diaria) {
        this.diaria = diaria;
    }



    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Carro atualizar(String placa, CarroRepository carroRepository){
        Optional<Carro> optionalCarro = carroRepository.findById(placa);
        Carro carro = optionalCarro.get();
        carro.setQuilometragem(quilometragem);
        carro.setDiaria(diaria);
        carro.setCor(cor);

        return carro;
    }
}
