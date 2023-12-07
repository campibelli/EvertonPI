package BR.EDU.IMEPAC.ENTIDADES;

public class Pacientes {
    
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String data_nascimento;
    private int registro_paciente;

    public Pacientes(Long id, String nome, String cpf, String rg, String data_nascimento, int registro_paciente) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data_nascimento = data_nascimento;
        this.registro_paciente = registro_paciente;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getRegistro_paciente() {
        return registro_paciente;
    }

    public void setRegistro_paciente(int registro_paciente) {
        this.registro_paciente = registro_paciente;
    }
}