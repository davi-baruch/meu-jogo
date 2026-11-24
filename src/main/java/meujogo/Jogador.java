package meujogo;

import java.util.Collections;
import java.util.List;

/** Estado e regra de movimento do exemplo inicial; não depende de janela ou teclado. */
public class Jogador implements Alvo {
    private double x;
    private double y;
    private int vida = 100;
    private Poder poder;
    private String nome;

    public Jogador(String nome){
        this(nome, 460, 340);
    }

    public Jogador(String nome, double x, double y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() { return x; }

    @Override
    public double getY() { return y; }

    @Override
    public double getTamanho() { return 40; }

    @Override
    public int getVida() { return vida; }

    public String getNome() { return nome; }

    @Override
    public void receberDano(int qnt) {
        vida = Math.max(0, vida - Math.max(0, qnt));
    }

    public void receberCura(int qnt) {
        vida = Math.max(0, vida + Math.max(0, qnt));
    }

    public boolean usarPoder(Poder poder){
        return poder.prepararDisparo();
    }

    public void mover(int horizontal, int vertical, double segundos) {
        mover(horizontal, vertical, segundos, Collections.<Obstaculo>emptyList());
    }

    public void mover(int horizontal, int vertical, double segundos,
                      List<? extends Obstaculo> obstaculos) {
        double deslocamentoX = horizontal * 260 * segundos;
        double deslocamentoY = vertical * 260 * segundos;
        double passoMaximo = calcularPassoMaximo(obstaculos);
        int passos = Math.max(1, (int) Math.ceil(
                Math.max(Math.abs(deslocamentoX), Math.abs(deslocamentoY)) / passoMaximo));
        double passoX = deslocamentoX / passos;
        double passoY = deslocamentoY / passos;

        for (int i = 0; i < passos; i++) {
            double proximoX = Math.max(0, Math.min(960 - getTamanho(), x + passoX));
            double proximoY = Math.max(110, Math.min(600 - getTamanho(), y + passoY));

            for (Obstaculo obstaculo : obstaculos) {
                if (obstaculo.colideCom(
                        proximoX, proximoY, getTamanho(), getTamanho())) {
                    return;
                }
            }

            x = proximoX;
            y = proximoY;
        }
    }

    private double calcularPassoMaximo(List<? extends Obstaculo> obstaculos) {
        double passoMaximo = 5;
        for (Obstaculo obstaculo : obstaculos) {
            double menorDimensao = Math.min(
                    obstaculo.getLargura(), obstaculo.getAltura());
            if (menorDimensao > 0) {
                passoMaximo = Math.min(passoMaximo, menorDimensao / 2);
            }
        }
        return passoMaximo;
    }
}
