package deloitte.bootcamp.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoException extends RuntimeException {

    private final List<String> erros;

    public ValidacaoException(String mensagem) {
        super(mensagem);
        this.erros = new ArrayList<>();
        this.erros.add(mensagem);
    }

    public ValidacaoException(List<String> erros) {
        super("Erros de validação: " + String.join(", ", erros));
        this.erros = erros;
    }

    public List<String> getErros() {
        return erros;
    }
}