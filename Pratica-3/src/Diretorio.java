import java.util.Scanner;

public class Diretorio {

	private Diretorio primeiro;
	private Diretorio ultimo;
	private Diretorio proximo;
	private Arquivo arquivo;
	private String nome;
	private Arquivo primeiroArq;
	private Arquivo ultimoArq;
	private static int CapacidadeTotal = 15;
	private int CapacidadeAtual;

	public Diretorio() {
		this.CapacidadeAtual = CapacidadeTotal;
	}

	public Arquivo getPrimeiroArq() {
		return primeiroArq;
	}

	public void setPrimeiroArq(Arquivo primeiroArq) {
		this.primeiroArq = primeiroArq;
	}

	public Arquivo getUltimoArq() {
		return ultimoArq;
	}

	public void setUltimoArq(Arquivo ultimoArq) {
		this.ultimoArq = ultimoArq;
	}

	public Diretorio(String nome) {
		this.nome = nome;
	}

	public Diretorio getPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(Diretorio primeiro) {
		this.primeiro = primeiro;
	}

	public Diretorio getUltimo() {
		return ultimo;
	}

	public void setUltimo(Diretorio ultimo) {
		this.ultimo = ultimo;
	}

	public Diretorio getProximo() {
		return proximo;
	}

	public void setProximo(Diretorio proximo) {
		this.proximo = proximo;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidadeAtual() {
		return CapacidadeAtual;
	}

	public void setCapacidadeAtual(int capacidadeAtual) {
		CapacidadeAtual = capacidadeAtual;
	}

	// Create a new Folder
	public void CreateFolder(Diretorio ListaDiretorios, Diretorio novoDir) {
		if (CapacidadeAtual > 0) {
			if (ListaDiretorios.primeiro == null) {
				ListaDiretorios.primeiro = novoDir;
				ListaDiretorios.ultimo = novoDir;
				CapacidadeAtual--;
			} else {
				ListaDiretorios.ultimo.proximo=novoDir;
				ListaDiretorios.ultimo = novoDir;
				CapacidadeAtual--;
			}
		} else {
			System.out.println("Sistema sem Capacidade");
		}

	}

	// Search Folder
	public Diretorio SearchFolder(Diretorio ListaDiretorios, String nomeDiretorio) {
		Diretorio aux = ListaDiretorios.primeiro;
		Diretorio procurada = null;

		while (aux != null) {
			if (aux.getNome().equals(nomeDiretorio)) {
				procurada = aux;
				aux = null;
			} else {
				aux = aux.getProximo();

			}

		}

		return procurada;

	}

	// Delete a Folder

	public void DeleteFolder(Diretorio ListaDiretorios, Diretorio novoDir) {

		Diretorio aux = ListaDiretorios.primeiro;
		Diretorio anterior = null;
		int quantidadeRemovidaArquivos = QuantidadeArquivosNoDiretorio(novoDir) + 1; // o +1 representa o dir em si
		while (aux != null) {
			if (aux.getNome().equals(novoDir.getNome())) {
				if (anterior == null) {
					ListaDiretorios.primeiro = aux.getProximo();
					aux.setProximo(null);
				} 
				else if(aux.getNome().equals(ListaDiretorios.ultimo.getNome())) {
					anterior.setProximo(null);
					aux.setProximo(null);
					ListaDiretorios.ultimo=anterior;
				}
				else {
					anterior.setProximo(aux.getProximo());
					aux.setProximo(null);
				}
				aux=null;
			} else {
				anterior = aux;
				aux = aux.getProximo();

			}

		}
		ListaDiretorios.setCapacidadeAtual(ListaDiretorios.getCapacidadeAtual() + quantidadeRemovidaArquivos);
	}
	
	public void RemoverArquivoVazios(Diretorio list, int tamanho) {
		Diretorio aux = list.primeiro;
		Arquivo aux2= aux.primeiroArq;
		Arquivo anterior= null;
		while(aux!=null) {
			for( int i=0;i<tamanho;i++) {
				while( aux2!= null) {
					if(aux2.getNome().equals("vazio")){
						anterior.setProximo(aux2.getProximo());
						aux2= null;
					}else { 
						anterior=aux2;
						aux2= aux2.getProximo();
						
					}
					
				}
				aux2=aux.primeiroArq;
			}
			aux= aux.getProximo();
		}
		
		list.setCapacidadeAtual(list.getCapacidadeAtual() + tamanho);
	}
	

	public void ImprimirList(Diretorio list) {
		Diretorio aux = null;
		aux = list.primeiro;
		while (aux != null) {
			System.out.println(aux.getNome());
			aux = aux.getProximo();
		}
	}

	public void AdicionarArchive(Diretorio list, String nomeDiretorio, Arquivo archive) {
		Diretorio aux = SearchFolder(list, nomeDiretorio);
		if (aux.arquivo == null) {
			aux.primeiroArq = archive;
			aux.arquivo = archive;
			aux.ultimoArq = archive;

		} else {
			aux.ultimoArq.setProximo(archive);
			aux.ultimoArq = archive;
		}
	}

	// Visão Baixo Nivel
	public void ImprimirArquivosEmDiretorios(Diretorio list) {
		int i = 0;
		Diretorio aux = null;
		aux = list.primeiro;
		while (aux != null) {
			System.out.println(" " + i + " - " + aux.getNome());
			Arquivo aux2 = aux.primeiroArq;
			i++;
			while (aux2 != null) {
				System.out.println(" " + i + " - " + aux2.getNome());
				i++;
				aux2 = aux2.getProximo();
			}
			aux = aux.getProximo();
		}
	}
	
	public int  QuantidadeVazioGeral(Diretorio list) {
		Diretorio aux = null;
		int totalVazio =0;
		aux = list.primeiro;
		while (aux != null) {
			if (aux.getNome().equals("vazio")) {
				totalVazio++;
			}
			Arquivo aux2 = aux.primeiroArq;
		
			while (aux2 != null) {
				if (aux2.getNome().equals("vazio")) {
					totalVazio++;
				}
				aux2 = aux2.getProximo();
			}
			aux = aux.getProximo();
		}
		return totalVazio;
	}

	

	public void RemoverArquivo(Diretorio list, String nomeArquivo, String nomeDiretorio) {
		Diretorio aux = SearchFolder(list, nomeDiretorio);
		Arquivo aux2 = aux.arquivo;

		while (aux2 != null) {
			if (aux2.getNome().equals(nomeArquivo)) {
				aux2.setNome("vazio");
				aux = null;
			} else {
				aux2 = aux2.getProximo();

			}

		}
	}

	public void AdicionarBlocos(Diretorio list,String nomeDiretorio, int tamanho, String arquivoName) {
		Diretorio aux1= list.SearchFolder(list, nomeDiretorio);
		
		if (list.getCapacidadeAtual()>= tamanho) {
			for( int i=0;i<tamanho;i++) {
				Arquivo aux = new Arquivo(arquivoName, nomeDiretorio);
				list.AdicionarArchive(list, aux.getDiretorioName(), aux);
			}
			list.setCapacidadeAtual(list.getCapacidadeAtual()- tamanho);
		}else {
//			System.out.println("Não há espaço");
			int vazios = QuantidadeVazios(aux1);
			int VazioTotal = QuantidadeVazioGeral(list); 
			if(vazios >=tamanho) {
				SubstituirVazios(aux1,arquivoName, tamanho);
			}else if(VazioTotal >= tamanho) {
				RemoverArquivoVazios(list, VazioTotal);
				AdicionarBlocos(list, nomeDiretorio, tamanho, arquivoName);
			}
		}
	}
	

	public void SubstituirVazios(Diretorio dirt, String nomeArquivo, int tamanho) {
		Arquivo aux = dirt.primeiroArq;
		int contador = 0;

		while (aux != null) {
			if (contador < tamanho) {
				if (aux.getNome().equals("vazio")) {
					aux.setNome(nomeArquivo);
					contador++;
				}
			}
			aux = aux.getProximo();
		}
	}


	public int QuantidadeVazios(Diretorio dirt) {
		Arquivo aux = dirt.primeiroArq;
		int totalVazio = 0;
		while (aux != null) {
			if (aux.getNome().equals("vazio")) {
				totalVazio++;
			}
			aux = aux.getProximo();
		}
		return totalVazio;
	}
	


	public int QuantidadeArquivosNoDiretorio(Diretorio dirt) {
		Arquivo aux = dirt.primeiroArq;
		int totalArquivos = 0;
		while (aux != null) {
			totalArquivos++;
			aux = aux.getProximo();
		}
		return totalArquivos;
	}

	// Lista todos os arquivos de um Determinado Diretório - alto nivel
	public void ListarArquivosEmDiretorios(Diretorio list, String nomeDiretorio) {
		Diretorio aux = SearchFolder(list, nomeDiretorio);
		String nomeAnterior;
		int i = 1;
		Arquivo aux2 = aux.primeiroArq;
		nomeAnterior = "";

		while (aux2 != null) {
			if (nomeAnterior.equals(aux2.getNome())) {

			} else {
				nomeAnterior = aux2.getNome();
				if (nomeAnterior != "vazio") {
					System.out.println(" " + i + "-" + aux2.getNome());
					i++;
				}
			}

			aux2 = aux2.getProximo();
		}

	}

	public static void main(String[] args) {

		Diretorio lista = new Diretorio();
		Diretorio dirt1 = new Diretorio("dirt1");
		Diretorio dirt2 = new Diretorio("dirt2");
		Diretorio dirt3 = new Diretorio("dirt3");
		Diretorio dirt = new Diretorio();
		Diretorio dirt4 = new Diretorio("dirt4");
//		Diretorio dirt5 = new Diretorio("dirt5");
		
		lista.CreateFolder(lista, dirt1);
		lista.CreateFolder(lista, dirt2);
		lista.CreateFolder(lista, dirt3);
//		lista.CreateFolder(lista, dirt4);
//		lista.CreateFolder(lista, dirt5);
//		
//        lista.DeleteFolder(lista, dirt4);
////		
//		lista.CreateFolder(lista, dirt4);
//		
//		lista.ImprimirList(lista);
		
		lista.AdicionarBlocos(lista, "dirt1", 2, "arq1");
		lista.AdicionarBlocos(lista, "dirt1", 3, "arq2");
		lista.AdicionarBlocos(lista, "dirt1", 4, "arq3");
		lista.AdicionarBlocos(lista, "dirt1", 2, "arq4");
		
//---		lista.RemoverArquivo(lista, "arq2", "dirt1");
//		int vaz= lista.QuantidadeVazioGeral(lista);
//		System.out.println("vazT"+vaz);
//		lista.RemoverArquivoVazios(lista, "dirt1",vaz);
//	---	lista.AdicionarBlocos(lista, "dirt2", 2, "arq5");


		
		
	

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);

		String cmd = " ";
		String path = " ";
		String nomeDir = " ";
		String nomeArq = " ";

		while (true) {

			System.out.println("User/raiz/" + path);
			cmd = ler.nextLine();
			// LISTAGEM FISICA
			if (path.equals(" ") && cmd.equals("lsall")) {

				lista.ImprimirArquivosEmDiretorios(lista);

			}
			// LISTAGEM DE DIRETORIOS DO USUARIO
			else if (path.equals(" ") && cmd.equals("lsdir")) {

				lista.ImprimirList(lista);

			}
			// CRIAR DIRETORIO
			else if (path.equals(" ") && cmd.equals("createdir")) {
				//DIGITE NOME DO DIRETORIO QUE DESEJA CRIAR
				System.out.println("Digite o nome do diretório: ");
				nomeDir = ler.nextLine();
				dirt = new Diretorio(nomeDir);
				lista.CreateFolder(lista, dirt);
				lista.ImprimirList(lista);

			}
			// ACESSAR DIRETORIO
			else if (path.equals(" ") && cmd.equals("cd dir_")) {

				System.out.println("Digite o nome do diretório: ");
				nomeDir = ler.nextLine();
				//DIGITE NOME DO DIRETORIO QUE DESEJA ACESSAR
				if (lista.SearchFolder(lista, nomeDir) == null) {
					System.out.println("Não existe");
				} else {
					while (true) {
//						lista.ImprimirArquivosEmUmDiretorio(lista.SearchFolder(lista, nomeDir));
						lista.ListarArquivosEmDiretorios(lista, nomeDir);
						path = "cd dir_" + nomeDir;
						System.out.println("User/raiz/" + path);
						cmd = ler.nextLine();
						// DELETAR DIRETORIO
						if (cmd.equals("deletedir")) {
							//DIGITE NOME DO DIRETORIO QUE DESEJA DELETAR
							System.out.println("Confirme o nome do diretório: ");
							nomeDir = ler.nextLine();
							dirt = lista.SearchFolder(lista, nomeDir);
							lista.DeleteFolder(lista, dirt);
							lista.ImprimirList(lista);
							path = " ";
							break;

						}
						// CRIAR ARQUIVO
						else if (cmd.equals("createar")) {
							//DIGITE O NOME DO ARQUIVO QUE DESEJA CRIAR
							System.out.println("Digite o nome do arquivo: ");
							nomeArq = ler.nextLine();
							lista.AdicionarBlocos(lista, nomeDir, 2, nomeArq);

						}
						// DELETAR ARQUIVO
						else if (cmd.equals("deletear")) {
							//DIGITE O NOME DO ARQUIVO QUE DESEJA DELETAR
							System.out.println("Confirme o nome do arquivo: ");
							nomeArq = ler.nextLine();
							lista.RemoverArquivo(lista, nomeArq, nomeDir);
							

						}
						// VOLTAR PARA DIRETORIO RAIZ
						else if (cmd.equals("cd..")) {
							lista.ImprimirList(lista);
							path = " ";
							break;

						} else {
							System.out.println("Comando não reconhecido ou o elemento não existe");
						}
					}
				}
			}
			// SAIR DO CMD
			else if (cmd.equals("sair")) {

				break;

			} else {

				System.out.println("Comando não reconhecido");

			}
		}
		System.out.println("FIM DE PROGRAMA");
	}

}
