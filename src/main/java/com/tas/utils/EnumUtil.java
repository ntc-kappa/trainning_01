package com.tas.utils;

public class EnumUtil {
    public enum DeviceHeader {
        CELL_1(1, "Mã thiết bị"),
        CELL_2(2, "Tên thiết bị"),
        CELL_3(3, "Giá thiết bị"),
        CELL_4(4, "Loại thiết bị");

        private int index;
        private String label;

        private DeviceHeader(int index, String label) {
            this.index = index;
            this.label = label;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
