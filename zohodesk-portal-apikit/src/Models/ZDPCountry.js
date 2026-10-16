export class ZDPCountry {
    constructor(name, countryCode, dialCode) {
        this.name = name;
        this.countryCode = countryCode;
        this.dialCode = dialCode;
    }

    // iOS sends the country name as `name`, Android as `country`
    static fromJSON(json) {
        return new ZDPCountry(
            json.name ?? json.country,
            json.countryCode,
            json.dialCode
        );
    }
}
