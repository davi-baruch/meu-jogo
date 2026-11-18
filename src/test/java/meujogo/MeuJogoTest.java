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
    void disparoMostraOProjetilEConsomeUmaCargaAntesDoImpacto() {
        MeuJogo jogo = new MeuJogo();
        EntradaFake entrada = new EntradaFake();
        entrada.atacar = true;

        jogo.atualizar(0, entrada);

        TelaFake tela = desenhar(jogo);
        assertTrue(tela.textos.contains("JOGADOR: 100  BOLAS: 2"));
        assertTrue(tela.textos.contains("NINJA: 100"));
        assertEquals(1, tela.circulos);
    }

    @Test
    void naoConsomeBolaDeFogoQuandoONinjaEstaForaDoAlcance() {
        MeuJogo jogo = new MeuJogo();
        EntradaFake entrada = new EntradaFake();
        entrada.esquerda = true;
        jogo.atualizar(0.5, entrada);
        entrada = new EntradaFake();
        entrada.atacar = true;
        jogo.atualizar(0, entrada);

        TelaFake tela = desenhar(jogo);

        assertTrue(tela.textos.contains("JOGADOR: 100  BOLAS: 3"));
        assertTrue(tela.textos.contains("NINJA: 100"));
    }

    @Test
    void desenhaNinjaEAsShurikensComFormasGeometricas() {
        MeuJogo jogo = new MeuJogo();
        jogo.atualizar(0.8, new EntradaFake());

        TelaFake tela = desenhar(jogo);

        assertEquals(Cor.VERMELHO, tela.retangulos.get(2));
        assertTrue(tela.triangulos >= 4);
    }

    @Test
    void reiniciarRestauraJogadorPoderENinja() {
        MeuJogo jogo = new MeuJogo();
        EntradaFake entrada = new EntradaFake();
        entrada.atacar = true;
        jogo.atualizar(0, entrada);
        entrada = new EntradaFake();
        entrada.reiniciar = true;
        jogo.atualizar(0, entrada);

        TelaFake tela = desenhar(jogo);

        assertTrue(tela.textos.contains("JOGADOR: 100  BOLAS: 3"));
        assertTrue(tela.textos.contains("NINJA: 100"));
    }

    private TelaFake desenhar(MeuJogo jogo) {
        TelaFake tela = new TelaFake();
        jogo.desenhar(tela);
        return tela;
    }

    private static class EntradaFake implements Entrada {
        private boolean atacar;
        private boolean reiniciar;
        private boolean esquerda;

        @Override public boolean esquerda() { return esquerda; }
        @Override public boolean direita() { return false; }
        @Override public boolean cima() { return false; }
        @Override public boolean baixo() { return false; }
        @Override public boolean acao() { return false; }
        @Override public boolean confirmou() { return atacar; }
        @Override public boolean pausou() { return false; }
        @Override public boolean reiniciou() { return reiniciar; }
    }

    private static class TelaFake implements Tela {
        private final List<String> textos = new ArrayList<>();
        private final List<Cor> retangulos = new ArrayList<>();
        private int triangulos;
        private int circulos;

        @Override
        public void retangulo(double x, double y, double largura, double altura, Cor cor) {
            retangulos.add(cor);
        }

        @Override public void circulo(double x, double y, double raio, Cor cor) { circulos++; }
        @Override public void triangulo(double x1, double y1, double x2, double y2,
                                        double x3, double y3, Cor cor) { triangulos++; }
        @Override public void texto(String texto, double x, double y, double escala, Cor cor) {
            textos.add(texto);
        }
    }
}
