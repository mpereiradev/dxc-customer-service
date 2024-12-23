package dev.matheuspereira.dxc_customer_service.domain.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super("Not found the register", 404);
    }
}