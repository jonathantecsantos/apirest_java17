package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacoPaciente(
        @NotNull
        Long id,
        String nome) {
}
