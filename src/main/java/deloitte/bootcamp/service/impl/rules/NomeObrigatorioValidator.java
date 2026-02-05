package deloitte.bootcamp.service.impl.rules;

import deloitte.bootcamp.exception.NomeInvalidoException;
import deloitte.bootcamp.model.Produto;
import deloitte.bootcamp.service.ProdutoValidator;
import org.springframework.stereotype.Component;

@Component
public class NomeObrigatorioValidator implements ProdutoValidator {

    @Override
    public void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new NomeInvalidoException(
                    "O nome do produto é obrigatório",
                    produto.getNome()
            );
        }
    }
}