package BR.EDU.IMEPAC.ENTIDADES;

public class Consultas {

    private Long id;
    private String nomePaciente;
    private String tipo_consulta;
    private String horario;

    public Consultas(Long id, String patientName, String doctorSpecialty, String date) {
        this.id = id;
        this.nomePaciente = patientName;
        this.tipo_consulta = doctorSpecialty;
        this.horario = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return nomePaciente;
    }

    public void setPatientName(String patientName) {
        this.nomePaciente = patientName;
    }

    public String getDoctorSpecialty() {
        return tipo_consulta;
    }

    public void setDoctorSpecialty(String doctorSpecialty) {
        this.tipo_consulta = doctorSpecialty;
    }

    public String getDate() {
        return horario;
    }

    public void setDate(String date) {
        this.horario = date;
    }


}