
import java.util.Scanner;
import java.util.Random;

public class MiniBatalla {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Puntos de vida iniciales
        int vidaJugador = 100;
        int vidaJefe = 100;

        System.out.println("=========================================");
        System.out.println("      ⚔️ ¡MINI BATALLA POR TURNOS! ⚔️     ");
        System.out.println("=========================================");
        System.out.println("Un Jefe salvaje ha aparecido. ¡Prepárate!");

        // El bucle sigue mientras ambos sigan vivos
        while (vidaJugador > 0 && vidaJefe > 0) {
            System.out.println("\n--- TU TURNO ---");
            System.out.println("Tu HP: " + vidaJugador + " | HP del Jefe: " + vidaJefe);
            System.out.println("1. Atacar con espada (hace 15-25 de daño)");
            System.out.println("2. Usar poción mágica (cura 15-25 de vida)");
            System.out.print("Elige tu acción (1 o 2): ");

            int accion = 0;
            
            // Verificamos que el jugador introduzca un número
            if (scanner.hasNextInt()) {
                accion = scanner.nextInt();
            } else {
                scanner.next(); // Limpia el error
            }

            // Genera un número aleatorio entre 15 y 25 para el poder de tu acción
            int poderAleatorio = random.nextInt(11) + 15; 

            if (accion == 1) {
                vidaJefe -= poderAleatorio;
                System.out.println("💥 ¡Atacaste al Jefe y le quitaste " + poderAleatorio + " puntos de vida!");
            } else if (accion == 2) {
                vidaJugador += poderAleatorio;
                if (vidaJugador > 100) vidaJugador = 100; // La vida máxima es 100
                System.out.println("🧪 ¡Te curaste " + poderAleatorio + " puntos de vida!");
            } else {
                System.out.println("⚠️ Opción no válida. Te tropezaste y perdiste tu turno.");
            }

            // Turno del Jefe (solo ataca si sigue vivo)
            if (vidaJefe > 0) {
                System.out.println("\n--- TURNO DEL JEFE ---");
                // El jefe hace un daño aleatorio entre 10 y 20
                int danoEnemigo = random.nextInt(11) + 10; 
                vidaJugador -= danoEnemigo;
                System.out.println("🔥 El Jefe te golpea y te quita " + danoEnemigo + " puntos de vida.");
            }
        }

        // Resultados finales
        System.out.println("\n=========================================");
        if (vidaJugador > 0) {
            System.out.println("🏆 ¡VICTORIA! Derrotaste al Jefe. Eres un héroe.");
        } else {
            System.out.println("💀 GAME OVER. El Jefe te ha derrotado.");
        }
        System.out.println("=========================================");
        
        scanner.close();
    }
}