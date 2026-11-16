package meujogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void operacaoPermitida(){
        Jogador jogador1 = new Jogador("Player 1");
        Jogador jogador2 = new Jogador("Player 2");
        Poder poder1 = new BolaDeFogo();

        jogador1.usarPoder(poder1, jogador2);

        assertEquals(2, poder1.getCarga());
        assertEquals(85, jogador2.getVida());
    }

    @Test
    void limiteOuRecusa(){
        Jogador jogador = new Jogador("Player 1");
        Poder poder1 = new BolaDeFogo();

        jogador.usarPoder(poder1, jogador);
        jogador.usarPoder(poder1, jogador);
        jogador.usarPoder(poder1, jogador);

        assertEquals(0, poder1.getCarga());
    }

    @Test
    void doisObjetosIndependentes(){
        Jogador jogador1 = new Jogador("Player 1");
        Jogador jogador2 = new Jogador("Player 2");

        Poder poder1 = new BolaDeFogo();
        jogador1.usarPoder(poder1, jogador2);

        assertEquals(85, jogador2.getVida());
        assertEquals(100, jogador1.getVida());
    }
}
