package deloitte.bootcamp.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
