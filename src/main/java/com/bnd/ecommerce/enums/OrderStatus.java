package com.bnd.ecommerce.enums;

public enum OrderStatus {
//    chờ xác nhận
    PENDING,
//    đang tiến hành, đang trong quá trình vận chuyển
    IN_PROGRESS,
//    hoàn thanh giao hàng
    COMPLETED,
//    hủy đơn hàng
    CANCELED,
//    bị hủy do lỗi kỹ thuật hoặc lý do khách hàng
    FAILED
}
