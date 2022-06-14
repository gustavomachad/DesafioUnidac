package com.gustavo.unidac.desafio.rh.dominio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {

	@Modifying 
	@Transactional
	@Query (value = "insert into tb_pessoa (nome, Cafe, CPF) values (?)", nativeQuery = true )
	Pessoa insert (Pessoa pessoa);
	
	@Query (value = "select * from tb_pessoa order by id", nativeQuery = true )
	List <Pessoa> findAll();
	
	@Query (value = "select * from tb_pessoa u where u.id = ?", nativeQuery = true )
	Pessoa getPessoa (Long id);
	
    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_pessoa SET NOME = ?2 WHERE ID = ?1", nativeQuery = true)
    Pessoa update(Long nome, Pessoa obj);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_pessoa WHERE ID = ? ", nativeQuery = true)
    void delete(Long id);
    
    @Query("SELECT c FROM Pessoa c WHERE c.CPF = :CPF")
    public Pessoa findByCpf(@Param("CPF") String CPF);
    
    @Query("SELECT c FROM Pessoa c WHERE c.Cafe = :Cafe")
    public Pessoa findByCafe(@Param("Cafe") String Cafe);
}

