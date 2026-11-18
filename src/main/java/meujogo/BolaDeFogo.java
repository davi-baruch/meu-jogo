package meujogo;

public class BolaDeFogo implements Poder {
    private int carga = 10;

    @Override
    public boolean prepararDisparo() {
        if (carga <= 0) {
            return false;
        }
        carga--;
        return true;
    }

    @Override
    public int getDano() {
        return 15;
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
