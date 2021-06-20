package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SistemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Dados obrigatórios não informados.")
    @Column(nullable = false, length = 100)
    private String descricao;

    @NotNull(message = "Dados obrigatórios não informados.")
    @Column(nullable = false, length = 10)
    private String sigla;

    @Column(length = 100)
    @Email(message = "E-mail inválido.")
    private String email_de_atendimento_sistema;

    @Column(length = 50)
    private String url_sistema;

    @Column(nullable = false)
    private String status;

    @Column(length = 100)
    private String usuario_ultima_alteracao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column
    private LocalDateTime data_ultima_alteracao;

    @Column(nullable = false, length = 500)
    private String justificativa_ultima_alteracao;

    @PrePersist
    public void prePersist(){
        setData_ultima_alteracao(LocalDateTime.now());
    }

}
