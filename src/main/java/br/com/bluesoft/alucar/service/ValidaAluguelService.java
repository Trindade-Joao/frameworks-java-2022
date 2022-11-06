package br.com.bluesoft.alucar.service;

import br.com.bluesoft.alucar.controller.form.AluguelForm;
import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import br.com.bluesoft.alucar.repository.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ValidaAluguelService {

    public boolean validar(AluguelForm aluguelForm, CarroRepository carroRepository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository){
        Optional<Carro> carro = carroRepository.findById(aluguelForm.getPlacaCarro());
        Optional<Cliente> cliente = clienteRepository.findById(aluguelForm.getClienteKey());
        Optional<Vendedor> vendedor = vendedorRepository.findById(aluguelForm.getVendedorKey());

        return carro.isPresent() && cliente.isPresent() && vendedor.isPresent();
    }
}
