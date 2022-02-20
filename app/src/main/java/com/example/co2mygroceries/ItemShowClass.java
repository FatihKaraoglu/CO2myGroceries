package com.example.co2mygroceries;

public class ItemShowClass {
    String[] all_Products;
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

    public ItemShowClass(String[] all_Products, String receiptLine, String[] PRODUCT_NAME, String[] PRODUCT_NAME_DE, double[] fuzzyScore, int[] PRODUCT_INDEX, String[] PRODUCT_ID, Double[] PRODUCT_QUANTITY, String[] agriculture, String[] iLUC, String[] food_processing, String[] packaging, String[] transport, String[] retail, Double[] total_kg_CO2_eq_kg, Double[] endResult) {
        this.all_Products = all_Products;
        this.receiptLine = receiptLine;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_NAME_DE = PRODUCT_NAME_DE;
        this.fuzzyScore = fuzzyScore;
        this.PRODUCT_INDEX = PRODUCT_INDEX;
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_QUANTITY = PRODUCT_QUANTITY;
        Agriculture = agriculture;
        this.iLUC = iLUC;
        Food_processing = food_processing;
        Packaging = packaging;
        Transport = transport;
        Retail = retail;
        Total_kg_CO2_eq_kg = total_kg_CO2_eq_kg;
        this.endResult = endResult;
    }

    public String[] getAll_Products() {
        return all_Products;
    }

    public void setAll_Products(String[] all_Products) {
        this.all_Products = all_Products;
    }

    public String getReceiptLine() {
        return receiptLine;
    }

    public void setReceiptLine(String receiptLine) {
        this.receiptLine = receiptLine;
    }

    public String[] getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String[] PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String[] getPRODUCT_NAME_DE() {
        return PRODUCT_NAME_DE;
    }

    public void setPRODUCT_NAME_DE(String[] PRODUCT_NAME_DE) {
        this.PRODUCT_NAME_DE = PRODUCT_NAME_DE;
    }

    public double[] getFuzzyScore() {
        return fuzzyScore;
    }

    public void setFuzzyScore(double[] fuzzyScore) {
        this.fuzzyScore = fuzzyScore;
    }

    public int[] getPRODUCT_INDEX() {
        return PRODUCT_INDEX;
    }

    public void setPRODUCT_INDEX(int[] PRODUCT_INDEX) {
        this.PRODUCT_INDEX = PRODUCT_INDEX;
    }

    public String[] getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String[] PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public Double[] getPRODUCT_QUANTITY() {
        return PRODUCT_QUANTITY;
    }

    public void setPRODUCT_QUANTITY(Double[] PRODUCT_QUANTITY) {
        this.PRODUCT_QUANTITY = PRODUCT_QUANTITY;
    }

    public String[] getAgriculture() {
        return Agriculture;
    }

    public void setAgriculture(String[] agriculture) {
        Agriculture = agriculture;
    }

    public String[] getiLUC() {
        return iLUC;
    }

    public void setiLUC(String[] iLUC) {
        this.iLUC = iLUC;
    }

    public String[] getFood_processing() {
        return Food_processing;
    }

    public void setFood_processing(String[] food_processing) {
        Food_processing = food_processing;
    }

    public String[] getPackaging() {
        return Packaging;
    }

    public void setPackaging(String[] packaging) {
        Packaging = packaging;
    }

    public String[] getTransport() {
        return Transport;
    }

    public void setTransport(String[] transport) {
        Transport = transport;
    }

    public String[] getRetail() {
        return Retail;
    }

    public void setRetail(String[] retail) {
        Retail = retail;
    }

    public Double[] getTotal_kg_CO2_eq_kg() {
        return Total_kg_CO2_eq_kg;
    }

    public void setTotal_kg_CO2_eq_kg(Double[] total_kg_CO2_eq_kg) {
        Total_kg_CO2_eq_kg = total_kg_CO2_eq_kg;
    }

    public Double[] getEndResult() {
        return endResult;
    }

    public void setEndResult(Double[] endResult) {
        this.endResult = endResult;
    }
}
