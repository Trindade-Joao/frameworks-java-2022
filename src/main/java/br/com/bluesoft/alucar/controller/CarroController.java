package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.controller.dto.CarroDto;
import br.com.bluesoft.alucar.controller.dto.DetalheCarroDto;
import br.com.bluesoft.alucar.controller.form.AtualizacaoCarroForm;
import br.com.bluesoft.alucar.controller.form.CarroForm;
import br.com.bluesoft.alucar.model.Carro;
import br.com.bluesoft.alucar.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public Page<CarroDto> listar(Pageable paginacao){
        Page<Carro> carros = carroRepository.findAll(paginacao);
        return CarroDto.converter(carros);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<DetalheCarroDto> detalharCarro(@PathVariable String placa){
        Optional<Carro> optionalCarro = carroRepository.findById(placa);
        if(optionalCarro.isPresent()){
            return ResponseEntity.ok(new DetalheCarroDto(optionalCarro.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalheCarroDto> cadastrar(@Valid @RequestBody CarroForm form, UriComponentsBuilder uriBuilder){
        Carro carro = form.converter();
        carroRepository.save(carro);

        URI uri = uriBuilder.path("/carro/{placa}").buildAndExpand(carro.getPlaca()).toUri();
        return ResponseEntity.created(uri).body(new DetalheCarroDto(carro));
    }

    @PutMapping("/{placa}")
    @Transactional
    public ResponseEntity<DetalheCarroDto> atualizar(@PathVariable String placa, @Valid @RequestBody AtualizacaoCarroForm form){
        Optional<Carro> optionalCarro = carroRepository.findById(placa);
        if(optionalCarro.isPresent()){
            Carro carro = form.atualizar(placa, carroRepository);
            return ResponseEntity.ok(new DetalheCarroDto(carro));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{placa}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable String placa){
        Optional<Carro> optionalCarro = carroRepository.findById(placa);
        if(optionalCarro.isPresent()){
            carroRepository.deleteById(placa);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
