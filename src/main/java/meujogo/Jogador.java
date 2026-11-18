package meujogo;

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

    public int getVida() { return vida; }

    public String getNome() { return nome; }

    @Override
    public void receberDano(int qnt) {
        vida = Math.max(0, vida - Math.max(0, qnt));
    }

    public void receberCura(int qnt) {
        vida += qnt;

        if (vida > 100) { vida = 100; }
    }

    public boolean usarPoder(Poder poder){
        return poder.prepararDisparo();
    }

    public void mover(int horizontal, int vertical, double segundos) {
        x += horizontal * 260 * segundos;
        y += vertical * 260 * segundos;
        x = Math.max(0, Math.min(960 - getTamanho(), x));
        y = Math.max(110, Math.min(600 - getTamanho(), y));
    }
}
