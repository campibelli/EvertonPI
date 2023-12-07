package BR.EDU.IMEPAC.ENTIDADES;

/**
 *
 * @author lucii
 */
public class Atendente {
    private Long id;
    private String nome;
    private String cpf;
    private String data_nascimento;
    private String registro_funcionario;

    public Atendente(Long id, String nome, String cpf, String data_nascimento, String registro_funcionario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.registro_funcionario = registro_funcionario;
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

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getRegistro_funcionario() {
        return registro_funcionario;
    }

    public void setRegistro_funcionario(String registro_funcionario) {
        this.registro_funcionario = registro_funcionario;
    }
}