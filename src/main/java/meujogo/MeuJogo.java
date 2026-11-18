package meujogo;

import br.edu.ifpb.jogos.base.Cor;
import br.edu.ifpb.jogos.base.Entrada;
import br.edu.ifpb.jogos.base.Jogo;
import br.edu.ifpb.jogos.base.Tela;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Ponto de partida, não uma arquitetura obrigatória para todos os jogos. */
public class MeuJogo implements Jogo {
    private Jogador jogador1 = new Jogador("Jogador 1", 260, 340);
    private BolaDeFogo poderJogador1 = new BolaDeFogo();
    private Ninja ninja = new Ninja("Ninja", 600, 340);
    private final List<Projetil> projeteis = new ArrayList<>();
    private static final double ALCANCE_BOLA_DE_FOGO = 350;

    @Override
    public String getTitulo() {
        return "Meu jogo - Ninja";
    }

    @Override
    public void atualizar(double segundos, Entrada entrada) {
        if (entrada.reiniciou()) {
            reiniciar();
            return;
        }
        if (jogador1.getVida() == 0 || ninja.getVida() == 0) {
            return;
        }

        int horizontal = 0;
        int vertical = 0;
        if (entrada.jogador1Esquerda()) { horizontal--; }
        if (entrada.jogador1Direita()) { horizontal++; }
        if (entrada.jogador1Baixo()) { vertical--; }
        if (entrada.jogador1Cima()) { vertical++; }
        jogador1.mover(horizontal, vertical, segundos);
        ninja.atualizar(segundos, jogador1);
        atualizarProjeteis(segundos);
        if (ninja.getVida() == 0) {
            projeteis.clear();
        }
        if (entrada.confirmou()) {
            double distancia = Math.hypot(
                    jogador1.getX() + jogador1.getTamanho() / 2
                            - ninja.getX() - ninja.getTamanho() / 2,
                    jogador1.getY() + jogador1.getTamanho() / 2
                            - ninja.getY() - ninja.getTamanho() / 2);
            if (distancia <= ALCANCE_BOLA_DE_FOGO && ninja.getVida() > 0) {
                if (jogador1.usarPoder(poderJogador1)) {
                    projeteis.add(new Projetil(
                            jogador1.getX() + jogador1.getTamanho() / 2,
                            jogador1.getY() + jogador1.getTamanho() / 2,
                            ninja, poderJogador1));
                }
            }
        }
    }

    private void atualizarProjeteis(double segundos) {
        Iterator<Projetil> iterator = projeteis.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().atualizar(segundos, ninja)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void desenhar(Tela tela) {
        tela.retangulo(0, 0, Tela.LARGURA, Tela.ALTURA, Cor.FUNDO);
        tela.texto("NINJA", 40, 670, 2, Cor.BRANCO);
        tela.texto("JOGADOR: " + jogador1.getVida() + "  BOLAS: " + poderJogador1.getCarga(),
                40, 625, 1, Cor.CIANO);
        tela.texto("NINJA: " + ninja.getVida(), 650, 625, 1, Cor.VERMELHO);
        tela.retangulo(jogador1.getX(), jogador1.getY(),
                jogador1.getTamanho(), jogador1.getTamanho(), Cor.CIANO);
        for (Projetil projetil : projeteis) {
            projetil.desenhar(tela);
        }
        ninja.desenhar(tela);
        if (ninja.getVida() == 0) {
            tela.texto("VITORIA! O ninja foi derrotado.", 330, 380, 1, Cor.BRANCO);
        } else if (jogador1.getVida() == 0) {
            tela.texto("FIM DE JOGO", 400, 380, 1, Cor.BRANCO);
        }
        tela.texto("Setas mover / ENTER atacar (alcance limitado)",
                40, 75, 1, Cor.BRANCO);
        tela.texto("F11: tela cheia     ESC: sair     Pressione R para reiniciar", 40, 40, 1, Cor.CINZA);
    }

    private void reiniciar() {
        jogador1 = new Jogador("Jogador 1", 260, 340);
        poderJogador1 = new BolaDeFogo();
        ninja = new Ninja("Ninja", 600, 340);
        projeteis.clear();
    }
}
