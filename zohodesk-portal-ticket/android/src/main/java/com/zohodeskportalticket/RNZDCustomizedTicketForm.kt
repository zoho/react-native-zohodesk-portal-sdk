package com.zohodeskportalticket;

import java.util.List;
import com.zoho.desk.asap.asap_tickets.utils.PreFillTicketField;
data class RNZDCustomizedTicketForm(
    var departmentId: String,
    var layoutId: String,
    var customizedTicketFields: List<PreFillTicketField>
)