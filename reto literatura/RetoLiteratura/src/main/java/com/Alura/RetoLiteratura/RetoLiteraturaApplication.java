package com.Alura.RetoLiteratura;

import com.Alura.RetoLiteratura.Principal.Principal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetoLiteraturaApplication implements CommandLineRunner {

    private final Principal principal;

    public RetoLiteraturaApplication(Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(RetoLiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        principal.mostrarMenu();
    }
}
