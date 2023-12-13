package BR.EDU.IMEPAC.ENTIDADES;

public class Pacientes {
    
    private Long id;
    private String cpf;
    private String nome;
    private String dataNascimento; // Changed from data_nascimento to dataNascimento
    private String endereco;
    private String historicoMedico;
    private String tipoSanguineo;

    public Pacientes(Long id, String cpf, String nome, String dataNascimento, String endereco, String historicoMedico, String tipoSanguineo) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.historicoMedico = historicoMedico;
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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