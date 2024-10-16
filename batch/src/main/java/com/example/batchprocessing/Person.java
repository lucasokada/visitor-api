package com.example.batchprocessing;

public record Person(
        String nome,
        String cpf,
        String dataNascimento,
        String rua,
        Integer numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String telefoneResidencial,
        String telefoneComercial,
        String celular,
        String email,
        String usuario,
        String senha,
        String tipoVisitante,
        String empresaNome,
        String empresaCnpj
) {

}
