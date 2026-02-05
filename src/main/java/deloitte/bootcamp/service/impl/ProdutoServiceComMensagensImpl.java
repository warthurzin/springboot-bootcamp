package deloitte.bootcamp.service.impl;

import deloitte.bootcamp.dto.ProdutoRequestDTO;
import deloitte.bootcamp.dto.ProdutoResponseDTO;
import deloitte.bootcamp.exception.ProdutoNaoEncontradoException;
import deloitte.bootcamp.exception.ValidacaoException;
import deloitte.bootcamp.mapper.ProdutoMapper;
import deloitte.bootcamp.model.Produto;
import deloitte.bootcamp.repository.ProdutoRepository;
import deloitte.bootcamp.service.ProdutoService;
import deloitte.bootcamp.service.ProdutoValidator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ProdutoServiceComMensagensImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final List<ProdutoValidator> validators;

    public ProdutoServiceComMensagensImpl(ProdutoRepository produtoRepository,
                                          ProdutoMapper produtoMapper,
                                          List<ProdutoValidator> validators) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
        this.validators = validators;
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        System.out.println(">>> Salvando produto: " + produtoRequestDTO.getNome());

        Produto produto = produtoMapper.toProduto(produtoRequestDTO);
        validarProduto(produto);
        Produto produtoSalvo = produtoRepository.save(produto);

        System.out.println(">>> Produto salvo com ID: " + produtoSalvo.getId());
        return produtoMapper.toResponse(produtoSalvo);
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        System.out.println(">>> Listando todos os produtos");

        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> response = new ArrayList<>();

        for (Produto produto : produtos) {
            response.add(produtoMapper.toResponse(produto));
        }

        System.out.println(">>> Total de produtos encontrados: " + response.size());
        return response;
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        System.out.println(">>> Buscando produto com ID: " + id);

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID: " + id + " não encontrado."));

        System.out.println(">>> Produto encontrado: " + produto.getNome());
        return produtoMapper.toResponse(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        System.out.println(">>> Atualizando produto ID: " + id);

        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID: " + id + " não encontrado."));

        produtoExistente.setNome(produtoRequestDTO.getNome());
        produtoExistente.setPreco(produtoRequestDTO.getPreco());

        validarProduto(produtoExistente);
        Produto produtoAtualizado = produtoRepository.save(produtoExistente);

        System.out.println(">>> Produto atualizado com sucesso!");
        return produtoMapper.toResponse(produtoAtualizado);
    }

    @Override
    public void deletar(Long id) {
        System.out.println(">>> Deletando produto ID: " + id);

        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto com ID: " + id + " não encontrado.");
        }

        produtoRepository.deleteById(id);
        System.out.println(">>> Produto deletado com sucesso!");
    }

    private void validarProduto(Produto produto) {
        for (ProdutoValidator validator : validators) {
            validator.validar(produto);
        }
    }
}