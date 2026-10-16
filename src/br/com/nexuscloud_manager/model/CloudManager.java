package br.com.nexuscloud_manager.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public void adicionarServidor(Cliente c, Servidor s) {
        bancoDeDados
                .computeIfAbsent(c, cliente -> new HashSet<>())
                .add(s);
    }

    public double calcularFaturaAtiva(Cliente c) {
        Set<Servidor> servidores = bancoDeDados.get(c);

        if (servidores == null) {
            return 0.0;
        }

        return servidores.stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}
