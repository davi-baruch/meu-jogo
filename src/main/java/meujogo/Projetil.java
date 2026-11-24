package meujogo;

import java.util.Collections;
import java.util.List;

import br.edu.ifpb.jogos.base.Cor;
import br.edu.ifpb.jogos.base.Tela;

public class Projetil {
    private static final double VELOCIDADE = 420;
    private static final double RAIO = 9;

    private final Poder poder;
    private final double direcaoX;
    private final double direcaoY;
    private double x;
    private double y;

    public Projetil(double x, double y, Alvo alvo, Poder poder) {
        this.x = x;
        this.y = y;
        this.poder = poder;

        double centroAlvoX = alvo.getX() + alvo.getTamanho() / 2;
        double centroAlvoY = alvo.getY() + alvo.getTamanho() / 2;
        double distancia = Math.hypot(centroAlvoX - x, centroAlvoY - y);
        if (distancia == 0) {
            direcaoX = 1;
            direcaoY = 0;
        } else {
            direcaoX = (centroAlvoX - x) / distancia;
            direcaoY = (centroAlvoY - y) / distancia;
        }
    }

    public boolean atualizar(double segundos, Alvo alvo) {
        return atualizar(segundos, alvo, Collections.<Obstaculo>emptyList());
    }

    public boolean atualizar(double segundos, Alvo alvo,
                             List<? extends Obstaculo> obstaculos) {
        double tempo = Math.max(0, segundos);
        double proximoX = x + direcaoX * VELOCIDADE * tempo;
        double proximoY = y + direcaoY * VELOCIDADE * tempo;

        for (Obstaculo obstaculo : obstaculos) {
            if (Colisao.segmentoAtingeObstaculo(
                    x, y, proximoX, proximoY, RAIO, obstaculo)) {
                return true;
            }
        }

        x = proximoX;
        y = proximoY;

        if (colidiu(alvo)) {
            alvo.receberDano(poder.getDano());
            return true;
        }
        return x + RAIO < 0 || x - RAIO > Tela.LARGURA
                || y + RAIO < 0 || y - RAIO > Tela.ALTURA;
    }

    private boolean colidiu(Alvo alvo) {
        return x + RAIO >= alvo.getX()
                && x - RAIO <= alvo.getX() + alvo.getTamanho()
                && y + RAIO >= alvo.getY()
                && y - RAIO <= alvo.getY() + alvo.getTamanho();
    }

    

    public void desenhar(Tela tela) {
        tela.circulo(x, y, RAIO, Cor.LARANJA);
    }
}
