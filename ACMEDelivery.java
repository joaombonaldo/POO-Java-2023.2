import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class ACMEDelivery {

    private Scanner entrada = null;                 // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private Clientela clientela;
    private CadastroEntregas cadastroEntregas;

    public ACMEDelivery() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivoentrada.txt"));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File("saida.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);

        // Implemente aqui o seu codigo adicional do construtor
        clientela = new Clientela();
        cadastroEntregas = new CadastroEntregas();

    }

    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }

    
    public void executa(){
        cadastraClientes();
        cadastraEntregas();
        quantidadeClienteCadastrados();
        quantEntregas();
        infoCLiente();
        infoEntrega();
        entregasCliente();
        entregaMaiorValor();
        mostrarEnderecoEntrega();
        somatorioValorDeEntregas();
        restauraES();
        menuCiclico();
    }

    private void cadastraClientes(){
        String email = entrada.nextLine();
        String nome = "";
        String endereco = "";

        while (!email.equals("-1")){
            nome = entrada.nextLine();
            endereco = entrada.nextLine();

           Cliente cliente = new Cliente(email, nome, endereco);
           if (clientela.cadastraCliente(cliente)){
            System.out.println("1;" + cliente.getEmail() + ";" + cliente.getNome() + ";" + cliente.getEndereco());
           }

            email = entrada.nextLine();
        }
    }

    private void cadastraEntregas(){
        int codigo = entrada.nextInt();
        double valor;
        String descricao;
        String email;

        while (codigo != -1){
            valor = entrada.nextDouble();
            entrada.nextLine();
            descricao = entrada.nextLine();
            email = entrada.nextLine();
            Cliente aux = clientela.pesquisaCliente(email);

            Entrega entrega = new Entrega(codigo, valor, descricao, aux);
            if (cadastroEntregas.cadastraEntrega(entrega)){
                System.out.println("2;" + entrega.getCodigo() + ";" + entrega.getValor() + ";" + entrega.getDescricao() + ";" + entrega.getCliente().getEmail());
            } else {
                System.out.println("Não foi possível cadastrar");
            }

            codigo = entrada.nextInt();
            entrada.nextLine();
        }
    }

    private void quantidadeClienteCadastrados(){
        System.out.println("3;" + clientela.quantClienteCadastrados());
    }

    private void quantEntregas(){
        System.out.println("4;" + cadastroEntregas.numEntregas());
    }

    private void infoCLiente(){
        String email = entrada.nextLine();
        Cliente aux = clientela.pesquisaCliente(email);
        if (!aux.equals(null)){
            System.out.println("5;" + aux.getEmail() + ";" + aux.getNome() + ";" + aux.getEndereco());
        } else {
            System.out.println("5;Cliente inexistente");
        }
    }

    private void infoEntrega(){
        int codigo = entrada.nextInt();
        entrada.nextLine();
        Entrega aux = cadastroEntregas.pesquisaEntrega(codigo);
        if (!aux.equals(null)){
            System.out.println("6;" + aux.getCodigo() + ";" + aux.getValor() + ";" + aux.getDescricao() + ";" + aux.getCliente().getEmail() + ";" + aux.getCliente().getNome() + ";" + aux.getCliente().getEndereco());
        } else {
            System.out.println("6;Entrega inexistente");
        }
    }

    private void entregasCliente(){
        String email = entrada.nextLine();
        ArrayList<Entrega> entregasDoCliente = cadastroEntregas.pesquisaEntregas(email);
        if (entregasDoCliente != null && !entregasDoCliente.isEmpty()){
            for (Entrega entrega : entregasDoCliente){
                System.out.println("7;" + entrega.getCliente().getEmail() + ";" + entrega.getCodigo() + ";" + entrega.getValor() + ";" + entrega.getDescricao());
            }
        } else {
            System.out.println("7;Cliente inexistente");
        }
    }

    private void entregaMaiorValor(){
        Entrega aux = cadastroEntregas.entregaMaiorValor();
        if (!aux.equals(null)){
            System.out.println("8;" + aux.getCodigo() + ";" + aux.getValor() + ";" + aux.getDescricao());
        } else {
            System.out.println("8;Entrega inexistente");
        }
    }

    private void mostrarEnderecoEntrega(){
        int codigo = entrada.nextInt();
        entrada.nextLine();
        Entrega aux = cadastroEntregas.pesquisaEntrega(codigo);
        if (!aux.equals(null)){
            System.out.println("9;" + aux.getCodigo() + ";" + aux.getValor() + ";" + aux.getDescricao() + ";" + aux.getCliente().getEndereco());
        } else {
            System.out.println("9;Entrega inexistente");
        }
    }

    private void somatorioValorDeEntregas(){
        String email = entrada.nextLine();
        Cliente aux = clientela.pesquisaCliente(email);
        double somatorio = Math.ceil(cadastroEntregas.somatorioValorEntregas(aux) * 100.0) / 100.0;    ;
        if (aux.equals(null)){
            System.out.println("10;Cliente inexistente");
        } else if (somatorio == 0){
            System.out.println("10;Cliente inexistente");
        } else {
            System.out.printf("10;%s;%s;%.2f%n",aux.getEmail(), aux.getNome(), somatorio);
        }
    }

    // PONTO EXTRA

    private void menuCiclico(){
        int opcao = 0;
        do {
            System.out.println("\n\n=============== MENU ===============\n\n");
            System.out.println("1 - Cadastrar cliente e Entrega");
            System.out.println("2 - Mostrar Clientes e Entregas Correspondentes");
            System.out.println("3 - Encerrar programa");
            System.out.println("\n\n====================================\n\n");
            System.out.print("Digite a opção desejada: ");
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao){
                case 1: 
                    cadastraClienteEntregaManual();
                break;

                case 2:
                    mostraCLientesEntregas();
                break;
                
                case 3:
                    System.out.println("Programa encerrado");
                break;
            }
        } while (opcao != 3);

    }

    private void cadastraClienteEntregaManual(){
        
        //Cadastra cliente
        System.out.println("\n\n=============== CADASTRA CLIENTE ===============\n\n");
        System.out.println("Digite o email do cliente: ");
        String email = entrada.nextLine();
        System.out.println("Digite o nome do cliente: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        String endereco = entrada.nextLine();

        Cliente cliente = new Cliente(email, nome, endereco);
           
        if (clientela.cadastraCliente(cliente)){
            System.out.println("\nCliente cadastrado:\n Email -> " + cliente.getEmail() + " || Nome -> " + cliente.getNome() + " || Endereço -> " + cliente.getEndereco());
        } else {
            System.out.println("Não foi possível cadastrar o cliente");
        }

           // Cadastra entrega
           System.out.println("\n\n=============== CADASTRA ENTREGA ===============\n\n");
        System.out.println("Digite o código da entrega: ");
        int codigo = entrada.nextInt();
        System.out.println("Digite o valor da entrega: ");
        double valor = entrada.nextDouble();
        entrada.nextLine();
        System.out.println("Digite a descrição da entrega: ");
        String descricao = entrada.nextLine();

        Entrega entrega = new Entrega(codigo, valor, descricao, cliente);
        if (cadastroEntregas.cadastraEntrega(entrega)){
            System.out.println("\nEntrega cadastrada:\n Código -> " + entrega.getCodigo() + " || Valor -> " + entrega.getValor() + " || Descrição -> " + entrega.getDescricao() + " || Email do Cliente -> " + entrega.getCliente().getEmail());
        } else {
            System.out.println("Não foi possível cadastrar");
        }  
    }

    private void mostraCLientesEntregas(){
        System.out.println("\n\n=============== MOSTRA CLIENTES E ENTREGAS ===============");

        ArrayList<Cliente> clientes = clientela.getClientes();
        int i = 1;
        for (Cliente cliente : clientes){
            System.out.printf("\n\n\nCliente %d: ", i);
            System.out.println("\n Email -> " + cliente.getEmail() + " || Nome -> " + cliente.getNome() + " || Endereço -> " + cliente.getEndereco());
            ArrayList<Entrega> entregasDoCliente = cadastroEntregas.pesquisaEntregas(cliente.getEmail());
            if (entregasDoCliente != null && !entregasDoCliente.isEmpty()){
                System.out.println("\nEntregas do cliente: ");
                for (Entrega entrega : entregasDoCliente){
                    System.out.println(" Código -> " + entrega.getCodigo() + " || Valor -> " + entrega.getValor() + " || Descrição -> " + entrega.getDescricao());
                }
            } else {
                System.out.println("Cliente inexistente");
            }
            i++;
        }
    }
}


