import java.util.ArrayList;

public class CadastroEntregas {
    private ArrayList<Entrega> entregas;

    public CadastroEntregas() {
        this.entregas = new ArrayList<>();
    }
    
    public boolean cadastraEntrega(Entrega entrega) {
        for (Entrega existente : entregas) {
            if (entrega.getCodigo() == existente.getCodigo()) {
                return false;
            }
        }
        return entregas.add(entrega);
    }
    

    public Entrega pesquisaEntrega(int codigo){
        for (Entrega entrega : entregas) {
            if (entrega.getCodigo() == codigo)
                return entrega;
        }
        return null;
    }

    public ArrayList<Entrega> pesquisaEntregas(String email){
        ArrayList<Entrega> entregasClient = new ArrayList<>();
        for (Entrega entrega : entregas){
            if (entrega.getCliente().getEmail().equalsIgnoreCase(email)){
                entregasClient.add(entrega);
            }
        }
        return entregasClient;

    }

    public int numEntregas(){
        return entregas.size();
    }

    public Entrega entregaMaiorValor() {
        if (entregas.isEmpty()) {
            return null;
        }
        Entrega entregaMaiorValor = entregas.get(0);
        for (int i = 1; i < entregas.size(); i++) {
            if (entregas.get(i).getValor() > entregaMaiorValor.getValor()) {
                entregaMaiorValor = entregas.get(i);
            }
        }
        return entregaMaiorValor;
    }

    public double somatorioValorEntregas(Cliente cliente){
        double somatorioEntregas = 0;
        ArrayList<Entrega> ArraySomatorio = pesquisaEntregas(cliente.getEmail());
        for (Entrega entrega : ArraySomatorio){
            somatorioEntregas += entrega.getValor();
        }
        return somatorioEntregas;
    }   
}
