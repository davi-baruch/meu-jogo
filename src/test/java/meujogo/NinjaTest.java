package meujogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

class NinjaTest {
    @Test
    void iniciaComCemDeVidaEDanoNaoPodeReduzirVidaAbaixoDeZero() {
        Ninja ninja = new Ninja("Ninja", 400, 340);

        assertEquals(100, ninja.getVida());
        ninja.receberDano(120);
        assertEquals(0, ninja.getVida());
    }

    @Test
    void bolaDeFogoTemDanoEConsomeCargaAoDisparar() {
        Ninja ninja = new Ninja("Ninja", 400, 340);
        BolaDeFogo bola = new BolaDeFogo();

        assertTrue(bola.prepararDisparo());

        assertEquals(15, bola.getDano());
        assertEquals(100, ninja.getVida());
        assertEquals(2, bola.getCarga());
    }

    @Test
    void ninjaSeMoveAleatoriamenteDentroDaArena() {
        Ninja ninja = new Ninja("Ninja", 400, 340, new Random(1));

        ninja.mover(0.5);
        assertTrue(ninja.getX() != 400 || ninja.getY() != 340);

        ninja.mover(20);
        assertTrue(ninja.getX() >= 0 && ninja.getX() <= 920);
        assertTrue(ninja.getY() >= 110 && ninja.getY() <= 560);
    }

    @Test
    void shurikenCausaCincoDeDanoAoAtingirJogador() {
        Ninja ninja = new Ninja("Ninja", 260, 340, new Random(1));
        Jogador jogador = new Jogador("Jogador", 260, 340);
        ninja.usarPoder(jogador);

        ninja.atualizar(0, jogador);

        assertEquals(95, jogador.getVida());
    }
}
