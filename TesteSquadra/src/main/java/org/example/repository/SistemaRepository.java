package org.example.repository;

import org.example.entity.SistemaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SistemaRepository extends JpaRepository<SistemaEntity, Integer> {

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where descricao ilike %:descricao% and sigla ilike %:sigla% and email_de_atendimento_sistema ilike %:email%",
            countQuery = "select count(*) from sistema_entity where descricao ilike %:descricao% and sigla ilike %:sigla% and email_de_atendimento_sistema ilike %:email%",
            nativeQuery = true)
    Page<SistemaEntity> findByParams(@Param("descricao") String descricao, @Param("sigla") String sigla, @Param("email") String email, PageRequest pageRequest);

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where descricao ilike %:descricao%",
            countQuery = "select count(*) from sistema_entity where descricao ilike %:descricao%",
            nativeQuery = true)
    Page<SistemaEntity> findByDescricao(@Param("descricao") String descricao, PageRequest pageRequest);

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where sigla ilike %:sigla%",
            countQuery = "select count(*) from sistema_entity where sigla ilike %:sigla%",
            nativeQuery = true)
    Page<SistemaEntity> findBySigla(@Param("sigla") String sigla, PageRequest pageRequest);

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where email_de_atendimento_sistema ilike %:email%",
            countQuery = "select count(*) from sistema_entity where email_de_atendimento_sistema ilike %:email%",
            nativeQuery = true)
    Page<SistemaEntity> findByEmailDeAtendimento(@Param("email") String email, PageRequest pageRequest);

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where descricao ilike %:descricao% and sigla ilike %:sigla%",
            countQuery = "select count(*) from sistema_entity where descricao ilike %:descricao% and sigla ilike %:sigla%",
            nativeQuery = true)
    Page<SistemaEntity> findByDescricaoSigla(@Param("descricao") String descricao, @Param("sigla") String sigla, PageRequest pageRequest);

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where descricao ilike %:descricao% and email_de_atendimento_sistema ilike %:email%",
            countQuery = "select count(*) from sistema_entity where descricao ilike %:descricao% and email_de_atendimento_sistema ilike %:email%",
            nativeQuery = true)
    Page<SistemaEntity> findByDescricaoEmail(@Param("descricao") String descricao, @Param("email") String email, PageRequest pageRequest);

    @Query(value = "select * from sistema_entity /*#pageRequest*/ where sigla ilike %:sigla% and email_de_atendimento_sistema ilike %:email%",
            countQuery = "select count(*) from sistema_entity where sigla ilike %:sigla% and email_de_atendimento_sistema ilike %:email%",
            nativeQuery = true)
    Page<SistemaEntity> findBySiglaEmail(@Param("sigla") String sigla, @Param("email") String email, PageRequest pageRequest);
}
