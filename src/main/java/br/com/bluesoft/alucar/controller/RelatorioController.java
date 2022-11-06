package br.com.bluesoft.alucar.controller;

import br.com.bluesoft.alucar.controller.dto.ComissaoDto;
import br.com.bluesoft.alucar.controller.dto.DetalheClienteDto;
import br.com.bluesoft.alucar.controller.dto.DetalheVendedorDto;
import br.com.bluesoft.alucar.model.Cliente;
import br.com.bluesoft.alucar.model.Comissao;
import br.com.bluesoft.alucar.model.Vendedor;
import br.com.bluesoft.alucar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping("/comissao")
    public ResponseEntity<List<ComissaoDto>> listarMesAtual() {
        int mesAtual = LocalDate.now().getMonthValue();
        List<Comissao> comissoes = aluguelRepository.findValorTotalPorVendedorMes(mesAtual);
        return ResponseEntity.ok(ComissaoDto.converter(comissoes, contaCorrenteRepository));

    }

    @GetMapping("/comissao/mes={mes}")
    public ResponseEntity<List<ComissaoDto>> listarMesSelecionado(@PathVariable int mes) {
        if (mes > 0 && mes <= 12) {
            List<Comissao> comissoes = aluguelRepository.findValorTotalPorVendedorMes(mes);
            return ResponseEntity.ok(ComissaoDto.converter(comissoes, contaCorrenteRepository));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/comissao/vendedor={vendedorKey}")
    public ResponseEntity<ComissaoDto> mostrarComissaoIdVendedor(@PathVariable int vendedorKey) {
        int mesAtual = LocalDate.now().getMonthValue();
        Optional<Comissao> optionalComissao = aluguelRepository.valorTotalMesPorVendedorKey(vendedorKey, mesAtual);
        if(optionalComissao.isPresent()){
            return ResponseEntity.ok(ComissaoDto.converter(
                    optionalComissao.get(), contaCorrenteRepository));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente")
    public DetalheClienteDto mostrarClienteMaisAtivo(){
        Cliente cliente = clienteRepository.findClienteMaisAtivo();
        return new DetalheClienteDto(cliente, enderecoRepository);
    }

    @GetMapping("/vendedor")
    public ResponseEntity<DetalheVendedorDto> mostrarVendedorDoMes(){
        int mesAtual = LocalDate.now().getMonthValue();
        Optional<Vendedor> optionalVendedor = vendedorRepository.findVendedorDoMes(mesAtual);
        if(optionalVendedor.isPresent()){
            return ResponseEntity.ok(new DetalheVendedorDto(optionalVendedor.get(),contaCorrenteRepository));
        }
        return ResponseEntity.notFound().build();
    }



}
