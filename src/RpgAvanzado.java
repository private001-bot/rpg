import java.util.Scanner;
import java.util.Random;

public class RpgAvanzado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Estadísticas del Jugador
        int vidaJugador = 100;
        int energia = 0;
        int pociones = 3;
        boolean defendiendo = false;

        // Estadísticas del Jefe
        int vidaJefe = 200;
        boolean jefeEnfurecido = false;

        System.out.println("==================================================");
        System.out.println(" 🌌 BATALLA ÉPICA: EL DESPERTAR DEL CABALLERO 🌌 ");
        System.out.println("==================================================");
        System.out.println("Un Caballero Corrupto se interpone en tu camino.");
        System.out.println("¡Gestiona bien tus pociones y carga tu Definitiva!\n");

        while (vidaJugador > 0 && vidaJefe > 0) {
            defendiendo = false; // Reinicia la defensa cada turno

            System.out.println("--------------------------------------------------");
            System.out.println("💖 TU HP: " + vidaJugador + " | ⚡ ENERGÍA: " + energia + "/100 | 🧪 POCIONES: " + pociones);
            System.out.println("👹 HP DEL JEFE: " + vidaJefe);
            System.out.println("--------------------------------------------------");
            System.out.println("1. ⚔️ Ataque Básico (Daño moderado, carga 25 de energía)");
            if (pociones > 0) {
                System.out.println("2. 🧪 Usar Poción (Cura 30-40 HP. Te quedan " + pociones + ")");
            } else {
                System.out.println("2. ❌ Usar Poción (Sin pociones restantes)");
            }
            System.out.println("3. 🛡️ Defender (Recibes 50% de daño este turno, carga 15 de energía)");
            
            if (energia >= 100) {
                System.out.println("4. 🌟 HABILIDAD DEFINITIVA (Daño Masivo) ¡LISTA!");
            } else {
                System.out.println("4. 🔒 Habilidad Definitiva (Requiere 100 de energía)");
            }
            
            System.out.print("\nElige tu acción: ");

            int accion = 0;
            if (scanner.hasNextInt()) {
                accion = scanner.nextInt();
            } else {
                scanner.next(); 
            }

            System.out.println("");

            // Resolución del turno del jugador
            switch (accion) {
                case 1:
                    int danoAtaque = random.nextInt(11) + 15; // 15 a 25
                    vidaJefe -= danoAtaque;
                    energia += 25;
                    System.out.println("⚔️ Atacas con tu espada haciendo " + danoAtaque + " de daño.");
                    break;
                case 2:
                    if (pociones > 0) {
                        int curacion = random.nextInt(11) + 30; // 30 a 40
                        vidaJugador += curacion;
                        if (vidaJugador > 100) vidaJugador = 100;
                        pociones--;
                        System.out.println("🧪 Te bebes una poción y recuperas " + curacion + " HP.");
                    } else {
                        System.out.println("⚠️ Buscas en tu bolsa pero no te quedan pociones. ¡Pierdes el turno!");
                    }
                    break;
                case 3:
                    defendiendo = true;
                    energia += 15;
                    System.out.println("🛡️ Levantas tu escudo. Tu defensa aumenta este turno.");
                    break;
                case 4:
                    if (energia >= 100) {
                        int danoDefinitiva = random.nextInt(21) + 60; // 60 a 80
                        vidaJefe -= danoDefinitiva;
                        energia = 0; // Se gasta la energía
                        System.out.println("🌟 ¡LIBERAS TU PODER OCULTO! La Habilidad Definitiva impacta por " + danoDefinitiva + " de daño.");
                    } else {
                        System.out.println("⚠️ No tienes suficiente energía. ¡El ataque falla y pierdes el turno!");
                    }
                    break;
                default:
                    System.out.println("⚠️ Te quedaste paralizado y perdiste tu turno.");
                    break;
            }

            // Límite de energía
            if (energia > 100) energia = 100;

            // Turno del Jefe
            if (vidaJefe > 0) {
                // Mecánica de Fase 2
                if (vidaJefe <= 100 && !jefeEnfurecido) {
                    jefeEnfurecido = true;
                    System.out.println("🔥 ¡EL JEFE SE ENFURECE! Sus ataques ahora hacen más daño. 🔥");
                }

                int danoJefe = random.nextInt(11) + 10; // 10 a 20 de daño base
                
                if (jefeEnfurecido) {
                    danoJefe += 15; // Si está enfurecido pega mucho más duro
                }

                if (defendiendo) {
                    danoJefe /= 2; // La defensa reduce el daño a la mitad
                    System.out.println("🛡️ Tu escudo absorbe parte del golpe.");
                }

                vidaJugador -= danoJefe;
                System.out.println("🩸 El Caballero Corrupto te ataca y te quita " + danoJefe + " HP.");
            }
        }

        // Final del juego
        System.out.println("\n==================================================");
        if (vidaJugador > 0) {
            System.out.println("🏆 ¡VICTORIA! El Caballero Corrupto ha caído.");
            System.out.println("Te sobraron " + pociones + " pociones y te quedaron " + vidaJugador + " HP.");
        } else {
            System.out.println("💀 GAME OVER. Has sido derrotado.");
        }
        System.out.println("==================================================");

        scanner.close();
    }
}