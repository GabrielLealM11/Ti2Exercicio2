package app;

import java.util.List;
import java.util.Scanner;

import dao.InscricaoDAO;
import model.Inscricao;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        InscricaoDAO InscricaoDAO = new InscricaoDAO();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n\n==== Menu ====");
            System.out.println("1) Inserir");
            System.out.println("2) Listar");
            System.out.println("3) Atualizar");
            System.out.println("4) Excluir");
            System.out.println("5) Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n==== Inserir usuário ====");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Posicao: ");
                    String posicao = scanner.nextLine();
                    System.out.print("Time: ");
                    String time = scanner.nextLine();

                    Inscricao novaInscricao = new Inscricao(nome, idade, posicao, time);
                    if (InscricaoDAO.insert(novaInscricao)) {
                        System.out.println("Inserção com sucesso -> " + novaInscricao.toString());
                    }
                    break;

                case 2:
                    System.out.println("\n==== Listar usuários ====");
                    List<Inscricao> inscricoes = InscricaoDAO.getAll();
                    for (Inscricao m : inscricoes) {
                        System.out.println(m.toString());
                    }
                    break;

                case 3:
                    System.out.println("\n==== Atualizar usuário ====");
                    System.out.print("Digite o código do usuário a ser atualizado: ");
                    String codigoAtualizar = scanner.nextLine();
                    Inscricao inscricaoAtualizar = InscricaoDAO.get(codigoAtualizar);
                    

                    if (inscricaoAtualizar != null) {
                        System.out.print("Novo posicao: ");
                        String novoPosicao = scanner.nextLine();
                        inscricaoAtualizar.setposicao(novoPosicao);

                        InscricaoDAO.update(inscricaoAtualizar);
                        System.out.println("Atualização realizada com sucesso.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\n==== Excluir usuário ====");
                    System.out.print("Digite o código do usuário a ser excluído: ");
                    String codigoExcluir = scanner.nextLine();
                    Inscricao inscricaoExcluir = InscricaoDAO.get(codigoExcluir);

                    if (inscricaoExcluir != null) {
                        InscricaoDAO.delete(codigoExcluir);
                        System.out.println("Exclusão realizada com sucesso.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}
