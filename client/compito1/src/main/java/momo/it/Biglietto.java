package momo.it;

public class Biglietto {
    
    private String posto;
    private int numeroSeriale;


    public Biglietto() {
    }


    public Biglietto(String posto, int numeroSeriale) {
        this.posto = posto;
        this.numeroSeriale = numeroSeriale;
    }


    public String getPosto() {
        return posto;
    }
    public void setPosto(String posto) {
        this.posto = posto;
    }
    public int getNumeroSeriale() {
        return numeroSeriale;
    }
    public void setNumeroSeriale(int numeroSeriale) {
        this.numeroSeriale = numeroSeriale;
    }

    


}
