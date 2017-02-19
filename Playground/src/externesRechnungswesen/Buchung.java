package externesRechnungswesen;
import java.util.*;
import java.text.SimpleDateFormat;

public class Buchung{

  static int nextBuchungsnummer = 0;
  Calendar date;
  public String buchungsname;
  int buchungsnummer;
  double wert;

  public Buchung(double wert){
    this.date = new GregorianCalendar();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
    this.buchungsname = sdf.format(date.getTime());
    this.wert = wert;
    this.buchungsnummer = nextBuchungsnummer;
    nextBuchungsnummer++;
  }
}
