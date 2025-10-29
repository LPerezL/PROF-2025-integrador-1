package com;

public interface Operacion {
    public String Id;
    public String concepto;
    public double importe;

    
    // Method to get the operation ID
    long getId();
    
    // Method to get the operation concept/description
    String getConcepto();
    
    // Method to get the operation amount
    double getImporte();
    
}