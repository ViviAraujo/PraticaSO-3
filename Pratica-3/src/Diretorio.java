
public class Diretorio {
	
	private Diretorio primeiro;
	private Diretorio ultimo;
	private Diretorio proximo;
	private Arquivo arquivo;
	private String nome;
	private Arquivo primeiroArq;
	private Arquivo ultimoArq;
	private static int CapacidadeTotal=20;
	private int CapacidadeAtual;
	
	
	
	public Diretorio() {
		this.CapacidadeAtual=CapacidadeTotal;
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


	//Create a new Folder
	public void CreateFolder(Diretorio ListaDiretorios, Diretorio novoDir) {
		if(CapacidadeAtual>0) {
			if(ListaDiretorios.primeiro== null) {
				ListaDiretorios.primeiro= novoDir;
				ListaDiretorios.ultimo=novoDir;
				CapacidadeAtual--;		
			}else {
				ListaDiretorios.ultimo.proximo=novoDir;
				ListaDiretorios.ultimo=novoDir;
				CapacidadeAtual--;		
			}
		}else {
			System.out.println("Sistema sem Capacidade");
		}
		
		
	}
	
	//Search Folder
	public Diretorio SearchFolder(Diretorio ListaDiretorios, String nomeDiretorio) {
		Diretorio aux = ListaDiretorios.primeiro;
		Diretorio procurada= null;
		
		while( aux!= null) {
			if(aux.getNome().equals(nomeDiretorio)){
				procurada= aux;
				aux= null;
			}else { 
				aux= aux.getProximo();
				
			}
			
		}
		
		return procurada;
		
	}
	
	//Delete a Folder
	
	public void DeleteFolder (Diretorio ListaDiretorios, Diretorio novoDir) {
		
		Diretorio aux = ListaDiretorios.primeiro;
		Diretorio anterior= null;
		int quantidadeRemovidaArquivos= QuantidadeArquivosNoDiretorio(novoDir) + 1; // o +1 representa o dir em si
		while( aux!= null) {
			if(aux.getNome().equals(novoDir.getNome())){
				if(anterior == null) {
					ListaDiretorios.primeiro= aux.getProximo();
				}else {
					anterior.setProximo(aux.getProximo());
				}
				aux= null;
			}else { 
				anterior= aux;
				aux= aux.getProximo();
				
			}
			
		}
		ListaDiretorios.setCapacidadeAtual(ListaDiretorios.getCapacidadeAtual()+ quantidadeRemovidaArquivos);
	}
	
	public void ImprimirList(Diretorio list) {
		Diretorio aux= null;
		aux=list.primeiro;
		while(aux!= null) {
			System.out.println(aux.getNome());
			aux= aux.getProximo();
		}
	}
	
	
	public void AdicionarArchive(Diretorio list,String nomeDiretorio, Arquivo archive) {
		Diretorio aux = SearchFolder(list, nomeDiretorio);
			if(aux.arquivo==null) {
				aux.primeiroArq= archive;
				aux.arquivo= archive;
				aux.ultimoArq=archive;
				
			}
			else {
				aux.ultimoArq.setProximo(archive);
				aux.ultimoArq=archive;
			}
	}
		
	//Visão Baixo Nivel
	public void ImprimirArquivosEmDiretorios(Diretorio list) {
		int i=0;
		Diretorio aux= null;
		aux=list.primeiro;
		while(aux!= null) {
			System.out.println(" "+i +" - "+ aux.getNome());
			Arquivo aux2= aux.primeiroArq;
			i++;
			while(aux2!= null) {
				System.out.println(" "+ i +" - " + aux2.getNome());
				i++;
				aux2= aux2.getProximo();
			}
			aux= aux.getProximo();
		}
	}
	
	public void RemoverArquivo(Diretorio list,String nomeArquivo, String nomeDiretorio) {
		Diretorio aux = SearchFolder(list, nomeDiretorio);
		Arquivo aux2= aux.arquivo;
		
		while( aux2!= null) {
			if(aux2.getNome().equals(nomeArquivo)){
				aux2.setNome("vazio");
				aux= null;
			}else { 
				aux2= aux2.getProximo();
				
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
			if(vazios >=tamanho) {
				SubstituirVazios(aux1,arquivoName, tamanho);
			}
		}
	}
	
	public void SubstituirVazios(Diretorio dirt,String nomeArquivo, int tamanho) {
		Arquivo aux= dirt.primeiroArq;
		int contador=0;
		
		while(aux!= null ) {
			if(contador < tamanho) {
				if(aux.getNome().equals("vazio")) {
					aux.setNome(nomeArquivo);
					contador++;
				}
			}
			aux= aux.getProximo();
		}
	}
	
	public int QuantidadeVazios(Diretorio dirt) {
		Arquivo aux= dirt.primeiroArq;
		int totalVazio=0;
		while(aux!= null) {
			if(aux.getNome().equals("vazio")) {
				totalVazio ++;
			}
			aux= aux.getProximo();
		}
		return totalVazio;
	}
	
	public int QuantidadeArquivosNoDiretorio(Diretorio dirt) {
		Arquivo aux= dirt.primeiroArq;
		int totalArquivos=0;
		while(aux!= null) {
			totalArquivos ++;
			aux= aux.getProximo();
		}
		return totalArquivos;
	}
	
	//Lista todos os arquivos de um Determinado Diretório
	public void ListarArquivosEmDiretorios(Diretorio list,String nomeDiretorio) {
		Diretorio aux = SearchFolder(list, nomeDiretorio);
		String nomeAnterior;
		int i= 1;
		Arquivo aux2 =aux.primeiroArq;
		nomeAnterior="";
		
			while(aux2!= null) {
				if(nomeAnterior.equals(aux2.getNome())) {
					
				}else {
					nomeAnterior=aux2.getNome();
					if(nomeAnterior != "vazio") {
						System.out.println(" "+ i + "-" + aux2.getNome());
						i++;
					}
				}
				
				aux2= aux2.getProximo();
			}
			
		
	}
	
	
	public static void main(String[] args) {
		
		Diretorio lista = new Diretorio();
		Diretorio dirt1 = new Diretorio("dirt1");
		Diretorio dirt2 = new Diretorio("dirt2");
		Diretorio dirt3 = new Diretorio("dirt3");
		
		lista.CreateFolder(lista, dirt1);
		lista.CreateFolder(lista, dirt2);
		lista.CreateFolder(lista, dirt3);
		
//		lista.ImprimirList(lista);
		
//		lista.DeleteFolder(lista, dirt1);
//		System.out.println("**************");	
//		lista.ImprimirList(lista);
		
//		Arquivo arq1 = new Arquivo("arq1", "dirt1");
//		Arquivo arq2 = new Arquivo("arq2", "dirt1");
		
			
		
		
		lista.AdicionarBlocos(lista, "dirt1", 2, "arq1");
		lista.AdicionarBlocos(lista, "dirt1", 3, "arq2");
		lista.AdicionarBlocos(lista, "dirt1", 4, "arq3");
		
//	lista.RemoverArquivo(lista, "arq2", "dirt1");
//	lista.AdicionarBlocos(lista, "dirt1", 2, "arq4");
		
//		lista.ImprimirArquivosEmDiretorios(lista);
		
//		lista.DeleteFolder(lista, dirt1);
		
		lista.ImprimirArquivosEmDiretorios(lista);

//		System.out.println("*******************************");
//		lista.ListarArquivosEmDiretorios(lista, "dirt1");
//		System.out.println("*******************************");
//		lista.AdicionarBlocos(lista, "dirt2", 2, "arq3");
//		lista.ListarArquivosEmDiretorios(lista, "dirt2");
	}

}
