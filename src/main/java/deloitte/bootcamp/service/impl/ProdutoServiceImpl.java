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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final List<ProdutoValidator> validators;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper, List<ProdutoValidator> validators) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
        this.validators = validators;
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoMapper.toProduto(produtoRequestDTO);

        validarProduto(produto);

        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toResponse(produtoSalvo);
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> response = new ArrayList<>();

        for (Produto produto : produtos) {
            response.add(produtoMapper.toResponse(produto));
        }

        return response;
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID: " + id + " não encontrado."));

        return produtoMapper.toResponse(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO) {

        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID: " + id + " não encontrado."));

        produtoExistente.setNome(produtoRequestDTO.getNome());
        produtoExistente.setPreco(produtoRequestDTO.getPreco());

        validarProduto(produtoExistente);

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return produtoMapper.toResponse(produtoAtualizado);
    }

    @Override
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto com ID: " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    private void validarProduto(Produto produto) {
        List<String> erros = new ArrayList<>();

        for (ProdutoValidator validator : validators) {
            String erro = validator.validar(produto);
            if (erro != null) {
                erros.add(erro);
            }
        }

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }
    }
}