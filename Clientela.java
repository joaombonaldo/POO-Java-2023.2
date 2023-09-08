import java.util.ArrayList;

public class Clientela {
        private ArrayList<Cliente> clientes;

    public Clientela() {
        this.clientes = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
    
    public boolean cadastraCliente(Cliente cliente){      
        Cliente aux = pesquisaCliente(cliente.getEmail());
        if (aux == null){
            return clientes.add(cliente);
        }
        return false;
    }

    public Cliente pesquisaCliente(String email){
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email))
                return cliente;
        }
        return null;
    }

    public int quantClienteCadastrados(){
        return clientes.size();
    }
}
