public class Entrega {
    private int codigo;
    private double valor;
    private String descricao;
    private Cliente cliente;


    public Entrega(int codigo, double valor, String descricao, Cliente cliente) {
        this.codigo = codigo;
        this.valor = valor;
        this.descricao = descricao;
        this.cliente = cliente;
    }

    public double getValor() {
        return this.valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public Cliente getCliente(){
        return cliente;
    }
}
