
package com.teste.primeiroexemplo.view.model;

public class BibliotecaVirtualRequest {

  private String nome;
  private String autor;
  private Integer paginas;
  private Integer quantidade;
  private Double valor;
  private String observacao;
  // #endregion

  // #region Getters e Setters

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public Integer getPaginas() {
    return paginas;
  }

  public void setPaginas(Integer paginas) {
    this.paginas = paginas;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

}
