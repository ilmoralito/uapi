package uccleonapi

import grails.rest.*

@Resource(readOnly = false, formats = ['json'])
class ThirdPartyEmployee {
    String fullName
    String email
    Boolean enabled = true

    static constrints = {
        fullName blank: false
        email blank: false, email: true, unique: true
    }

    static belongsTo = [thirdParty: ThirdParty]

    static mapping = {
        version false
        table 'thirdpartyemployees'
    }
}
