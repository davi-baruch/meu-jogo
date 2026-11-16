package meujogo;

/** Estado e regra de movimento do exemplo inicial; não depende de janela ou teclado. */
public class Jogador {
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

    public double getX() { return x; }

    public double getY() { return y; }

    public int getTamanho() { return 40; }

    public int getVida() { return vida; }

    public String getNome() { return nome; }

    public void receberDano(int qnt) {
        vida -= qnt;

        if (vida < 0) { vida = 0; }
    }

    public void receberCura(int qnt) {
        vida += qnt;

        if (vida > 100) { vida = 100; }
    }

    public void usarPoder(Poder poder, Jogador alvo){
        poder.lancarPoder(alvo);
        System.out.printf("O jogador %s usou [%s] em %s", this.nome, poder.getNome(), alvo.getNome());
    }

    public void mover(int horizontal, int vertical, double segundos) {
        x += horizontal * 260 * segundos;
        y += vertical * 260 * segundos;
        x = Math.max(0, Math.min(960 - getTamanho(), x));
        y = Math.max(110, Math.min(600 - getTamanho(), y));
    }
}
