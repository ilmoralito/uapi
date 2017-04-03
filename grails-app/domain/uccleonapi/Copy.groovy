package uccleonapi

import groovy.transform.ToString

@ToString
class Copy {
    transient copyService
    static transients = ['copyService']

    PaperSize paperSize = PaperSize.LETTER
    ColorType colorType = ColorType.BLACK
    Status status = Status.NOTIFIED
    String documentDescription
    Coordination coordination
    Employee employee
    Date dateNotified
    Date dateAttended
    Date dateCreated
    Date lastUpdated
    Integer copies

    static constraints = {
        status blank: false
        colorType blank: false
        paperSize blank: false
        documentDescription blank: false
        dateNotified nullable: true
        dateAttended nullable: true
        copies min: 1 // validator: { copies, obj ->
            // Integer balanceToDate = obj.copyService.balanceByCoordination(obj.coordination)

            // if (!(obj in ExtraCopy)) {
            //     copies >= 1 && balanceToDate > 0
            // } else {
            //     copies >= 1 && balanceToDate <= 0
            // }

            // println obj.copyService.copiesToDateByCoordination(obj.coordination)
            // true
        // }
    }

    static mapping = {
        table 'copies'
        version false
        cache true
    }

    def afterUpdate() {
        Date now = new Date()

        switch(status) {
            case Status.NOTIFIED:
                dateNotified = now
            break
            case Status.ATTENDED:
                dateAttended = now
            break
        }
    }
}

