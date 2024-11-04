export class TicketField{
    constructor(displayLabel,apiName, isReadOnly, isCustomField, i18NLabel, name, id, isVisible, type, maxLength, isMandatory,
         allowedValues, dependency,defaultValue, toolTip,toolTipType, sortBy, statusMapping,restoreOnReplyValues, roundingPrecision,
          decimalPlaces, roundingOption, uploadAttachment,captchaURL,fieldName,isPHI,isEncrypted,isNested){
        this.displayLabel = displayLabel || null
        this.apiName = apiName || null
        this.isReadOnly = isReadOnly || null
        this.isCustomField = isCustomField || null
        this.i18NLabel = i18NLabel || null
        this.name = name || null
        this.id = id
        this.isVisible = isVisible
        this.type = type
        this.maxLength = maxLength
        this.isMandatory = isMandatory
        this.allowedValues = allowedValues || null
        this.dependency = dependency || null
        this.defaultValue = defaultValue || null
        this.toolTip = toolTip || null
        this.toolTipType = toolTipType || null
        this.sortBy = sortBy || null
        this.statusMapping = statusMapping || null
        this.restoreOnReplyValues = restoreOnReplyValues || null
        this.roundingPrecision = roundingPrecision || null
        this.decimalPlaces = decimalPlaces || null
        this.roundingOption = roundingOption || null
        this.uploadAttachment = uploadAttachment || null
        this.captchaURL = captchaURL || null
        this.fieldName = fieldName || null
        this.isPHI = isPHI ?? false
        this.isEncryptedField = isEncrypted ?? false
        this.isNested = isNested ?? false
    }

    static fromJSON(data){
        return new TicketField(
            data.displayLabel,
            data.apiName,
            data.isReadOnly,
            data.isCustomField,
            data.i18NLabel,
            data.name,
            data.id,
            data.isVisible,
            data.type,
            data.maxLength,
            data.isMandatory,
            data.allowedValues,
            data.dependency,
            data.defaultValue,
            data.toolTip,
            data.toolTipType,
            data.sortBy,
            data.statusMapping ? data.statusMapping.map(map => StatusMap.fromJSON(map)) : null,
            data.restoreOnReplyValues,
            data.roundingPrecision,
            data.decimalPlaces,
            data.roundingOption,
            data.uploadAttachment,
            data.captchaURL,
            data.fieldName,
            data.isPHI,
            data.isEncryptedField ,
            data.isNested,
        )
    }
}

class StatusMap {
    constructor(mappingValue, name) {
        this.mappingValue = mappingValue;
        this.name = name;
    }

    static fromJSON(json) {
        return new StatusMap(json.mappingValue, json.name);
    }
}