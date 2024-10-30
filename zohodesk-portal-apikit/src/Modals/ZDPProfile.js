export class ZDPProfile{

    constructor(displayName,facebook,mobile,timeZone,fullName,emailID,countryLocale,photoURL,twitter,phone,joiningTime,id){
        this.displayName = displayName
        this.facebook = facebook || null
        this.mobile = mobile || null
        this.timeZone = timeZone
        this.fullName = fullName
        this.emailID = emailID
        this.countryLocale = countryLocale
        this.photoURL = photoURL || null
        this.twitter = twitter || null
        this.phone = phone || null
        this.joiningTime = joiningTime || null
        this.id = id
    }
    
    static fromJSON(data){
        return new ZDPProfile(
            data.displayName,
            data.facebook,
            data.mobile,
            data.timeZone,
            data.fullName,
            data.emailId,
            data.countryLocale,
            data.photoURL,
            data.twitter,
            data.phone,
            data.joiningTime,
            data.id,
        )
    }
}