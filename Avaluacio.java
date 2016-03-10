import org.joda.time.DateTime;
import org.joda.time.Days;

public class Avaluacio {
    
    protected String dataInici;
    protected String dataFi;
    
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
    
    public static boolean validPeriod(String dataInici, String dataFi) {
        DateTime dI = JodaDT.parseDDMMYYYY(dataInici);
        DateTime dF = JodaDT.parseDDMMYYYY(dataFi);
        Days d = Days.daysBetween(dI, dF);
        int days = d.getDays();
        if (days >= 60) {
            return true;
        } else {
            return false;
        }
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
    
    public int nombreDies() {
        DateTime dI = JodaDT.parseDDMMYYYY(this.getDataInici());
        DateTime dF = JodaDT.parseDDMMYYYY(this.getDataFi());
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