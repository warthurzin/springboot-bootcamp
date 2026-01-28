package deloitte.bootcamp.exception;

public class NomeInvalidoException extends RuntimeException {

    private final String nomeInformado;

    public NomeInvalidoException(String mensagem, String nomeInformado) {
        super(mensagem);
        this.nomeInformado = nomeInformado;
    }

    public String getNomeInformado() {
        return nomeInformado;
    }
}