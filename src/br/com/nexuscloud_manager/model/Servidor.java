package br.com.nexuscloud_manager.model;

import java.util.ArrayList;
import java.util.List;

import br.com.nexuscloud_manager.exception.CapacidadeExcedidaException;
import br.com.nexuscloud_manager.exception.ServidorOfflineException;

public class Servidor {
    private String ip;
    private double ramTotal;
    private double precoPorHora;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
    }

    public void ligar() {
        statusLigado = true;
    }

    public void desligar() {
        statusLigado = false;
    }

    public void alocarContainer(Container c)
            throws CapacidadeExcedidaException {

        if (!statusLigado) {
            throw new ServidorOfflineException(
                "Erro: Não é possível alocar containers em um servidor desligado."
            );
        }

        double ramAposAlocacao =
                calcularRamEmUso() + c.getRamNecessaria();

        if (ramAposAlocacao > ramTotal) {
            throw new CapacidadeExcedidaException(
                "Falha Crítica: Memória RAM insuficiente no servidor "
                + ip + "."
            );
        }

        containers.add(c);
    }

    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Servidor other = (Servidor) obj;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        return true;
    }

}
