package deloitte.bootcamp.controller;

import deloitte.bootcamp.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    private Long proximoId = 1L;

    @GetMapping("/hello")
    public String hello() {
        return "API de Produtos rodando com Spring Boot";
    }

    @GetMapping
    public List<Produto> listar() {
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
        return produto;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                produtos.remove(i);
                break;
            }
        }
    }
}
