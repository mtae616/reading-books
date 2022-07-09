package InterfaceAndAbstractClass;

import java.util.Locale;

interface Upper {} // marker interface
interface Lower {} // marker interface

interface Printable2 {
    String getContents();
}

class Report implements Printable2, Upper {
    String cons;

    public Report(String cons) {
        this.cons = cons;
    }

    @Override
    public String getContents() {
        return cons;
    }
}

class Printer2 {
    public void printContents(Printable2 doc) {
        if(doc instanceof Upper) {
            System.out.println(doc.getContents().toUpperCase());
        }
        else if (doc instanceof Lower) {
            System.out.println(doc.getContents().toLowerCase());
        }
        else {
            System.out.println(doc.getContents());
        }
    }

}

public class MarkerInterface {
    public static void main(String[] args) {
        Printer2 prn = new Printer2();
        Report doc = new Report("Simple Funny News ~");
        prn.printContents(doc);
    }
}
