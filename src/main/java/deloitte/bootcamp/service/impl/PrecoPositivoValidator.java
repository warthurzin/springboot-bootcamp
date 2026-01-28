package deloitte.bootcamp.service.impl;

import deloitte.bootcamp.exception.PrecoInvalidoException;
import deloitte.bootcamp.model.Produto;
import deloitte.bootcamp.service.ProdutoValidator;
import org.springframework.stereotype.Component;

@Component
public class PrecoPositivoValidator implements ProdutoValidator {

    @Override
    public String validar(Produto produto) {
        if (produto.getPreco() == null) {
            throw new PrecoInvalidoException("O preço do produto é obrigatório", null);
        }

        if (produto.getPreco() <= 0) {
            throw new PrecoInvalidoException("O preço do produto é obrigatório", null);
        }

        return null;
    }
}