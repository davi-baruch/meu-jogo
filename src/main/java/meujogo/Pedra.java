package meujogo;

public class Pedra implements Obstaculo{
    private double x;
    private double y;
    private double altura;
    private double largura;

    public Pedra(double x, double y, double largura, double altura){
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.largura = largura;
    }

    @Override 
    public double getY(){
        return y;
    }

    @Override
    public double getX(){
        return x;
    }

    @Override
    public double getAltura(){
        return altura;
    }

    @Override 
    public double getLargura(){
        return largura;
    }

    @Override
    public boolean colideCom(double alvoX, double alvoY, double alvoAltura, double alvoLargura){
        return alvoX < x + largura
            && alvoX + alvoLargura > x
            && alvoY < y + altura
            && alvoY + alvoAltura > y;
    }
}
