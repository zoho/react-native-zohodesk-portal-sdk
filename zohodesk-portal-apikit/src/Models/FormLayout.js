export class FormLayout{
    constructor(departmentID, hasLogo, id, isDefaultLayout, isStandardLayout, layoutDesc, layoutName, module) {
        this.departmentID = departmentID;
        this.hasLogo = hasLogo;
        this.id = id;
        this.isDefaultLayout = isDefaultLayout;
        this.isStandardLayout = isStandardLayout;
        this.layoutDesc = layoutDesc;
        this.layoutName = layoutName;
        this.module = module;
    }

    static fromJSON(json) {
        return new FormLayout(
            json.departmentID,
            json.hasLogo,
            json.id,
            json.isDefaultLayout,
            json.isStandardLayout,
            json.layoutDesc,
            json.layoutName,
            json.module
        );
    }
}
