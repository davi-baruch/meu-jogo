package br.edu.ifpb.jogos.base;

/** Teclas mantidas e ações de um único quadro; implementadas pela infraestrutura. */
public interface Entrada {
    boolean esquerda();

    boolean direita();

    boolean cima();

    boolean baixo();

    boolean acao();

    boolean confirmou();

    boolean pausou();

    boolean reiniciou();

    default boolean jogador1Esquerda() {
        return esquerda();
    }

    default boolean jogador1Direita() {
        return direita();
    }

    default boolean jogador1Cima() {
        return cima();
    }

    default boolean jogador1Baixo() {
        return baixo();
    }

    default boolean jogador2Esquerda() {
        return false;
    }

    default boolean jogador2Direita() {
        return false;
    }

    default boolean jogador2Cima() {
        return false;
    }

    default boolean jogador2Baixo() {
        return false;
    }

    default boolean jogador2Confirmou() {
        return false;
    }
}
