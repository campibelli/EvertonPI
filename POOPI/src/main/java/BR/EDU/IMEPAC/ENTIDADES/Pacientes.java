package BR.EDU.IMEPAC.ENTIDADES;

public class Pacientes {
    
    private Long id;
    private String cpf;
    private String nome;
    private String endereco; // Changed from data_nascimento to endereco
    private String historicoMedico;

    public Pacientes(Long id, String cpf, String nome, String endereco, String historicoMedico) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.historicoMedico = historicoMedico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }
}