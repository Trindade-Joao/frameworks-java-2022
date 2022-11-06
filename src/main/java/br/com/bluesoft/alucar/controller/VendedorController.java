package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.controller.dto.DetalheVendedorDto;
import br.com.bluesoft.alucar.controller.dto.VendedorDto;
import br.com.bluesoft.alucar.controller.form.AtualizacaoVendedorForm;
import br.com.bluesoft.alucar.controller.form.VendedorForm;
import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.ContaCorrenteRepository;
import br.com.bluesoft.alucar.repository.VendedorRepository;
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
@RequestMapping("/vendedor")
public class VendedorController {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @GetMapping
    public Page<VendedorDto> listar(Pageable paginacao){
        Page<Vendedor> vendedores = vendedorRepository.findAll(paginacao);
        return VendedorDto.converter(vendedores);
    }

    @GetMapping("/{vendedorKey}")
    public ResponseEntity<DetalheVendedorDto> detalhar(@PathVariable Integer vendedorKey){
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(vendedorKey);
        if(optionalVendedor.isPresent()){
            return ResponseEntity.ok(new DetalheVendedorDto(optionalVendedor.get(), contaCorrenteRepository));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalheVendedorDto> cadastrar(@Valid @RequestBody VendedorForm vendedorForm, UriComponentsBuilder uriBuilder){
        Vendedor vendedor = vendedorForm.converter();
        vendedorRepository.save(vendedor);
        ContaCorrente contaCorrente = vendedorForm.getContaCorrenteForm().converter(vendedor);
        contaCorrenteRepository.save(contaCorrente);

        URI uri = uriBuilder.path("/vendedor/{vendedorKey}").buildAndExpand(vendedor.getVendedorKey()).toUri();
        return ResponseEntity.created(uri).body(new DetalheVendedorDto(vendedor, contaCorrenteRepository));
    }

    @PutMapping("/{vendedorKey}")
    @Transactional
    public ResponseEntity<DetalheVendedorDto> atualizar(@PathVariable Integer vendedorKey, @Valid @RequestBody AtualizacaoVendedorForm atualizacaoVendedorForm){
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(vendedorKey);
        if(optionalVendedor.isPresent()){
            Vendedor vendedor = atualizacaoVendedorForm.atualizar(vendedorKey,vendedorRepository);
            ContaCorrente contaCorrente = atualizacaoVendedorForm.
                    getAtualizacaoContaCorrenteForm().atualizar(contaCorrenteRepository, vendedor);
            return ResponseEntity.ok(new DetalheVendedorDto(vendedor, contaCorrenteRepository));
        }
        return ResponseEntity.notFound().build();
    }



    @DeleteMapping("/{vendedorKey}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Integer vendedorKey){
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(vendedorKey);
        if(optionalVendedor.isPresent()){
            vendedorRepository.deleteById(vendedorKey);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
