package br.com.nexuscloud_manager.main;

import java.util.Map;
import java.util.Set;

import br.com.nexuscloud_manager.exception.CapacidadeExcedidaException;
import br.com.nexuscloud_manager.model.Cliente;
import br.com.nexuscloud_manager.model.CloudManager;
import br.com.nexuscloud_manager.model.Container;
import br.com.nexuscloud_manager.model.Servidor;

public class MainCloud {
   public static void main(String[] args) {
    CloudManager manager = new CloudManager();

        Cliente cliente = new Cliente(
            "111.222.333-44",
            "Wayne Enterprises"
        );

        Servidor servidor = new Servidor(
            "192.168.0.1",
            16.0,
            5.50
        );

        manager.adicionarServidor(cliente, servidor);

        Container container1 = new Container(
            "C-01",
            "Sistema Wayne",
            10.0
        );

        Container container2 = new Container(
            "C-02",
            "Banco de Dados",
            8.0
        );

        servidor.ligar();

        try {
            servidor.alocarContainer(container1);
            System.out.println("Container C-01 alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            servidor.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        double fatura = manager.calcularFaturaAtiva(cliente);

        System.out.printf(
            "Fatura ativa do cliente: R$ %.2f%n",
            fatura
        );
    }
   } 

