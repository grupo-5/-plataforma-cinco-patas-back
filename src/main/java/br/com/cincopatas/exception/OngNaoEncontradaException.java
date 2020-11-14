package br.com.cincopatas.exception;

public class OngNaoEncontradaException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public OngNaoEncontradaException(String mensagem) {
		super(mensagem);		
	}

	public OngNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de ong com código %d", id));
	}
}
