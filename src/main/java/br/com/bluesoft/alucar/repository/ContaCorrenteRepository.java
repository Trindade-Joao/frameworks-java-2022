package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.ContaCorrente;
import br.com.bluesoft.alucar.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {
    ContaCorrente findByVendedor(Vendedor vendedor);

}
