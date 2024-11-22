// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

public class Diploma {
	private String idDiploma;
	private String idAluno;
	private String nomeAluno;
	private String universidade;
	private String curso;

	public Diploma(String idDiploma, String idAluno, String nomeAluno, String universidade, String curso) {
		this.idDiploma = idDiploma;
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.universidade = universidade;
		this.curso = curso;
	}

	public String getIDDiploma() { return idDiploma; }
	public String getIDAluno() { return idAluno; }
	public String getNomeAluno() { return nomeAluno; }
	public String getUniversidade() { return universidade; }
	public String getCurso() { return curso; }

	public void setIDDiploma(String idDiploma) {
		this.idDiploma = idDiploma;
	}

	public void setIDAluno(String idAluno) {
		this.idAluno = idAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}
