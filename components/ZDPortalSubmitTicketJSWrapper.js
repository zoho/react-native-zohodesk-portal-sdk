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

function validateTicketForms(forms) {
  return (Array.isArray(forms) && forms.every(form =>  
      form instanceof ZDCustomizedTicketForm &&
      Array.isArray(form.customizedTicketFields) &&
      form.customizedTicketFields.every(field => field instanceof ZDCustomizedTicketField)
    )
  )
}

function validateVisibleTicketField(visibleFieldsList) {
    return (Array.isArray(visibleFieldsList) && visibleFieldsList.every(field => field instanceof ZDVisibleTicketField))
}

module.exports = {

    show: function() {
      RNZDPortalSubmitTicket.show();
    },

    preFillTicketFields: function(forms) {
      if (validateTicketForms(forms)) {
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
      } else {
        console.log("The form inputs do not match")
      }
    },

    setFieldsListTobeShown: function(visibleFieldsList) {
      if (validateVisibleTicketField(visibleFieldsList)) {
        const fieldsList = visibleFieldsList.map(field => ({
          departmentId: field.departmentId,
          layoutId: field.layoutId,
          fieldNames: field.fieldNames
        }));
    
        RNZDPortalSubmitTicket.setFieldsListTobeShown(fieldsList);
      } else {
        console.log("Field input types do not match")
      }
    },

    ZDCustomizedTicketField,
    ZDCustomizedTicketForm, 
    ZDVisibleTicketField
    

};