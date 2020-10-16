package com.maximus.jhingolbback.result;

public enum ResultType {
    INFO(0,"info"),

    SUCCESS(1,"success"),

    WARNING(2,"warning"),

    ERROR(3,"error");

    private Integer key;
    private String type;

    ResultType(Integer key,String type) {
        this.key = key;
        this.type = type;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
