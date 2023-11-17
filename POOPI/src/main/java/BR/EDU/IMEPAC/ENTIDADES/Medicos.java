/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BR.EDU.IMEPAC.ENTIDADES;

/**
 *
 * @author lucii
 */
public class Medicos {

    private String nome;
    private String cpf;
    private String CRM;
    private String data_nascimento;
    private Boolean convenio;

    public Medicos(String nome, String cpf, String CRM, String data_nascimento, Boolean convenio) {
        this.nome = nome;
        this.cpf = cpf;
        this.CRM = CRM;
        this.data_nascimento = data_nascimento;
        this.convenio = convenio;
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