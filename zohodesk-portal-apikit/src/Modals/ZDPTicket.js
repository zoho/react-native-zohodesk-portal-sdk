import { ZDPProduct } from "@zohocorp/zohodesk-portal-apikit/src/Modals/ZDPProduct";
import { ZDPUser } from "@zohocorp/zohodesk-portal-apikit/src/Modals/ZDPTopic";

export class ZDPTicket {
    constructor(ticketNumber, modifiedTime, creator, subject, dueDate, departmentID, threadCount, channel, onholdTime, priority,
        closedTime, assignee, commentCount, createdTime, id, status, cf = {}, secondaryContacts = [],
        tickeDescription, resolution, responseDueDate, modifiedBy, email, productID,
        contactID, classification, language, teamID, layoutDetails, phone, category, subCategory, account, product,hasBlueprint = false) {
        this.ticketNumber = ticketNumber;
        this.modifiedTime = modifiedTime || null;
        this.creator = creator;
        this.subject = subject;
        this.dueDate = dueDate || null;
        this.departmentID = departmentID;
        this.threadCount = threadCount;
        this.channel = channel || null;
        this.onholdTime = onholdTime || null; 
        this.priority = priority || null;
        this.closedTime = closedTime || null;
        this.assignee = assignee || null;
        this.commentCount = commentCount;
        this.createdTime = createdTime;
        this.id = id;
        this.status = status;
        this.cf = cf || null;
        this.secondaryContacts = secondaryContacts || null;
        this.tickeDescription = tickeDescription || null;
        this.resolution = resolution || null;
        this.responseDueDate = responseDueDate || null;
        this.modifiedBy = modifiedBy || null;
        this.email = email || null;
        this.productID = productID || null;
        this.contactID = contactID || null;
        this.classification = classification || null;
        this.language = language || null;
        this.teamID = teamID || null;
        this.layoutDetails = layoutDetails || null;
        this.phone = phone || null;
        this.category = category || null;
        this.subCategory = subCategory || null;
        this.account = account || null;
        this.product = product || null;
        this.hasBlueprint = hasBlueprint;
    }

   
    static fromJSON(data) {
        return new ZDPTicket(
            data.ticketNumber,
            data.modifiedTime,
            data.creator ? ZDPUser.fromJSON(data.creator) : null,
            data.subject,
            data.dueDate,
            data.departmentId,
            data.threadCount,
            data.channel,
            data.onholdTime,
            data.priority,
            data.closedTime,
            data.assignee ? ZDPUser.fromJSON(data.creator) : null,
            data.commentCount,
            data.createdTime,
            data.id,
            data.status,
            data.cf,
            data.secondaryContacts,
            data.tickeDescription,
            data.resolution,
            data.responseDueDate,
            data.modifiedBy,
            data.email,
            data.productId,
            data.contactId,
            data.classification,
            data.language,
            data.teamId,
            data.layoutDetails ? LayoutDetails.fromJSON(data.layoutDetails) : null,
            data.phone,
            data.category,
            data.subCategory,
            data.account ? ZDPAccount.fromJSON(data.account) : null,
            data.product ? ZDPProduct.fromJSON(data.product) : null,
            data.hasBlueprint
        )
    
    }
}

class LayoutDetails{
    constructor(id,layoutName){
        this.id = id;
        this.layoutName = layoutName;
    }

    static fromJSON(data){
        return new LayoutDetails(
            data.id,
            data.layoutName
        )
    }
}

class ZDPAccount{
    constructor(id,name,mappingType ){
        this.id = id;
        this.name = name;
        this.mappingType = mappingType || null
    }

    static fromJSON(data){
        return new ZDPAccount(
            data.id,
            data.name,
            data.mappingType
        )
    }
}