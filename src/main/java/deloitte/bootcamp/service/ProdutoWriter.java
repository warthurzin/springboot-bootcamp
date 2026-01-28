package deloitte.bootcamp.service;

import deloitte.bootcamp.dto.ProdutoRequestDTO;
import deloitte.bootcamp.dto.ProdutoResponseDTO;

public interface ProdutoWriter {

    ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO);

    void deletar(Long id);
}