import { TicketField } from "./TIcketField";

export class TicketForm {
    constructor(sections = []) {
        this.sections = sections;
    }

    // Method to decode from JSON
    static fromJSON(json) {
        const sections = json.sections.map(section => FormSection.fromJSON(section));
        return new TicketForm(sections);
    }
}

class FormSection {
    constructor(sectionName, fields = []) {
        this.sectionName = sectionName;
        this.fields = fields;
    }

    // Method to decode from JSON
    static fromJSON(json) {
        const fields = json.fields.map(field => TicketField.fromJSON(field));
        return new FormSection(json.sectionName, fields);
    }
}


