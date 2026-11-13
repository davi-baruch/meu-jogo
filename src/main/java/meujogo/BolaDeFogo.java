package meujogo;

public class BolaDeFogo implements Poder {
    private int carga = 3;

    @Override
    public boolean lancarPoder(Jogador alvo) {
        if (carga <= 0) {
            return false;
        }
        
        carga--;
        alvo.receberDano(15);
        return true;
    }

    @Override
    public int getCarga() {
        return carga;
    }

    @Override
    public String getNome() {
        return "Bola de fogo";
    }
}
