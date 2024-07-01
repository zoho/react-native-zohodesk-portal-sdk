const {NativeModules} = require('react-native');
const {RNZDPortalSubmitTicket} = NativeModules;

class ZDCustomizedTicketField {
  constructor(fieldName, value, isEditable) {
      this.fieldName = fieldName;
      this.value = value;
      this.isEditable = isEditable;
  }
} 

class ZDCustomizedTicketForm {
  constructor(departmentId, layoutId, customizedTicketFields) {
      this.departmentId = departmentId;
      this.layoutId = layoutId;
      this.customizedTicketFields = customizedTicketFields;
  }
}

class ZDVisibleTicketField {
  constructor(departmentId, layoutId, fieldNames) {
    this.departmentId = departmentId;
    this.layoutId = layoutId;
    this.fieldNames = fieldNames;
  }
}

module.exports = {

    show: function() {
      RNZDPortalSubmitTicket.show();
    },

    preFillTicketFields: function(forms) {
        const formsDicts = forms.map(form => ({
          departmentId: form.departmentId,
          layoutId: form.layoutId,
          customizedTicketFields: form.customizedTicketFields.map(field => ({
            fieldApiName: field.fieldName,
            fieldValue: field.value,
            isEditable: field.isEditable
          }))
        }));
      
        RNZDPortalSubmitTicket.preFillTicketFields(formsDicts);
    },

    setFieldsListTobeShown: function(visibleFieldsList) {
      const fieldsList = visibleFieldsList.map(field => ({
        departmentId: field.departmentId,
        layoutId: field.layoutId,
        fieldNames: field.fieldNames
      }));
  
      RNZDPortalSubmitTicket.setFieldsListTobeShown(fieldsList);
    },

    ZDCustomizedTicketField,
    ZDCustomizedTicketForm, 
    ZDVisibleTicketField
    

};
