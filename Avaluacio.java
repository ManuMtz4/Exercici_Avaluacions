/*
 * Avaluacio.java        1.0 Mar 10, 2016
 *
 * Copyright 2016 Manuel Martínez <ManuMtz@icloud.com> / <ManuMtz@hotmail.co.uk>
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

import org.joda.time.DateTime;
import org.joda.time.Days;

public class Avaluacio {
    
    protected String dataInici;
    protected String dataFi;
    
    // Constructor de Evaluaciones
    public Avaluacio (String dataInici, String dataFi) throws DateInvalidException {
        if (validPeriod(dataInici,dataFi)) {
            this.dataInici = dataInici;
            this.dataFi = dataFi;
        } else { 
            throw new DateInvalidException(dataInici,dataFi); 
        } 
    }
    
    // BEGIN - Getters & Setters
    protected String getDataInici() {
        return dataInici;
    }
    
    protected String getDataFi() {
        return dataFi;
    }
    
    protected void setData(String dataInici, String dataFi) throws DateInvalidException {
        if (validPeriod(dataInici,dataFi)) {
            this.dataInici = dataInici;
            this.dataFi = dataFi;
        } else { 
            throw new DateInvalidException(dataInici,dataFi);
        } 
    }
    // END - Getters & Setters
    
    @Override
    public String toString() {
        String s = "Avaluació: "+this.getDataInici()+" - "+this.getDataFi()+"\nDurada: "+nombreDies()+" dies";
        return s;
    }
    
    /**
     * 
     * Retorna True o False, para validar si una evaluación es correcta y tiene un mínimo de días
     * 
     *  Fecha de inicio de la Evaluacion en String
     * 
     *  Fecha de finalización de la Evaluacion en String
     *  
     * @param  dataInici
     * @param  dataFi
     * @return  boolean
     * 
     */
    
    public static boolean validPeriod(String dataInici, String dataFi) {
        DateTime dI = JodaDT.parseDDMMYYYY(dataInici);
        DateTime dF = JodaDT.parseDDMMYYYY(dataFi);
        Days d = Days.daysBetween(dI, dF);
        int days = d.getDays();
        return days >= 30;
    } 
    
    /**
     * 
     * Retorna una cantidad de días que tiene la evaluación
     *  
     * @return  int
     * 
     */
    
    public int nombreDies() {
        DateTime dI = JodaDT.parseDDMMYYYY(dataInici);
        DateTime dF = JodaDT.parseDDMMYYYY(dataFi);
        Days d = Days.daysBetween(dI, dF);
        int days = d.getDays();
        return days;
    } 
}

class DateInvalidException extends Exception { 
    public DateInvalidException(String dataInici, String dataFi){ 
        super("Error en la creació de Avaluació: "+dataInici+" - "+dataFi); 
    } 
} 