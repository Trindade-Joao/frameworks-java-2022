package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM cliente cli INNER JOIN " +
                    "(SELECT cliente_key, SUM(valor_total) as soma FROM aluguel GROUP BY cliente_key " +
                    "ORDER BY soma DESC LIMIT 1) cont " +
                    "WHERE cli.cliente_key = cont.cliente_key")
    Cliente findClienteMaisAtivo();
}
