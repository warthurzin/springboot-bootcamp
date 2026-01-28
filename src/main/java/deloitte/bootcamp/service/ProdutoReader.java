package deloitte.bootcamp.service;

import deloitte.bootcamp.dto.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoReader {

    List<ProdutoResponseDTO> listar();

    ProdutoResponseDTO buscarPorId(Long id);
}