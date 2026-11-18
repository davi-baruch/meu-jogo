package meujogo;

import br.edu.ifpb.jogos.base.Tela;

public interface Inimigo extends Alvo {
    void atualizar(double segundos, Jogador alvo);
    void mover(double segundos);
    void usarPoder(Jogador alvo);
    void desenhar(Tela tela);
    double getX();
    double getY();
    double getTamanho();
    String getNome();
    int getPontos();
}
