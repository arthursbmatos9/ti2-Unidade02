package exercicio02;
import java.util.Scanner;

public class Principal {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        DAO dao = new DAO();
		dao.conectar();
        Automovel automoveis = new Automovel(null, null, null, 0);

        System.out.println("Menu de opcoes: 1- Listar 2- Inserir 3- Excluir, 4- Atualizar, 5- Sair");
        int opc = sc.nextInt();
        if(opc == '1')
            dao.getAutomoveis();
        else if(opc == '2')
            dao.inserirAutomovel(automoveis);
        //else if(opc == '3')
            //dao.excluirAutomovel(automoveis);
        else if(opc == '4')
            dao.atualizarAutomovel(automoveis);
        else
            dao.close();
    }
}
