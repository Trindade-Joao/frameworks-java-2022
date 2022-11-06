package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.controller.dto.ClienteDto;
import br.com.bluesoft.alucar.controller.dto.DetalheClienteDto;
import br.com.bluesoft.alucar.controller.form.AtualizacaoClienteForm;
import br.com.bluesoft.alucar.controller.form.ClienteForm;
import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Endereco;
import br.com.bluesoft.alucar.repository.ClienteRepository;
import br.com.bluesoft.alucar.repository.EnderecoRepository;
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
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public Page<ClienteDto> listar(Pageable paginacao){
        Page<Cliente> clientes = clienteRepository.findAll(paginacao);
        return ClienteDto.converter(clientes);
    }

    @GetMapping("/{clienteKey}")
    public ResponseEntity<DetalheClienteDto> detalhar(@PathVariable Integer clienteKey){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteKey);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(new DetalheClienteDto(clienteOptional.get(), enderecoRepository));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalheClienteDto> cadastrar(@Valid @RequestBody ClienteForm clienteForm, UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteForm.converter();
        clienteRepository.save(cliente);
        Endereco endereco = clienteForm.getEnderecoForm().converter(cliente);
        enderecoRepository.save(endereco);

        URI uri = uriBuilder.path("/cliente/{clienteKey}").buildAndExpand(cliente.getClienteKey()).toUri();
        return ResponseEntity.created(uri).body(new DetalheClienteDto(cliente, enderecoRepository));
    }

    @PutMapping("/{clienteKey}")
    @Transactional
    public ResponseEntity<DetalheClienteDto> atualizar(@PathVariable Integer clienteKey, @Valid @RequestBody AtualizacaoClienteForm atualizacaoForm){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteKey);
        if(clienteOptional.isPresent()){
            Cliente cliente = atualizacaoForm.atualizar(clienteKey, clienteRepository);
            Endereco endereco = atualizacaoForm.getAtualizacaoEnderecoForm().atualizar(enderecoRepository, cliente);
            return ResponseEntity.ok(new DetalheClienteDto(cliente, enderecoRepository));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteKey}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Integer clienteKey){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteKey);
        if(clienteOptional.isPresent()){
            clienteRepository.deleteById(clienteKey);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
