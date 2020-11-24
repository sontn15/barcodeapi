package com.sh.barcodeapi.utils;

public class Const {

    public static class CUSTOMER_TYPE {
        public static final Long DAI_LY = 1L;
        public static final Long BAN_BUON = 2L;
        public static final Long BAN_LE = 3L;
    }

    public static class STATUS_BILL_DESKTOP {
        public static final Long CHUA_GUI = 0L;
        public static final Long DA_GUI = 1L;
        public static final Long DA_XU_LY = 2L;
        public static final Long HUY_DON_HANG = 4L;
    }

    public static class SORT {
        public static final String SORT_ID_BILL = "id";
        public static final String SORT_ID_ITEM = "id";
        public static final String SORT_ID_THU_CHI = "id";
    }

    public static class UNIT_TYPE_FOR_STORE {
        public static final Long CENTIMETRE = 1L;
        public static final Long MILLIMETRE = 2L;
    }

    public static class LOAI_SANPHAM {
        public static final Long KICH_THUOC = 1L;
        public static final Long VAT_TU = 2L;
        public static final Long DICH_VU = 3L;
        public static final Long VAN_TAI = 4L;
    }

    public static class EMPLOYEE_TYPE {
        public static final Long NHAN_VIEN = 1L;
        public static final Long QUAN_LY = 2L;
    }

    public static class PARAMETER {
        public static final String ID = "id";
        public static final String BILL_ID = "billId";
        public static final String CUSTOMER_CODE = "customerCode";
        public static final String BILL_NUMBER = "billNumber";
        public static final String CREATE_DATE = "createDate";
        public static final String TOTAL_MONEY = "totalMoney";
        public static final String CUSTOMER_NAME = "customerName";
        public static final String INCOME_MONEY = "incomeMoney";
        public static final String COST_MONEY = "costMoney";
        public static final String DESCRIPTION = "description";
        public static final String TOTAL_GIA_VON = "totalGiaVon";
        public static final String TYPE_NOTIFICATION = "typeNotification";
    }

    public static class FCM_MESSAGE {
        public static final String TOPIC_MESSAGE = "SalesTopic";
        public static final String TYPE_DON_HANG_NOTIFICATION = "1";
        public static final String TYPE_THU_CHI_NOTIFICATION = "2";
    }

}
