package uccleonapi

import groovy.transform.ToString

@ToString
class Copy {
    CopyService copyService

    static transients = ['copyService']

    PaperSize paperSize = PaperSize.LETTER
    ColorType colorType = ColorType.BLACK
    Status status = Status.PENDING
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
        copies validator: ConstraintsUtils.AreCopiesValid()
    }

    static mapping = {
        discriminator column: 'copyType'
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

