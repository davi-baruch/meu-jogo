package meujogo;

public interface Obstaculo {
    double getX();
    double getY();
    double getAltura();
    double getLargura();
    boolean colideCom(double x, double y, double altura, double largura);
}
