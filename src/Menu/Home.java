package Menu;

public class Home extends Menu
{
    public static void render()
    {
        System.out.println("Bem vindo ao Banco!\n");
        System.out.println("[1] Abrir nova conta");
        System.out.println("[2] Abrir conta salário");
        System.out.println("[3] Realizar depósito");
        System.out.println("[4] Realizar saque");
        System.out.println("[5] Realizar transferencia");
        System.out.println("[6] Visualizar extratos");

        System.out.println("\nMODO DESENVOLVEDOR");
        System.out.println("[7] Popular banco");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                AberturaConta.render();
                break;
            case 2:
                break;
            case 3:
                Deposito.render();
                break;
            case 4:
                Saque.render();
                break;
            case 5:
                Transferencia.render();
                break;
            case 6:
                Extratos.render();
                break;
        }

    }
}
