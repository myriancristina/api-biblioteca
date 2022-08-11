package com.teste.primeiroexemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.teste.primeiroexemplo.model.BibliotecaVirtual;

@Repository
public interface BibliotecaVirtualRepository extends JpaRepository<BibliotecaVirtual, Integer> {
  
}