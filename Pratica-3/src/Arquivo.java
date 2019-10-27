
public class Arquivo {
	private String nome;
	private int tamanho;
	private Arquivo Proximo;
	private String DiretorioName;
	private int posicao;
	

	
	public Arquivo(String nome ,String diretorioName) {
		super();
		this.nome = nome;
		DiretorioName = diretorioName;
	}

	@Override
	public String toString() {
		return "Arquivo [nome=" + nome + ", tamanho=" + tamanho + ", Proximo=" + Proximo + ", DiretorioName="
				+ DiretorioName + "]";
	}
	
	
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public Arquivo getProximo() {
		return Proximo;
	}
	public void setProximo(Arquivo proximo) {
		Proximo = proximo;
	}
	public String getDiretorioName() {
		return DiretorioName;
	}
	public void setDiretorioName(String diretorioName) {
		DiretorioName = diretorioName;
	}
	

}
