package deloitte.bootcamp.mapper;

import deloitte.bootcamp.dto.ProdutoRequestDTO;
import deloitte.bootcamp.dto.ProdutoResponseDTO;
import deloitte.bootcamp.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toProduto(ProdutoRequestDTO dto) {
        return Produto.builder()
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .build();
    }

    public ProdutoResponseDTO toResponse(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}
