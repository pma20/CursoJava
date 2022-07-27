import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio {
    public static void main(String[] args) {
        //Mensaje de bienvenida
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String fechaActual = df.format(date);

        System.out.println("Hola, a fecha " + fechaActual + " te pido tu nombre para poder referirme a ti");

        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();

        //Te pide el sentimiento
        String[] sentimientos = {"triste","alegre","nervioso"};

        System.out.println(nombre + ", dime si estás triste, alegre o nervioso");
        String sentimiento = sc.nextLine().toLowerCase(Locale.ROOT);

        if (sentimiento.equals(sentimientos[0])){
            //Número random
            int min = 0;
            int max= 2;
            Random random = new Random();
            int numeroRandom = random.nextInt(min + max);

            //Definicion de chistes
            String[] chiste = {"Van dos y se cae el del medio",
                    "¿Cuál es la videoconsola favorita de los cerdos?\nLa Ouiiiiiii",
                    "Era un hombre tan alto tan alto tan alto que cuando se comió un yogurt le llegó al estómago caducado"};

            System.out.println(chiste[numeroRandom]);

        } else if (sentimiento.equals(sentimientos[1])) {
            System.out.println("¡¡Enhorabuena!!");

        } else if (sentimiento.equals(sentimientos[2])) {
            System.out.println("Tranquilo, todo va a ir a mejor");
        }
    }
}
