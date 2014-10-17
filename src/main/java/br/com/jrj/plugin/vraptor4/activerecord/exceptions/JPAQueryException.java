package br.com.jrj.plugin.vraptor4.activerecord.exceptions;

public class JPAQueryException extends RuntimeException {
	public JPAQueryException(String message) {
        super(message, null);
    }

    public JPAQueryException(String message, Throwable cause) {
        super(message, cause);
    }

	public static Throwable findBestCause(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}

}
