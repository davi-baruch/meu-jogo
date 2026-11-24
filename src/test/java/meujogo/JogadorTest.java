package meujogo;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class JogadorTest {
    @Test
    void movimentoDependeDoTempoDecorrido() {
        Jogador jogador = new Jogador("Player1");
        jogador.mover(1, 0, 0.5);

        assertEquals(590, jogador.getX(), 0.001);
        assertEquals(340, jogador.getY(), 0.001);
    }

    @Test
    void dividirOIntervaloPreservaODeslocamento() {
        Jogador primeiro = new Jogador("Player1");
        Jogador segundo = new Jogador("Player2");
        primeiro.mover(1, 1, 0.4);
        segundo.mover(1, 1, 0.2);
        segundo.mover(1, 1, 0.2);

        assertEquals(primeiro.getX(), segundo.getX(), 0.001);
        assertEquals(primeiro.getY(), segundo.getY(), 0.001);
    }

    @Test
    void movimentoRespeitaAsDuasBordasHorizontais() {
        Jogador jogador = new Jogador("Player1");
        jogador.mover(1, 0, 10);
        assertEquals(920, jogador.getX(), 0.001);

        jogador.mover(-1, 0, 10);
        assertEquals(0, jogador.getX(), 0.001);
    }

    @Test
    void movimentoPreservaAsFaixasDeTexto() {
        Jogador jogador = new Jogador("Player1");
        jogador.mover(0, 1, 10);
        assertEquals(560, jogador.getY(), 0.001);

        jogador.mover(0, -1, 10);
        assertEquals(110, jogador.getY(), 0.001);
    }

    @Test
    void semDirecaoNaoHaDeslocamento() {
        Jogador jogador = new Jogador("Player1");
        jogador.mover(0, 0, 1);

        assertEquals(460, jogador.getX(), 0.001);
        assertEquals(340, jogador.getY(), 0.001);
    }

    @Test
    void jogadorParaAntesDeAtravessarUmaPedra() {
        Jogador jogador = new Jogador("Player1", 260, 340);
        Obstaculo pedra = new Pedra(350, 330, 50, 50);

        jogador.mover(1, 0, 1, Collections.singletonList(pedra));

        assertEquals(310, jogador.getX(), 0.001);
        assertFalse(pedra.colideCom(
                jogador.getX(), jogador.getY(),
                jogador.getTamanho(), jogador.getTamanho()));
    }

    @Test 
    void operacaoPermitida(){
        Jogador jogador1 = new Jogador("Player 1");
        Jogador jogador2 = new Jogador("Player 2");
        Poder poder1 = new BolaDeFogo();

        assertTrue(jogador1.usarPoder(poder1));

        assertEquals(9, poder1.getCarga());
        assertEquals(100, jogador2.getVida());
    }

    @Test
    void limiteOuRecusa(){
        Jogador jogador = new Jogador("Player 1");
        Poder poder1 = new BolaDeFogo();

        assertTrue(jogador.usarPoder(poder1));
        assertTrue(jogador.usarPoder(poder1));
        assertTrue(jogador.usarPoder(poder1));
        assertFalse(jogador.usarPoder(poder1));

        assertEquals(0, poder1.getCarga());
    }

    @Test
    void doisObjetosIndependentes(){
        Jogador jogador1 = new Jogador("Player 1");
        Jogador jogador2 = new Jogador("Player 2");

        Poder poder1 = new BolaDeFogo();
        jogador1.usarPoder(poder1);

        assertEquals(100, jogador2.getVida());
        assertEquals(100, jogador1.getVida());
    }
}
