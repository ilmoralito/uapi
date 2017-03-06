package uccleonapi

import grails.rest.*

@groovy.transform.ToString
@Resource(readOnly = false, formats = ['json'])
class ThirdParty {
    String name
    Boolean enabled = true
    List<ThirdPartyEmployee> thirdPartyEmployees

    static constraints = {
        name blank: false, unique: true
    }

    static hasMany = [thirdPartyEmployees: ThirdPartyEmployee]

    static mapping = {
        version false
        table 'thirdparties'
    }

    def beforeUpdate() {
        thirdPartyEmployees*.enabled = enabled
    }
}

