package com;

import java.util.List;

public class CuentaBancaria {
    private String numeroCuenta;
    private double saldoInicial;
    private boolean admiteDescubierto;
    private List<Operacion> operaciones;

    public CuentaBancaria(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldoInicial = saldoInicial;
        this.operaciones = new java.util.ArrayList<Operacion>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldoInicial += cantidad;
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldoInicial) {
            saldoInicial -= cantidad;
            return true;
        }
        return false;
    }

    public void addOperacion(Operacion operacion) throws OperacionNulaException,OperacionDuplicadaException {
        boolean duplicada=false;
        if (operacion == null)
            throw new OperacionNulaException("La operacion no puede ser nula");
        
        for (Operacion op : this.operaciones) {
            if (op.getId() == operacion.getId()) {
                duplicada=true;
            }
        }
        if (duplicada)
            throw new OperacionDuplicadaException("La operacion ya existe");
        operaciones.add(operacion);
    }

    public double getSaldoActual() throws saldoNegativoException {
        double saldoActual = 0 + this.saldoInicial;
        for (Operacion operacion : operaciones) {
            saldoActual += operacion.getImporte();
        }
        if (admiteDescubierto==false && saldoActual<0) {
            throw new saldoNegativoException("Saldo negativo no permitido");
        }
        saldoActual = ((int)(saldoActual * 100 + 0.5)) / 100.0;
        
        return saldoActual;
    }

    private static class OperacionNulaException extends Exception {

        public OperacionNulaException(String causa) {
        }
    }

    private static class OperacionDuplicadaException extends Exception {

        public OperacionDuplicadaException(String la_operacion_ya_existe) {
        }
    }

    private static class saldoNegativoException extends Exception {

        public saldoNegativoException(String saldo_negativo_no_permitido) {
        }
    }
}
