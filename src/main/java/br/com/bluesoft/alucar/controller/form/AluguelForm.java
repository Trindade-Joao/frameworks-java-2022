package br.com.bluesoft.alucar.controller.form;

import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import br.com.bluesoft.alucar.repository.VendedorRepository;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AluguelForm {
    @NotNull
    private Integer clienteKey;
    @NotNull
    private Integer vendedorKey;
    @NotBlank
    private String placaCarro;
    @NotNull
    @Range(min = 1)
    private Integer diasAlugado;

    public Integer getClienteKey() {
        return clienteKey;
    }

    public void setClienteKey(Integer clienteKey) {
        this.clienteKey = clienteKey;
    }

    public Integer getVendedorKey() {
        return vendedorKey;
    }

    public void setVendedorKey(Integer vendedorKey) {
        this.vendedorKey = vendedorKey;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public Integer getDiasAlugado() {
        return diasAlugado;
    }

    public void setDiasAlugado(Integer diasAlugado) {
        this.diasAlugado = diasAlugado;
    }

    public Aluguel converter(CarroRepository carroRepository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository) {
        Optional<Carro> optionalCarro = carroRepository.findById(placaCarro);
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteKey);
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(vendedorKey);

        return new Aluguel(optionalCliente.get(),
                optionalVendedor.get(), optionalCarro.get(), diasAlugado);
    }

}
