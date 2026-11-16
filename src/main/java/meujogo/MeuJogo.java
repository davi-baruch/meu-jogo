package meujogo;

import br.edu.ifpb.jogos.base.Cor;
import br.edu.ifpb.jogos.base.Entrada;
import br.edu.ifpb.jogos.base.Jogo;
import br.edu.ifpb.jogos.base.Tela;

/** Ponto de partida, não uma arquitetura obrigatória para todos os jogos. */
public class MeuJogo implements Jogo {
    private Jogador jogador1 = new Jogador("Jogador 1", 260, 340);
    private Jogador jogador2 = new Jogador("Jogador 2", 660, 340);
    private BolaDeFogo poderJogador1 = new BolaDeFogo();
    private BolaDeFogo poderJogador2 = new BolaDeFogo();

    @Override
    public String getTitulo() {
        return "Space Wars";
    }

    @Override
    public void atualizar(double segundos, Entrada entrada) {
        if (entrada.reiniciou()) {
            reiniciar();
            return;
        }

        int horizontal1 = 0;
        int vertical1 = 0;
        if (entrada.jogador1Esquerda()) { horizontal1--; }
        if (entrada.jogador1Direita()) { horizontal1++; }
        if (entrada.jogador1Baixo()) { vertical1--; }
        if (entrada.jogador1Cima()) { vertical1++; }
        jogador1.mover(horizontal1, vertical1, segundos);

        int horizontal2 = 0;
        int vertical2 = 0;
        if (entrada.jogador2Esquerda()) { horizontal2--; }
        if (entrada.jogador2Direita()) { horizontal2++; }
        if (entrada.jogador2Baixo()) { vertical2--; }
        if (entrada.jogador2Cima()) { vertical2++; }
        jogador2.mover(horizontal2, vertical2, segundos);

        if (entrada.confirmou()) {
            jogador1.usarPoder(poderJogador1, jogador2);
        }
        if (entrada.jogador2Confirmou()) {
            jogador2.usarPoder(poderJogador2, jogador1);
        }
    }

    @Override
    public void desenhar(Tela tela) {
        tela.retangulo(0, 0, Tela.LARGURA, Tela.ALTURA, Cor.FUNDO);
        tela.texto("MEU JOGO", 40, 670, 2, Cor.BRANCO);
        tela.texto("J1 VIDA: " + jogador1.getVida() + "  BOLAS: " + poderJogador1.getCarga(),
                40, 625, 1, Cor.CIANO);
        tela.texto("J2 VIDA: " + jogador2.getVida() + "  BOLAS: " + poderJogador2.getCarga(),
                500, 625, 1, Cor.LARANJA);
        tela.retangulo(jogador1.getX(), jogador1.getY(),
                jogador1.getTamanho(), jogador1.getTamanho(), Cor.CIANO);
        tela.retangulo(jogador2.getX(), jogador2.getY(),
                jogador2.getTamanho(), jogador2.getTamanho(), Cor.LARANJA);
        tela.texto("J1: SETAS mover / ENTER atacar     J2: WASD mover / ESPACO atacar",
                40, 75, 1, Cor.BRANCO);
        tela.texto("F11: tela cheia     ESC: sair     Pressione R para reiniciar", 40, 40, 1, Cor.CINZA);
    }

    private void reiniciar() {
        jogador1 = new Jogador("Jogador 1", 260, 340);
        jogador2 = new Jogador("Jogador 2", 660, 340);
        poderJogador1 = new BolaDeFogo();
        poderJogador2 = new BolaDeFogo();
    }
}
