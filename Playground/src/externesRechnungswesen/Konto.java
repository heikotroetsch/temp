package externesRechnungswesen;
import java.util.*;
//Kontenramen zur entwicklung
//http://files.schulbuchzentrum-online.de/onlineanhaenge/files/industriekontenrahmen_2012_1.pdf
public class Konto {

String kontoNummer;
String kontoName;
double saldo;
double sumSoll = 0;
double sumHaben = 0;
ArrayList<Buchung> haben = new ArrayList<Buchung>();
ArrayList<Buchung> soll = new ArrayList<Buchung>();
public static final int SOLL = 0;
public static final int HABEN = 1;

  public Konto(String kontoNummer){
    this.kontoNummer = kontoNummer;
  }

  public void buchen(double wert, int sollOderHaben){
    Buchung buchung = new Buchung(wert);
    if(sollOderHaben == Konto.SOLL){
      soll.add(buchung);
    }else if(sollOderHaben == Konto.HABEN){
        haben.add(buchung);
    }else{
      System.out.println("Error: The Booking with the number: "+ buchung.buchungsnummer+" could not be added. \nTYPE NOT DEFINED");
    }
  }

  public void createSaldo(){
    for (int i = 0; i < soll.size(); i++) {
			     this.sumSoll += soll.get(i).wert;
		  }
    for (int i = 0; i < haben.size(); i++) {
			     this.sumHaben += haben.get(i).wert;
		  }
    if(sumSoll>sumHaben){
      Buchung buchung = new Buchung(sumSoll-sumHaben);
      this.saldo = sumSoll-sumHaben;
      buchung.buchungsname = "SALDO";
      haben.add(buchung);
      sumHaben = sumSoll;
    }else if(sumHaben>sumSoll){
      Buchung buchung = new Buchung(sumHaben-sumSoll);
      saldo = sumHaben-sumSoll;
      buchung.buchungsname = "SALDO";
      soll.add(buchung);
      sumSoll = sumHaben;
    }
  }

  public void removeSaldo(){
    for (int i = 0; i < soll.size(); i++) {
			     if(soll.get(i).buchungsname.matches("SALDO")){
           soll.remove(soll.get(i));
         }
		  }
    for (int i = 0; i < haben.size(); i++) {
      if(haben.get(i).buchungsname.matches("SALDO")){
        haben.remove(haben.get(i));
      }
		  }
  }

  public void printKonto(){
    this.createSaldo();
    String leftAlignFormat = "| %-15s %-15s | %15s %15s |%n";
    System.out.format("                           "+this.kontoNummer+" "+this.kontoName+"\n");
    System.out.format("+---------------------------------+---------------------------------+%n");
    System.out.format("| SOLL                            |                           HABEN |%n");
    System.out.format("+---------------------------------+---------------------------------+%n");
    int biggerSize;
    if(soll.size()>haben.size()){
      biggerSize = soll.size();
    }else {
      biggerSize = haben.size();
    }
    for (int i = 0; i < biggerSize; i++) {
      if(i<soll.size()&&i<haben.size()){
        System.out.format(leftAlignFormat, soll.get(i).buchungsname, soll.get(i).wert, haben.get(i).buchungsname, haben.get(i).wert);
      }else if(i<soll.size()){
        System.out.format(leftAlignFormat, soll.get(i).buchungsname, soll.get(i).wert, "", "");
      }else if(i<haben.size()){
        System.out.format(leftAlignFormat, "", "", haben.get(i).buchungsname, haben.get(i).wert);
      }
    }
    System.out.format("+---------------------------------+---------------------------------+%n");
    System.out.format(leftAlignFormat, "SUMME", sumSoll, "", sumHaben);
    System.out.format("+---------------------------------+---------------------------------+%n");
    this.removeSaldo();
  }

public static void main(String[]args){
  Konto k = new Konto("2400");
  k.buchen(432, Konto.SOLL);
  k.buchen(415, Konto.SOLL);
  k.buchen(205410, Konto.SOLL);
  k.buchen(20540, Konto.SOLL);
  k.buchen(2030, Konto.HABEN);
  k.buchen(2500, Konto.HABEN);
  k.buchen(54200, Konto.HABEN);
  k.buchen(300, Konto.HABEN);



  k.printKonto();



}
}
