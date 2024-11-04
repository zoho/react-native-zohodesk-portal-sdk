export class Department {
    constructor(photoURL, name, deptDescription, id, nameInCustomerPortal, layoutCount) {
        this.photoURL = photoURL || null; 
        this.name = name;
        this.deptDescription = deptDescription || null;
        this.id = id;
        this.nameInCustomerPortal = nameInCustomerPortal;
        this.layoutCount = layoutCount;
    }

    
    static fromJSON(json) {
        return new Department(
            json.photoURL,
            json.name,
            json.deptDescription,
            json.id,
            json.nameInCustomerPortal,
            json.layoutCount
        );
    }
}