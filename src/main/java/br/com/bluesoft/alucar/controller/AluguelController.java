package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.controller.dto.AluguelDto;
import br.com.bluesoft.alucar.controller.dto.DetalheAluguelDto;
import br.com.bluesoft.alucar.controller.form.AluguelForm;
import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.repository.AluguelRepository;
import br.com.bluesoft.alucar.repository.CarroRepository;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import br.com.bluesoft.alucar.repository.VendedorRepository;
import br.com.bluesoft.alucar.service.ValidaAluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ValidaAluguelService validaAluguelService;


    @GetMapping
    public Page<AluguelDto> listar(Pageable paginacao){
        Page<Aluguel> alugueis = aluguelRepository.findAll(paginacao);
        return AluguelDto.converter(alugueis);
    }
    @GetMapping("/{aluguelKey}")
    public ResponseEntity<DetalheAluguelDto> detalharAluguel(@PathVariable Integer aluguelKey){
        Optional<Aluguel> optionalAluguel = aluguelRepository.findById(aluguelKey);
        if(optionalAluguel.isPresent()){
            return ResponseEntity.ok(new DetalheAluguelDto(optionalAluguel.get()));
        }
        return ResponseEntity.notFound().build();
    }



    @PostMapping
    @Transactional
    public ResponseEntity<DetalheAluguelDto> cadastrar(@Valid @RequestBody AluguelForm aluguelForm, UriComponentsBuilder uriBuilder){
        if(validaAluguelService.validar(aluguelForm,carroRepository,clienteRepository,vendedorRepository)){
            Aluguel aluguel = aluguelForm.converter(carroRepository, clienteRepository, vendedorRepository);
            aluguelRepository.save(aluguel);

            URI uri = uriBuilder.path("/aluguel/{aluguelKey}").buildAndExpand(aluguel.getAluguelKey()).toUri();

            return ResponseEntity.created(uri).body(new DetalheAluguelDto(aluguel));
        }
        return ResponseEntity.notFound().build();
    }

}
