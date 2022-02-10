package Menu;

public class Extratos extends Menu
{
    public static void render()
    {
        System.out.println("EXTRATOS\n");

        System.out.println("Insira o número de sua conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("De qual conta deseja visualizar o extrato? ");
        System.out.println("[1] Conta corrente");
        System.out.println("[2] Conta poupanca");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        try {
            bancoController.exibirExtratos(numeroConta, tipoConta, senha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.render();
    }
}
