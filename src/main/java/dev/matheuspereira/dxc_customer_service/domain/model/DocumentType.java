package dev.matheuspereira.dxc_customer_service.domain.model;

import lombok.Getter;

@Getter
public enum DocumentType {
    CPF("Pessoa física"),
    CNPJ("Pessoa jurídica");

    private String description;

    DocumentType(String description) {
        this.description = description;
    }


    public static DocumentType fromKey(String key) {
        for (DocumentType type : DocumentType.values()) {
            if (type.name().equalsIgnoreCase(key)) {
                return type;
            }
        }

        throw new IllegalArgumentException("No valid identifier type found for key: " + key);
    }
}