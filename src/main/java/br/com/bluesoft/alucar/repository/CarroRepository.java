package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, String> {

}
