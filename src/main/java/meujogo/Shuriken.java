package meujogo;

import java.util.Collections;
import java.util.List;

import br.edu.ifpb.jogos.base.Cor;
import br.edu.ifpb.jogos.base.Tela;

public class Shuriken {
    private static final double VELOCIDADE = 300;
    private static final double RAIO_COLISAO = 7;
    private double x;
    private double y;
    private final double direcaoX;
    private final double direcaoY;

    public Shuriken(double x, double y, double alvoX, double alvoY) {
        this.x = x;
        this.y = y;
        double distancia = Math.hypot(alvoX - x, alvoY - y);
        if (distancia == 0) {
            direcaoX = 1;
            direcaoY = 0;
        } else {
            direcaoX = (alvoX - x) / distancia;
            direcaoY = (alvoY - y) / distancia;
        }
    }

    public void mover(double segundos) {
        mover(segundos, Collections.<Obstaculo>emptyList());
    }

    public boolean mover(double segundos, List<? extends Obstaculo> obstaculos) {
        double tempo = Math.max(0, segundos);
        double proximoX = x + direcaoX * VELOCIDADE * tempo;
        double proximoY = y + direcaoY * VELOCIDADE * tempo;

        for (Obstaculo obstaculo : obstaculos) {
            if (Colisao.segmentoAtingeObstaculo(
                    x, y, proximoX, proximoY, RAIO_COLISAO, obstaculo)) {
                return true;
            }
        }

        x = proximoX;
        y = proximoY;
        return false;
    }

    public boolean colidiu(Jogador jogador) {
        return x + RAIO_COLISAO >= jogador.getX()
                && x - RAIO_COLISAO <= jogador.getX() + jogador.getTamanho()
                && y + RAIO_COLISAO >= jogador.getY()
                && y - RAIO_COLISAO <= jogador.getY() + jogador.getTamanho();
    }

    public boolean saiuDaTela() {
        return x < 0 || x > Tela.LARGURA || y < 0 || y > Tela.ALTURA;
    }

    public void desenhar(Tela tela) {
        double ponta = 8;
        double largura = 4;
        tela.triangulo(x, y + ponta, x - largura, y, x + largura, y, Cor.LARANJA);
        tela.triangulo(x + ponta, y, x, y + largura, x, y - largura, Cor.LARANJA);
        tela.triangulo(x, y - ponta, x - largura, y, x + largura, y, Cor.LARANJA);
        tela.triangulo(x - ponta, y, x, y + largura, x, y - largura, Cor.LARANJA);
    }
}
