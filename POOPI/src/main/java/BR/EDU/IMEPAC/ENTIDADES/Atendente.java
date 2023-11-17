/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BR.EDU.IMEPAC.ENTIDADES;

/**
 *
 * @author lucii
 */
public class Atendente {
    private String nome;
    private String cpf;
    private String data_nascimento;
    private String registro_funcionario;

    public Atendente(String nome, String cpf, String data_nascimento, String registro_funcionario) {
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.registro_funcionario = registro_funcionario;
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
