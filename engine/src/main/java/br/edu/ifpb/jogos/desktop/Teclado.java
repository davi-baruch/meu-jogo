package br.edu.ifpb.jogos.desktop;

import br.edu.ifpb.jogos.base.Entrada;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

class Teclado implements Entrada {
    @Override
    public boolean esquerda() {
        return mantida(Keys.LEFT) || mantida(Keys.A);
    }

    @Override
    public boolean direita() {
        return mantida(Keys.RIGHT) || mantida(Keys.D);
    }

    @Override
    public boolean cima() {
        return mantida(Keys.UP) || mantida(Keys.W);
    }

    @Override
    public boolean baixo() {
        return mantida(Keys.DOWN) || mantida(Keys.S);
    }

    @Override
    public boolean acao() {
        return mantida(Keys.SPACE);
    }

    @Override
    public boolean confirmou() {
        return Gdx.input.isKeyJustPressed(Keys.ENTER);
    }

    @Override
    public boolean pausou() {
        return Gdx.input.isKeyJustPressed(Keys.P);
    }

    @Override
    public boolean reiniciou() {
        return Gdx.input.isKeyJustPressed(Keys.R);
    }

    @Override
    public boolean jogador1Esquerda() {
        return mantida(Keys.LEFT);
    }

    @Override
    public boolean jogador1Direita() {
        return mantida(Keys.RIGHT);
    }

    @Override
    public boolean jogador1Cima() {
        return mantida(Keys.UP);
    }

    @Override
    public boolean jogador1Baixo() {
        return mantida(Keys.DOWN);
    }

    @Override
    public boolean jogador2Esquerda() {
        return mantida(Keys.A);
    }

    @Override
    public boolean jogador2Direita() {
        return mantida(Keys.D);
    }

    @Override
    public boolean jogador2Cima() {
        return mantida(Keys.W);
    }

    @Override
    public boolean jogador2Baixo() {
        return mantida(Keys.S);
    }

    @Override
    public boolean jogador2Confirmou() {
        return Gdx.input.isKeyJustPressed(Keys.SPACE);
    }

    private boolean mantida(int tecla) {
        return Gdx.input.isKeyPressed(tecla);
    }
}
