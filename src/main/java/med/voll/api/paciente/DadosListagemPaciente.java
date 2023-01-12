package med.voll.api.paciente;

public record DadosListagemPaciente(Long id, String nome, String Email) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }

}
