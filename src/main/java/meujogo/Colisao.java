package meujogo;

final class Colisao {
    private Colisao() {
    }

    static boolean segmentoAtingeObstaculo(
            double xInicial, double yInicial, double xFinal, double yFinal,
            double raio, Obstaculo obstaculo) {
        double minimoX = obstaculo.getX() - raio;
        double maximoX = obstaculo.getX() + obstaculo.getLargura() + raio;
        double minimoY = obstaculo.getY() - raio;
        double maximoY = obstaculo.getY() + obstaculo.getAltura() + raio;
        double deltaX = xFinal - xInicial;
        double deltaY = yFinal - yInicial;
        double entrada = 0;
        double saida = 1;

        if (deltaX == 0) {
            if (xInicial < minimoX || xInicial > maximoX) {
                return false;
            }
        } else {
            double entradaX = (minimoX - xInicial) / deltaX;
            double saidaX = (maximoX - xInicial) / deltaX;
            entrada = Math.max(entrada, Math.min(entradaX, saidaX));
            saida = Math.min(saida, Math.max(entradaX, saidaX));
            if (entrada > saida) {
                return false;
            }
        }

        if (deltaY == 0) {
            return yInicial >= minimoY && yInicial <= maximoY;
        }

        double entradaY = (minimoY - yInicial) / deltaY;
        double saidaY = (maximoY - yInicial) / deltaY;
        entrada = Math.max(entrada, Math.min(entradaY, saidaY));
        saida = Math.min(saida, Math.max(entradaY, saidaY));
        return entrada <= saida;
    }
}
