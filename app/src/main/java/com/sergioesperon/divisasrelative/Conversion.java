package com.sergioesperon.divisasrelative;

/**
 * Created by sergi on 22/10/2017.
 */

public class Conversion {

    private final double precio_actual=18.99;

    public double dolarToPeso(double dolar)
    {
        return precio_actual*dolar;
    }

    public double pesoToDolar(double peso)
    {
        return  peso/precio_actual;
    }
}
