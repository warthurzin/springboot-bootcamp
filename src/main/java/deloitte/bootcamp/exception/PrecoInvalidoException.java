package deloitte.bootcamp.exception;

public class PrecoInvalidoException extends RuntimeException {

    private final Double precoInformado;

    public PrecoInvalidoException(String mensagem, Double precoInformado) {
        super(mensagem);
        this.precoInformado = precoInformado;
    }

    public Double getPrecoInformado() {
        return precoInformado;
    }
}