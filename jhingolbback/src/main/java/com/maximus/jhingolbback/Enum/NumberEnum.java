package com.maximus.jhingolbback.Enum;

public enum NumberEnum {
    ZERO("0","零"),
    ONE("1","一"),
    TWO("2","二"),
    THREE("3","三"),
    FOUR("4","四"),
    FIVE("5","五"),
    SIX("6","六"),
    SEVEN("7","七"),
    EIGHT("8","八"),
    NINE("9","九");

    private String number;
    private String cnNumber;

    NumberEnum(String number,String cnNumber) {
        this.number = number;
        this.cnNumber = cnNumber;
    }

    public String getNumber() {
        return number;
    }

    public String getCnNumber() {
        return cnNumber;
    }

    public static String getValueByKey(String key) {
        NumberEnum[] numberEnums = NumberEnum.values();
        for(NumberEnum numberEnum : numberEnums) {
            if(numberEnum.getNumber().equals(key)) {
                return numberEnum.getCnNumber();
            }
        }
        return null;
    }
}
