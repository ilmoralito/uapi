package uccleonapi

class ExtraCopy extends Copy {
    Employee authorizedBy
    Date dateAuthorized
    Employee canceledBy
    Date dateCanceled
    String reasonForCancellation
    String description

    static constraints = {
        authorizedBy nullable: true, validator: ConstraintsUtils.authorizedByCannotBeNull()
        dateAuthorized nullable: true
        canceledBy nullable: true, validator: ConstraintsUtils.canceledByCannotBeNull()
        dateCanceled nullable: true
        reasonForCancellation nullable: true, validator: ConstraintsUtils.reasonForCancellationCannotBeNull()
        description maxSize: 1500
    }

    static mapping = {
        reasonForCancellation type: 'text'
        description type: 'text'
    }

    def afterUpdate() {
        Date now = new Date()

        switch(status) {
            case Status.NOTIFIED:
                dateNotified = now
            break
            case Status.CANCELED:
                dateCanceled = now
            break
            case Status.AUTHORIZED:
                dateAuthorized = now
            break
        }
    }
}

