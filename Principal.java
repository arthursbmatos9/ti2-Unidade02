package exercicio02;

import java.util.Scanner;

public class Principal {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        DAO dao = new DAO();
        dao.conectar();
        Automovel automoveis = new Automovel(null, null, null, 0);

        while (opc != 5) {
            System.out.println("Menu de opcoes: 1- Listar 2- Inserir 3- Excluir, 4- Atualizar, 5- Sair");
            int opc = scanner.nextInt();

            if (opc == '1') {
                List<Automovel> automoveis = automovelDAO.getAll();
                System.out.println("Automoveis registrados: ");
                System.out.println(automoveis);

            } else if (opc == '2') {
                System.out.println("DIGITE MARCA: ");
                String marca = scanner.nextLine();

                System.out.println("DIGITE MODELO: ");
                String modelo = scanner.nextLine();

                System.out.println("DIGITE PLACA: ");
                String placa = scanner.nextLine();

                System.out.println("DIGITE ANO: ");
                int ano = scanner.nextInt();
                scanner.nextLine();

                Automovel automovel = new Automovel(marca, modelo, placa, ano);

                automovelDAO.insert(automovel);

            } else if (opc == '3') {
                System.out.println("Digite a placa do automovel que deseja excluir: ");
                int placaAExcluir = scanner.nextInt();
                scanner.nextLine();
                automovelDAO.delete(placaAExcluir);

            } else if (opc == '4') {
                System.out.println("Digite a placa do automovel que deseja atualizar informacoes: ");
                System.out.println("Obs: impossivel alterar marca, placa e ano");

                String placaReferencia = scanner.nextLine();
                scanner.nextLine();

                System.out.println("NOVO MODELO: ");
                String modeloAtualizado = scanner.nextLine();

                Automovel marcaAntiga = automovelDAO.getByMarca(placaReferencia);
                Automovel anoAntigo = automovelDAO.getByAno(placaReferencia);

                Automovel automovelAtt = new Automovel(marcaAntiga.getMarca(), modeloAtualizado, placaReferencia.getPlaca(), anoAntigo.getAno());

                automovelDAO.update(automovelAtt);

            } else
                System.out.println("OPCAO INVALIDA");

        }
    }
}

