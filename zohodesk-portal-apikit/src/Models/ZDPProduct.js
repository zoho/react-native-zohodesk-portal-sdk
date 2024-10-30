export class ZDPProduct{
    constructor(departmentIDS,productName,productDescription,productCode,id){
        this.departmentIDS = departmentIDS || null
        this.productName = productName
        this.productDescription = productDescription || null
        this.productCode = productCode || null
        this.id = id
    }

    static fromJSON(data) {
        return new ZDPProduct(
            data.departmentIds,
            data.productName,
            data.description,
            data.productCode,
            data.id
        )
    }
}