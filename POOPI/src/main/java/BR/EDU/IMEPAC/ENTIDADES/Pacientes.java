/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BR.EDU.IMEPAC.ENTIDADES;

/**
 *
 * @author lucii
 */
public class Pacientes {
    
    private String nome;
    private String cpf;
    private String rg;
    private String data_nascimento;
    private int registro_paciente;

    public Pacientes(String nome, String cpf, String rg, String data_nascimento, int registro_paciente) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data_nascimento = data_nascimento;
        this.registro_paciente = registro_paciente;
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
