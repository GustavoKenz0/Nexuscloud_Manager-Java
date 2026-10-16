package br.com.nexuscloud_manager.model;

public class Cliente {
    private String cpf;
    private String nomeEmpresa;

    public Cliente(String cpf, String nomeEmpresa) {
        this.cpf = cpf;
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Cliente other = (Cliente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    
    
}
