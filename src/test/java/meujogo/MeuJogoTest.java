package meujogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.edu.ifpb.jogos.base.Cor;
import br.edu.ifpb.jogos.base.Entrada;
import br.edu.ifpb.jogos.base.Tela;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class MeuJogoTest {
    @Test
    void controlesMovemCadaJogadorSeparadamente() {
        MeuJogo jogo = new MeuJogo();
        EntradaFake entrada = new EntradaFake();
        entrada.jogador1Direita = true;
        entrada.jogador2Esquerda = true;

        jogo.atualizar(0.5, entrada);

        TelaFake tela = desenhar(jogo);
        assertEquals(390, tela.retangulos.get(1).x, 0.001);
        assertEquals(Cor.CIANO, tela.retangulos.get(1).cor);
        assertEquals(530, tela.retangulos.get(2).x, 0.001);
        assertEquals(Cor.LARANJA, tela.retangulos.get(2).cor);
    }

    @Test
    void cadaAtaqueAtingeOAdversarioEConsomeUmaCarga() {
        MeuJogo jogo = new MeuJogo();
        EntradaFake entrada = new EntradaFake();
        entrada.jogador1Atacou = true;
        jogo.atualizar(0, entrada);

        TelaFake tela = desenhar(jogo);
        assertEquals("J1 VIDA: 100  BOLAS: 2", tela.textos.get(1));
        assertTrue(tela.textos.contains("J2 VIDA: 85  BOLAS: 3"));

        entrada = new EntradaFake();
        entrada.jogador2Atacou = true;
        jogo.atualizar(0, entrada);

        tela = desenhar(jogo);
        assertTrue(tela.textos.contains("J1 VIDA: 85  BOLAS: 2"));
        assertTrue(tela.textos.contains("J2 VIDA: 85  BOLAS: 2"));
    }

    @Test
    void reinicioRestauraPosicoesVidaECargasDosDoisJogadores() {
        MeuJogo jogo = new MeuJogo();
        EntradaFake entrada = new EntradaFake();
        entrada.jogador1Direita = true;
        entrada.jogador2Esquerda = true;
        entrada.jogador1Atacou = true;
        entrada.jogador2Atacou = true;
        jogo.atualizar(0.5, entrada);

        entrada = new EntradaFake();
        entrada.reiniciar = true;
        jogo.atualizar(0, entrada);

        TelaFake tela = desenhar(jogo);
        assertTrue(tela.textos.contains("J1 VIDA: 100  BOLAS: 3"));
        assertTrue(tela.textos.contains("J2 VIDA: 100  BOLAS: 3"));
        assertEquals(260, tela.retangulos.get(1).x, 0.001);
        assertEquals(660, tela.retangulos.get(2).x, 0.001);
    }

    private TelaFake desenhar(MeuJogo jogo) {
        TelaFake tela = new TelaFake();
        jogo.desenhar(tela);
        return tela;
    }

    private static class EntradaFake implements Entrada {
        private boolean jogador1Direita;
        private boolean jogador2Esquerda;
        private boolean jogador1Atacou;
        private boolean jogador2Atacou;
        private boolean reiniciar;

        @Override public boolean esquerda() { return false; }
        @Override public boolean direita() { return false; }
        @Override public boolean cima() { return false; }
        @Override public boolean baixo() { return false; }
        @Override public boolean acao() { return false; }
        @Override public boolean confirmou() { return jogador1Atacou; }
        @Override public boolean pausou() { return false; }
        @Override public boolean reiniciou() { return reiniciar; }
        @Override public boolean jogador1Direita() { return jogador1Direita; }
        @Override public boolean jogador2Esquerda() { return jogador2Esquerda; }
        @Override public boolean jogador2Confirmou() { return jogador2Atacou; }
    }

    private static class TelaFake implements Tela {
        private final List<String> textos = new ArrayList<String>();
        private final List<Retangulo> retangulos = new ArrayList<Retangulo>();

        @Override
        public void retangulo(double x, double y, double largura, double altura, Cor cor) {
            retangulos.add(new Retangulo(x, cor));
        }

        @Override public void circulo(double x, double y, double raio, Cor cor) { }
        @Override public void triangulo(double x1, double y1, double x2, double y2,
                                        double x3, double y3, Cor cor) { }
        @Override public void texto(String texto, double x, double y, double escala, Cor cor) {
            textos.add(texto);
        }
    }

    private static class Retangulo {
        private final double x;
        private final Cor cor;

        private Retangulo(double x, Cor cor) {
            this.x = x;
            this.cor = cor;
        }
    }
}
