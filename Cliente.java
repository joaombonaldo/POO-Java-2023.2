import java.util.ArrayList;

public class Cliente{
    private String email;
    private String nome;
    private String endereco;
    private ArrayList<Entrega> entregasCliente;


    public Cliente(String email, String nome, String endereco) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        entregasCliente = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public boolean adicionaEntrega(Entrega entrega){
       if (entregasCliente.add(entrega)){
        return true;
       } 
       return false;
    }

    public ArrayList<Entrega> pesquisaEntregas(){
        if (entregasCliente != null){
            return entregasCliente;
        }
        return null;
    }
}