package com.zoho.desk.asap;
import java.util.List;

public class RNZDVisibleTicketField {
    private String departmentId;
    private String layoutId;
    private List<String> fieldNames;

    // Constructor
    public RNZDVisibleTicketField(String departmentId, String layoutId, List<String> ticketFieldsList) {
        this.departmentId = departmentId;
        this.layoutId = layoutId;
        this.fieldNames = ticketFieldsList;
    }

    // Getters and Setters
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> ticketFieldsList) {
        this.fieldNames = ticketFieldsList;
    }
}
