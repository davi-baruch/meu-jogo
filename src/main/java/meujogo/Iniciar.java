package meujogo;

/** Entrada já configurada. Para desenvolver seu jogo, comece por MeuJogo e Jogador. */
public class Iniciar {
    public static void main(String[] args) {
        // Janela.abrir(new MeuJogo());
        Jogador jogador1 = new Jogador("Jones");
        Jogador alvo1 = new Jogador("Judeu");
        Poder poder1 = new BolaDeFogo();

        jogador1.usarPoder(poder1, alvo1);

    
    }
}
