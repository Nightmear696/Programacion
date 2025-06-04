package model.validations;



import java.time.LocalDate;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author martilopbel
 */
public class UserDataValidations {
     /**
     * Validamos un ID según el formato establecido.
     *
     * @param typeDoc indica el tipo de documento (1 indica ID válido).
     * @param ID es el identificador a validar.
     * @return true si el NIF es válido, false en caso contrario.
     */
    public static boolean checkID(int typeDoc, String ID) {

        if (typeDoc != 1) {
            return false;
        }

        // Verificamos que el ID no sea nulo y tenga 9 caracteres
        if (ID == null || ID.length() != 9) {
            return false;
        }

        // Separamos la parte numerica y la letra 
        String parteNumeros = ID.substring(0, 8);
        char letraFinal = ID.charAt(8);

        for (int i = 0; i < parteNumeros.length(); i++) {
            if (!Character.isDigit(parteNumeros.charAt(i))) {
                return false;
            }
            // Calculamos la letra esperada basandonos en la parte numérica
            String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
            int valorNume = Integer.parseInt(parteNumeros);
            char letraEsperada = letrasDNI.charAt(valorNume % 23);

            // Comparamos la letra esperada con la letra en el ID
            return letraFinal == letraEsperada;
        }
        return true;
    }

    /**
     * Valida si una fecha tiene el formato "dd/MM/yyyy" y si es valida.
     *
     * @param date la fecha a validar.
     * @return true si el formato y valor de la fecha son correctos, false en
     * caso contrario.
     */
   public static boolean checkFormatDate(String date) {
        boolean resultado = false;
 
        if (date == null || date.length() != 10) {
            return resultado;
        }
 
        String[] partes = date.split("/");
 
        if (partes.length != 3) {
            return resultado;
        }
 
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int año = Integer.parseInt(partes[2]);
 
        if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || año < 1000 || año > 9999) {
            return resultado;
        }
 
        if (mes == 2) {
 
            if ((año % 4 == 0 && (año % 100 != 0 || año % 400 == 0))) {
                if (dia > 29) {
                    return resultado;
                }
            } else {
                if (dia > 28) {
                    return resultado;
                }
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia > 30) {
                return resultado;
            }
        } else if (dia > 31) {
            return resultado;
        }
 
        resultado = true;
        return resultado;
    }
    /**
     * Calcula la edad en base a una fecha de nacimiento.
     *
     * @param birthDate la fecha de nacimiento en formato "dd/MM/yyyy".
     * @return la edad calculada, o -1 si la fecha es invalida o incoherente.
     */
    public static int checkCalculateDate(String birthDate) {
        // fecha actual
        LocalDate fechaHoy = LocalDate.now();

        int dia = fechaHoy.getDayOfMonth();
        int mes = fechaHoy.getMonthValue();
        int year = fechaHoy.getYear();

        // Validamos que no sea nula y tenga 10 caracteres (son 10 y no 8 pq dejamos dos espacios en blanco)
        if (birthDate == null || birthDate.length() != 10) {
            return -1;
        }

        // Validamos que tenga 3 secciones
        String[] parts = birthDate.split("/");
        if (parts.length != 3) {
            return -1;
        }

        // partes a enteros
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year1 = Integer.parseInt(parts[2]);

        //Verificamos rango
        if (month < 1 || month > 12) {
            return -1;
        }

        //ultimos dias mes
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        //verificamos bisiesto
        if (mes == 2) {
            boolean esBisiesto = (year1 % 4 == 0 && year1 % 100 != 0) || (year1 % 400 == 0);
            if (esBisiesto) {
                diasPorMes[1] = 29;
            }
        }

        //Validamos rango permitido de dias
        if (dia < 1 || dia > diasPorMes[mes - 1]) {
            return -1;
        }

        //verificamos que no sea futura
        if (year1 > year || (year1 == year && month > mes)
                || (year1 == year && month == mes && day > dia)) {
            return -1;
        }

        //calculo edad
        int age = year - year1;

        if (month > mes || (month == mes && day > dia)) {
            age--;
        }
        return age;
    }

    /**
     * Verifica si un código postal es valido (5 dígitos entre 1000 y 52999)
     *
     * @param zip el codigo postal a validar
     * @return true si el codigo postal es valido, false en caso contrario
     */
    public static boolean checkPostalCode(String zip) {

        if (zip == null || zip.isEmpty()) {
            return false;
        }
        
//Comprobamos que tenga 5 digitos
        if (zip.length() != 5) {
            return false;
        }

        //comprobamos si son digitos       
        for (int i = 0; i < zip.length(); i++) {
            if (!Character.isDigit(zip.charAt(i))) {
                return false;
            }
        }

        //conversion string a int y validación
        int numero = Integer.parseInt(zip);
        return numero >= 1000 && numero <= 52999;
    }

    /**
     * Verifica si un string contiene solo caracteres numericos
     *
     * @param str el string a comprobar
     * @return true si el string es numerico, false en caso contrario
     */
    public static boolean isNumeric(String str) {
        boolean numericoOK = true;

        //comprobamos que los sean digitos
        for (char comprobar : str.toCharArray()) {
            if (!Character.isDigit(comprobar)) {
                numericoOK = false;
                break;
            }
        }
        return numericoOK;
    }

    /**
     * Verifica si un string contiene solo caracteres alfabéticos
     *
     * @param str el string a comprobar
     * @return true si el string es alfabético, false en caso contrario
     */
    public static boolean isAlphabetic(String str) {
        boolean numericoOK = true;

        //comprobamos que sean solo letras
        for (char comprobar : str.toCharArray()) {
            if (!Character.isAlphabetic(comprobar)) {
                numericoOK = false;
                break;
            }
        }
        return numericoOK;
    }

    /**
     * Valida si un correo electronico tiene un formato valido
     *
     * @param email el correo electronico a validar
     * @return true si el correo electrónico es valido, false en caso contrario
     */
    public static boolean checkEmail(String email) {
        //Dividimos para verificar la 1r parte
        String[] arroba = email.split("@");

        //Verifica la 2n parte
        String[] despuesArroba = email.split("\\.");
        boolean emailSirve = false;
        //Comprobamos que no sea nulo ni vacio
        if (email == null || email.isEmpty()) {
            return emailSirve;
        }

        //comprobamos que tenga contenido antes y despues @
        if (arroba.length != 2 || arroba[0].isEmpty() || arroba[1].isEmpty()) {
            return emailSirve;
        }

        //Comprobamos que tenga un . almenos despues @
        if (despuesArroba.length < 2 || despuesArroba[0].isEmpty() || despuesArroba[1].isEmpty()) {
            return emailSirve;
        }
        return true;
    }

    /**
     * Verifica si un nombre es valido
     *
     * @param name el nombre a validar
     * @return true si el nombre es valido, false en caso contrario
     */
    public static boolean checkName(String name) {
        boolean nameValid = false;
        //Comprobamos que la longitud sea razonable
        if (name.length() < 1 || name.length() > 27) {
            return nameValid;
        }
        //Comprobamos que sean solo letras
        for (char comprobar : name.toCharArray()) {
            if (!Character.isAlphabetic(comprobar)) {
                return nameValid;
            }

        }
        return true;
    }
    
}
