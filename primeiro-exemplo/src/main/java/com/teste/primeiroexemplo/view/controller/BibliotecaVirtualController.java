package com.teste.primeiroexemplo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.primeiroexemplo.services.BibliotecaVirtualService;
import com.teste.primeiroexemplo.shared.BibliotecaVirtualDTO;
import com.teste.primeiroexemplo.view.model.BibliotecaVirtualRequest;
import com.teste.primeiroexemplo.view.model.BibliotecaVirtualResponse;

@RestController
@RequestMapping("/api/bibliotecaVirtual")
public class BibliotecaVirtualController {

  @Autowired
  private BibliotecaVirtualService bibliotecaVirtualService;

  @GetMapping
  public ResponseEntity<List<BibliotecaVirtualResponse>> consultarTodos() {
    List<BibliotecaVirtualDTO> bibliotecas = bibliotecaVirtualService.consultarTodos();

    ModelMapper mapper = new ModelMapper();
    List<BibliotecaVirtualResponse> resposta = bibliotecas.stream()
        .map(bibliotecaVirtualDto -> mapper.map(bibliotecaVirtualDto, BibliotecaVirtualResponse.class))
        .collect(Collectors.toList());

    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<BibliotecaVirtualResponse>> consultarPorId(@PathVariable Integer id) {
    // try {
    Optional<BibliotecaVirtualDTO> dto = bibliotecaVirtualService.consultarPorId(id);
    BibliotecaVirtualResponse bibliotecaVirtual = new ModelMapper().map(dto.get(), BibliotecaVirtualResponse.class); 

    return new ResponseEntity<>(Optional.of(bibliotecaVirtual), HttpStatus.OK);

    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
  }

  @PostMapping
  public ResponseEntity<BibliotecaVirtualResponse> cadastrarLivro(
      @RequestBody BibliotecaVirtualRequest bibliotecaVirtualReq) {
    ModelMapper mapper = new ModelMapper();
    BibliotecaVirtualDTO bibliotecaVirtualDto = mapper.map(bibliotecaVirtualReq, BibliotecaVirtualDTO.class);

    bibliotecaVirtualDto = bibliotecaVirtualService.cadastrarLivro(bibliotecaVirtualDto);
    return new ResponseEntity<>(mapper.map(bibliotecaVirtualDto, BibliotecaVirtualResponse.class), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> excluir(@PathVariable Integer id) {
    bibliotecaVirtualService.excluir(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BibliotecaVirtualResponse> atualizar(@RequestBody BibliotecaVirtualRequest bibliotecaVirtualReq,
      @PathVariable Integer id) {
    ModelMapper mapper = new ModelMapper();
    BibliotecaVirtualDTO bibliotecaVirtualDto = mapper.map(bibliotecaVirtualReq, BibliotecaVirtualDTO.class);

    bibliotecaVirtualDto = bibliotecaVirtualService.atualizar(id, bibliotecaVirtualDto);
    return new ResponseEntity<>(
        mapper.map(bibliotecaVirtualDto, BibliotecaVirtualResponse.class),
        HttpStatus.OK);
  }
}