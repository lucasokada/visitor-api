package com.example.servicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceAApplication {

    private final List<VisitanteModel> visitantes = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }

    @GetMapping("/visitantes")
    public List<VisitanteModel> getVisitantes() {
        return visitantes; // memória de objetos para armazenar os visitantes 
    }

    @PostMapping("/visitante")
    public String addVisitante(@RequestBody VisitanteModel visitante) {
        visitantes.add(visitante); // armazenados na lista em memória
        return "Visitante " + visitante.getNome() + " adicionado com sucesso!";
    }
}
