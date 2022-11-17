package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class controllerSteam implements Isteam {

	@Override
	public void leDiretorio(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] lista = dir.listFiles(); //
			for (File f : lista) {
				if (f.isDirectory()) {
					System.out.println("[" + f.getName() + "]");
				}
			}
			for (File f : lista) {
				if (f.isFile()) {
					System.out.println(f.getName());
				}
			}
		} else {
			throw new IOException("Diretorio invalido");
		}

	}

	@Override
	public void leArquivo(String path, String nome, String ano, String mes, int media) throws IOException {
		File arquivo = new File(path, nome);
		if (arquivo.exists()) {
			FileInputStream fluxo =
					new FileInputStream(arquivo);
			InputStreamReader leitor =
					new InputStreamReader(fluxo);
			BufferedReader buffer = 
					new BufferedReader(leitor);
			String linha = buffer.readLine();

			System.out.printf("| %-60s | %-30s |", "Nome do jogo", "Media de jogadores ativos");
			System.out.println();

			while (linha != null) {
				String vet[] = linha.trim().split(",");
				if (vet[1].equals(ano) && vet[2].equals(mes) && Double.parseDouble(vet[3]) >= media) {
					System.out.printf("| %-60s | %-40s |", vet[0], vet[5]);
					System.out.println();
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo invalido");
		}
	}

	@Override
	public void geraArquivo(String path, String ano, String mes) throws IOException {
		File dir = new File(path);
		File arquivo = new File(path, "Steam" + mes + ano + ".csv");
		if (dir.exists()) {
			boolean arquivoExiste = false;
			if (arquivo.exists()) {
				arquivoExiste = true;
			}
			String conteudo = geraConteudoArq(ano, mes);
			FileWriter fw = new FileWriter(arquivo, arquivoExiste);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(conteudo);
			pw.flush();
			pw.close();
			fw.close();
		} else {
			throw new IOException("Diretório Inválido");
		}

	}

	private String geraConteudoArq(String ano, String mes) throws IOException {
		StringBuffer buffer = new StringBuffer();
		File leArq = new File("C:\\TEMP", "SteamCharts.csv");
		FileInputStream fluxo = new FileInputStream(leArq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader b = new BufferedReader(leitor);
		String linha = b.readLine();

		buffer.append("Nome do jogo, Média dos jogadores ativos \r\n");

		while (linha != null) {
			String vet[] = linha.trim().split(",");
			if (vet[1].equals(ano) && vet[2].equals(mes)) {
				buffer.append(vet[0] + ", " + vet[3] + "\r\n");
			}
			linha = b.readLine();
		}
		return buffer.toString();

	}
}
