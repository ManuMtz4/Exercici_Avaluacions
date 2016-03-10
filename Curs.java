import org.joda.time.DateTime;
import org.joda.time.Days;

public class Curs {
    
    Avaluacio ava1;
    Avaluacio ava2;
    Avaluacio ava3;
    
    public Curs (String dIni1, String dFi1, String dIni2, String dFi2, String dIni3, String dFi3) throws DateInvalidException, CursInvalidException {
        
        if (validaAvaluacions(dFi1,dIni2) || validaAvaluacions(dFi2,dIni3) || validaAvaluacions(dFi1,dIni3)) {
            ava1 = new Avaluacio(dIni1, dFi1);
            ava2 = new Avaluacio(dIni2, dFi2);
            ava3 = new Avaluacio(dIni3, dFi3);
        } else { 
            throw new CursInvalidException(); 
        } 
        
        
    }
    
    @Override
    public String toString() {
        String s = ava1+"\n"+ava2+"\n"+ava3+"\n\nTotal dies: "+nombreDies();
        return s;
    }
    
    public int nombreDies() {
        int count = ava1.nombreDies();
        count += ava2.nombreDies();
        count += ava3.nombreDies();
        return count;
    }
    
    public boolean validaAvaluacions(String dataFiBefore, String dataIniciAfter) {
        DateTime dFb = JodaDT.parseDDMMYYYY(dataFiBefore);
        DateTime dIa = JodaDT.parseDDMMYYYY(dataIniciAfter);
        Days d = Days.daysBetween(dFb, dIa);
        int days = d.getDays();
        if (days > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String args[]) throws DateInvalidException, CursInvalidException {
        Curs c = new Curs("01/01/2016","01/03/2016","01/04/2016","01/06/2016","01/07/2016","01/09/2016");
        System.out.println(c);
    }
}

class CursInvalidException extends Exception { 
    public CursInvalidException(){ 
        super("Error en la creació de Curs"); 
    } 
} 