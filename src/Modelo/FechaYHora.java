
package Modelo;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FechaYHora 
{
    private int dia;
    private int mes;
    private int año;
    private String hora;
    private String minutos;
    private String segundos;
    
    public FechaYHora()
    {
        dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mes = Calendar.getInstance().get(Calendar.MONTH)+1;
        año = Calendar.getInstance().get(Calendar.YEAR);
        
        Calendar calendario = new GregorianCalendar();
        Date horaActual = new Date();
        calendario.setTime(horaActual);
        hora = (calendario.get(Calendar.HOUR_OF_DAY) > 9) ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = (calendario.get(Calendar.MINUTE) > 9)? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = (calendario.get(Calendar.SECOND) > 9)? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    
    @Override
    public String toString() 
    {
        return dia + "/" + mes + "/" + año + " - " + hora + ":" + minutos + ":" + segundos;
    }
}
