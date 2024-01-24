package br.com.marcon.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcon.model.Pearson;

@Repository
public interface PearsonRepository extends JpaRepository<Pearson, Long>{
}
