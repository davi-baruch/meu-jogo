package meujogo;

import br.edu.ifpb.jogos.base.Cor;
import br.edu.ifpb.jogos.base.Tela;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Ninja implements Inimigo {
    private static final double TAMANHO = 40;
    private static final double VELOCIDADE = 110;
    private static final double INTERVALO_ATAQUE = 1.4;
    private static final double LIMITE_INFERIOR_Y = 110;
    private static final double LIMITE_SUPERIOR_Y = 560;

    private final String nome;
    private final Random aleatorio;
    private final List<Shuriken> shurikens = new ArrayList<>();
    private final int pontos = 10;
    private double x;
    private double y;
    private double direcaoX;
    private double direcaoY;
    private double tempoNaDirecao;
    private double tempoParaAtacar = 0.8;
    private int vida = 100;

    public Ninja(String nome, double x, double y) {
        this(nome, x, y, new Random());
    }

    Ninja(String nome, double x, double y, Random aleatorio) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.aleatorio = aleatorio;
        mudarDirecao();
    }

    @Override
    public void atualizar(double segundos, Jogador alvo) {
        if (vida == 0) {
            shurikens.clear();
            return;
        }

        mover(segundos);
        tempoParaAtacar -= segundos;
        if (tempoParaAtacar <= 0) {
            usarPoder(alvo);
            tempoParaAtacar += INTERVALO_ATAQUE;
        }

        Iterator<Shuriken> iterator = shurikens.iterator();
        while (iterator.hasNext()) {
            Shuriken shuriken = iterator.next();
            shuriken.mover(segundos);
            if (shuriken.colidiu(alvo)) {
                alvo.receberDano(5);
                iterator.remove();
            } else if (shuriken.saiuDaTela()) {
                iterator.remove();
            }
        }
    }

    @Override
    public void mover(double segundos) {
        double restante = Math.max(0, segundos);
        while (restante > 0) {
            if (tempoNaDirecao <= 0) {
                mudarDirecao();
            }

            double passo = Math.min(restante, tempoNaDirecao);
            x += direcaoX * VELOCIDADE * passo;
            y += direcaoY * VELOCIDADE * passo;
            x = Math.max(0, Math.min(Tela.LARGURA - TAMANHO, x));
            y = Math.max(LIMITE_INFERIOR_Y,
                    Math.min(LIMITE_SUPERIOR_Y, y));
            restante -= passo;
            tempoNaDirecao -= passo;

            if ((x == 0 && direcaoX < 0)
                    || (x == Tela.LARGURA - TAMANHO && direcaoX > 0)
                    || (y == LIMITE_INFERIOR_Y && direcaoY < 0)
                    || (y == LIMITE_SUPERIOR_Y && direcaoY > 0)) {
                tempoNaDirecao = 0;
            }
        }
    }

    private void mudarDirecao() {
        double angulo = aleatorio.nextDouble() * Math.PI * 2;
        direcaoX = Math.cos(angulo);
        direcaoY = Math.sin(angulo);
        tempoNaDirecao = 0.5 + aleatorio.nextDouble();
    }

    @Override
    public void receberDano(int quantidade) {
        vida = Math.max(0, vida - Math.max(0, quantidade));
        if (vida == 0) {
            shurikens.clear();
        }
    }

    @Override
    public void usarPoder(Jogador alvo) {
        if (vida == 0) {
            return;
        }
        shurikens.add(new Shuriken(x + TAMANHO / 2, y + TAMANHO / 2,
                alvo.getX() + alvo.getTamanho() / 2,
                alvo.getY() + alvo.getTamanho() / 2));
    }

    @Override
    public void desenhar(Tela tela) {
        if (vida == 0) {
            return;
        }
        tela.retangulo(x, y, TAMANHO, TAMANHO, Cor.VERMELHO);
        for (Shuriken shuriken : shurikens) {
            shuriken.desenhar(tela);
        }
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getTamanho() {
        return TAMANHO;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public int getPontos() {
        return pontos;
    }
}
