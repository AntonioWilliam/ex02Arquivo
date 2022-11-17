package controller;

import java.io.IOException;

public interface Isteam {
	public void leDiretorio (String path) throws IOException ;
	public void geraArquivo (String path, String ano, String mes) throws IOException ;
	public void leArquivo (String path, String nome,String ano, String mes, int media) throws IOException ;
	
	
}
