export class ZDPUser {
   
    constructor(photoURL, photoUrl, name, id, type, label) {
        this.photoURL = photoURL || null
        this.photoUrl = photoUrl || null
        this.name = name || null
        this.id = id || null
        this.type = type || null
        this.label = label || null
    }

    static fromJSON(data) {
        return new ZDPUser(
            data.photoURL,
            data.photoUrl,
            data.name,
            data.id,
            data.type,
            data.label ? ZDPLabel.fromJSON(data.label) : null
        )
    }
}

class ZDPLabel{
    constructor(name,logoURL,id,labelPreferences){
        this.name = name || null
        this.logoURL = logoURL || null
        this.id = id || null
        this.preferences = labelPreferences || null
    }

    static fromJSON(data){
        return new ZDPLabel(
            data.name,
            data.logoUrl,
            data.id,
            data.preferences ? LabelPreferences.fromJSON(data.preferences) : null
        )
    }
}
  
class LabelPreferences{
    constructor(textColor,shape, bgColor){
        this.textColor = textColor || null
        this.shape = shape ||  null
        this.bgColor = bgColor || null
    }

    static fromJSON(data){
        return new LabelPreferences(
            data.textColor,
            data.shape,
            data.bgColor
        )
    }
}

export class ZDPAttachment {

    constructor(id, name, size, downloadURL) {
        this.id = id
        this.name = name
        this.size = size
        this.downloadURL = downloadURL || null
    }

    static fromJSON(data){
        return new ZDPAttachment(
            data.id,
            data.name,
            data.size,
            data.downloadUrl
        )
    }
}
  
class ZDPTag {
    constructor(name,permalink) {
        self.name = name
        self.permalink = permalink
    }

    static fromJSON(data){
        return ZDPTag(
            data.name,
            data.permalink
        )
    }
}
  
class ZDPCTicket {
    constructor(number, id) {
        this.number = number
        this.id = id
    }

    static fromJSON(data){
        return ZDPCTicket(
            data.number,
            data.id
        )
    }
}
  
export class ZDPTopic {
    constructor(id, subject, content, creator, status, label, permalink, type, createdTime,
                commentCount, likeCount, isVoted, categoryID, lastCommenter,latestCommentTime, webUrl,bestCommentId,
                attachments,viewCount,followersCount,isFollowing, isSticky, isDraft, isLocked, notifyMe, ticket, tags){
      this.id = id;
      this.subject = subject;
      this.content = content;
      this.creator = creator; 
      this.status = status;
      this.label = label;
      this.permalink = permalink;
      this.type = type;
      this.createdTime = createdTime;
      this.commentCount = commentCount || null;
      this.likeCount = likeCount || null;
      this.isVoted = isVoted || false; 
      this.categoryID = categoryID;
      this.lastCommenter = lastCommenter || null; 
      this.latestCommentTime = latestCommentTime || null;
      this.webUrl = webUrl || null;
      this.bestCommentId = bestCommentId || null;
      this.attachments = attachments || null; 
      this.viewCount = viewCount || null;
      this.followersCount = followersCount || null;
      this.isFollowing = isFollowing || false;
      this.isSticky = isSticky || false; 
      this.isDraft = isDraft || false
      this.isLocked = isLocked || false;  
      this.notifyMe = notifyMe || false;
      this.ticket = ticket || null; 
      this.tags = tags || null; 
    }

  
    static fromJSON(data) {
      return new ZDPTopic(
        data.id,
        data.subject,
        data.content,
        ZDPUser.fromJSON(data.creator),
        data.status,
        data.label,
        data.permalink,
        data.type,
        data.createdTime,
        data.commentCount,
        data.likeCount,
        data.isVoted,
        data.categoryId,
        data.lastCommenter ? ZDPUser.fromJSON(data.lastCommenter) : null,
        data.latestCommentTime,
        data.webUrl,
        data.bestCommentId,
        (data.attachments || []).map(attachment => ZDPAttachment.fromJSON(attachment)),
        data.viewCount,
        data.followersCount,
        data.isFollowing,
        data.isSticky,
        data.isDraft,
        data.isLocked,
        data.notifyMe,
        data.tags ? ZDPTag.fromJSON(data.tags) : [],
        data.ticket ? ZDPCTicket.fromJSON(data.ticket) : null,
      );
    }
}
  