package BR.EDU.IMEPAC.ENTIDADES;

public class Medicos {

    private Long id;
    private String nome;
    private String cpf;
    private String CRM;
    private String especialidade;  // Added Especialidade field

    public Medicos(Long id, String nome, String cpf, String CRM, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.CRM = CRM;
        this.especialidade = especialidade;
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

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}