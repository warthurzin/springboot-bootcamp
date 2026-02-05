package deloitte.bootcamp.controller;

import deloitte.bootcamp.dto.ProdutoRequestDTO;
import deloitte.bootcamp.dto.ProdutoResponseDTO;
import deloitte.bootcamp.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/hello")
    @Operation(summary = "Verifica status da API", description = "Endpoint simples para verificar se a API está funcionando")
    public String hello() {
        return "API de Produtos rodando com Spring Boot";
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista completa de todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca produto por ID", description = "Retorna os detalhes de um produto específico baseado no ID")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo produto", description = "Cria um novo produto no sistema com nome e preço")
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoSalvo = produtoService.salvar(produto);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto existente", description = "Atualiza os dados de um produto existente pelo ID")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, produtoRequestDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto", description = "Remove permanentemente um produto do sistema pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
