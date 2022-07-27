import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Ejercicio {
    public static void main(String args[]){
        Collection coches = new ArrayList<Coche>();
        TreeSet<String> nombreModelos = new TreeSet<String>();

        try {
            String rutaArchivoExcel = "Marcas_y_modelos.xlsx";
            FileInputStream inputStream = new FileInputStream(new File(rutaArchivoExcel));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();

            String marca = null;
            String modelo = null;
            String velocidad = null;


            while (iterator.hasNext()) {
                Row nextRow = (Row) iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                int numColum = 0;
                while(cellIterator.hasNext()) {
                    var cell = cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    switch (numColum) {
                        case 0:
                            marca = contenidoCelda;
                            numColum++;
                            break;
                        case 1:
                            modelo = contenidoCelda;
                            numColum++;
                            break;
                        case 2:
                            velocidad = contenidoCelda;
                            break;
                    }
                }

                if (marca.equals("Marca") == false){
                    coches.add(new Coche(marca,modelo,velocidad));
                    nombreModelos.add(modelo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        Iterator value1 = nombreModelos.iterator();
        StringBuilder str1 = new StringBuilder();
        str1.append("Los modelos son los siguientes:");
        StringBuilder str2 = new StringBuilder();
        str2.append("Las velocidades son las siguientes:");

        while (value1.hasNext()) {
            Iterator value2 = coches.iterator();
            String modeloActual = (String) value1.next();

            while (value2.hasNext()) {
                Coche cocheIterando = (Coche) value2.next();

                if (modeloActual.equals(cocheIterando.modelo)){
                    str1.append("\n" + cocheIterando.modelo);
                    str2.append("\n" + cocheIterando.velocidad);
                }
            }
        }

        GrabarArchivo grabar = new GrabarArchivo();
        String nombreArchivo = "Datos.txt";

        grabar.crear(nombreArchivo, str1.toString() + "\n\n" + str2.toString());

    }

}

class Coche{
    String marca;
    String modelo;
    String velocidad;

    public Coche(String marca, String modelo, String velocidad) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = velocidad;
    }
}

class GrabarArchivo{
    public void crear(String direccion,String datos){
        File archivo = new File(direccion);
        try {
            FileWriter escritor = new FileWriter(archivo);
            BufferedWriter buffer = new BufferedWriter(escritor);
            buffer.write(datos);
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
