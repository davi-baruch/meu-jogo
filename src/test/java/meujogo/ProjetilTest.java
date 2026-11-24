package meujogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ProjetilTest {
    @Test
    void causaDanoSomenteQuandoColideComOAlvo() {
        AlvoFixo alvo = new AlvoFixo();
        BolaDeFogo poder = new BolaDeFogo();
        assertTrue(poder.prepararDisparo());
        Projetil projetil = new Projetil(0, 20, alvo, poder);

        assertFalse(projetil.atualizar(0.1, alvo));
        assertEquals(100, alvo.getVida());
        assertTrue(projetil.atualizar(0.2, alvo));
        assertEquals(80, alvo.getVida());
    }

    @Test
    void projetilEInterrompidoPelaPedraAntesDeAtingirOAlvo() {
        AlvoFixo alvo = new AlvoFixo();
        BolaDeFogo poder = new BolaDeFogo();
        assertTrue(poder.prepararDisparo());
        Projetil projetil = new Projetil(0, 20, alvo, poder);
        Obstaculo pedra = new Pedra(40, 0, 20, 40);

        assertEquals(100, alvo.getVida());
    }

    private static class AlvoFixo implements Alvo {
        private int vida = 100;

        @Override public void receberDano(int quantidade) { vida -= quantidade; }
        @Override public int getVida() { return vida; }
        @Override public double getX() { return 100; }
        @Override public double getY() { return 0; }
        @Override public double getTamanho() { return 40; }
    }
}
