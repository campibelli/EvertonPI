package BR.EDU.IMEPAC.ENTIDADES;

/**
 *
 * @author lucii
 */
public class Medicos {

    private Long id;  // Added ID field
    private String nome;
    private String cpf;
    private String CRM;
    private String data_nascimento;
    private Boolean convenio;

    public Medicos(Long id, String nome, String cpf, String CRM, String data_nascimento, Boolean convenio) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.CRM = CRM;
        this.data_nascimento = data_nascimento;
        this.convenio = convenio;
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

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Boolean getConvenio() {
        return convenio;
    }

    public void setConvenio(Boolean convenio) {
        this.convenio = convenio;
    }
}