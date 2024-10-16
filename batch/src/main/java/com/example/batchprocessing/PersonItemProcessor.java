package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(final Person person) {
		final String nome = person.nome();
		final String cpf = person.cpf();
		final String dataNascimento = person.dataNascimento();
		final String rua = person.rua();
		final Integer numero = person.numero();
		final String bairro = person.bairro();
		final String cidade = person.cidade();
		final String estado = person.estado();
		final String cep = person.cep();
		final String telefoneResidencial = person.telefoneResidencial();
		final String telefoneComercial = person.telefoneComercial();
		final String celular = person.celular();
		final String email = person.email();
		final String usuario = person.usuario();
		final String senha = person.senha();
		final String tipoVisitante = person.tipoVisitante();
		final String empresaNome = person.empresaNome();
		final String empresaCnpj = person.empresaCnpj();

		final Person transformedPerson = new Person(nome, cpf, dataNascimento, rua, numero, bairro, cidade, estado,
				cep, telefoneResidencial, telefoneComercial, celular, email, usuario, senha, tipoVisitante, empresaNome,
				empresaCnpj);

		log.info("Converting (" + person + ") into (" + transformedPerson + ")");

		return transformedPerson;
	}

}
