package br.com.bluesoft.alucar.controller.dto;

import br.com.bluesoft.alucar.model.Vendedor;
import org.springframework.data.domain.Page;

public class VendedorDto {
    private Integer vendedorKey;
    private String nomeCompleto;

    public VendedorDto(Vendedor vendedor) {
        this.vendedorKey = vendedor.getVendedorKey();
        this.nomeCompleto = vendedor.getNome();
    }

    public Integer getVendedorKey() {
        return vendedorKey;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public static Page<VendedorDto> converter(Page<Vendedor> vendedores) {
        return vendedores.map(VendedorDto::new);
    }
}
