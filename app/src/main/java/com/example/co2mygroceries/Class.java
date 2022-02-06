package com.example.co2mygroceries;

import java.io.Serializable;

public class Class implements Serializable {
    String receiptLine;
    String[] PRODUCT_NAME = new String[3];
    String[] PRODUCT_NAME_DE = new String[3];
    double [] fuzzyScore = new double[3];
    int[] PRODUCT_INDEX = new int[3];
    String[] PRODUCT_ID = new String[3];
    Double[] PRODUCT_QUANTITY = new Double[3];
    String[] Agriculture = new String[3];
    String[] iLUC = new String[3];
    String[] Food_processing = new String[3];
    String[] Packaging = new String[3];
    String[] Transport = new String[3];
    String[] Retail = new String[3];
    Double[] Total_kg_CO2_eq_kg = new Double[3];
    Double[] endResult = new Double[3];
}
