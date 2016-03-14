/*
 * Curs.java        1.0 Mar 10, 2016
 *
 * Copyright 2016 Manuel Martínez <ManuMtz@icloud.com> / <ManuMtz@hotmail.co.uk>
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

import org.joda.time.DateTime;
import org.joda.time.Days;

public class Curs {
    
    Avaluacio ava1;
    Avaluacio ava2;
    Avaluacio ava3;
    
    // Constructor de Cursos
    public Curs (String dIni1, String dFi1, String dIni2, String dFi2, String dIni3, String dFi3) throws DateInvalidException, CursInvalidException {
        
        if (validaAvaluacions(dFi1,dIni2) && validaAvaluacions(dFi2,dIni3)) {
            ava1 = new Avaluacio(dIni1, dFi1);
            ava2 = new Avaluacio(dIni2, dFi2);
            ava3 = new Avaluacio(dIni3, dFi3);
        } else { 
            throw new CursInvalidException(); 
        }  
    }
    
    @Override
    public String toString() {
        return ava1+"\n"+ava2+"\n"+ava3+"\n\nTotal dies: "+nombreDies();
    }
    
    /**
     * 
     * Retorna una cantidad de días que tiene el curso total
     *  
     * @return  int
     * 
     */
    
    public int nombreDies() {
        return ava1.nombreDies()+ava2.nombreDies()+ava3.nombreDies();
    }
    
    /**
     * 
     * Retorna True o False, para validar si las evaluaciones son coherentes y no se solapan
     * 
     *  Fecha de finalización de la Evaluacion Anterior en String
     * 
     *  Fecha de inicio de la Evaluacion Siguiente en String
     *  
     * @param  dataFiBefore
     * @param  dataIniciAfter
     * @return  boolean
     * 
     */
    
    public boolean validaAvaluacions(String dataFiBefore, String dataIniciAfter) {
        DateTime dFb = JodaDT.parseDDMMYYYY(dataFiBefore);
        DateTime dIa = JodaDT.parseDDMMYYYY(dataIniciAfter);
        Days d = Days.daysBetween(dFb, dIa);
        int days = d.getDays();
        return days > 0;
    }
    
    public static void main(String args[]) throws DateInvalidException, CursInvalidException {
        Curs c = new Curs("15/09/2015","15/12/2015","16/12/2015","18/02/2016","19/02/2016","01/05/2016");
        System.out.println(c);
        
        try { 
            Curs c1 = new Curs("15/09/2015","15/12/2014","16/12/2015","18/02/2016","19/02/2016","01/05/2016");
            System.out.println(c1);
        } catch (DateInvalidException de) { 
            System.err.println("Se ha producido un error de Evaluación, días negativos o no suficientes"); 
        }
        
        try { 
            Curs c2 = new Curs("15/09/2015","15/12/2015","16/12/2015","18/02/2016","14/09/2015","01/05/2016");
            System.out.println(c2); 
        } catch (CursInvalidException ce) {
            System.err.println("Se ha producido un error de Curso, dos o más evaluaciones se solapan"); 
        }
    }
}

class CursInvalidException extends Exception { 
    public CursInvalidException(){ 
        super("Error en la creació de Curs, dos o más evaluaciones se solapan"); 
    } 
} 