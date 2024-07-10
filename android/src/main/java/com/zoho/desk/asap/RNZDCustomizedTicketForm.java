package com.zoho.desk.asap;

import java.util.List;
import com.zoho.desk.asap.asap_tickets.utils.PreFillTicketField;

public class RNZDCustomizedTicketForm {
    private String departmentId;
    private String layoutId;
    private List<PreFillTicketField> customizedTicketFields;

    // Constructor
    public RNZDCustomizedTicketForm(String departmentId, String layoutId, List<PreFillTicketField> ticketFields) {
        this.departmentId = departmentId;
        this.layoutId = layoutId;
        this.customizedTicketFields = ticketFields;
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

    public List<PreFillTicketField> getCustomizedTicketFields() {
        return customizedTicketFields;
    }

    public void setCustomizedTicketFields(List<PreFillTicketField> ticketFields) {
        this.customizedTicketFields = ticketFields;
    }
}