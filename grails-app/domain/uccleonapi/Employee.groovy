package uccleonapi

import grails.rest.*
import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
@Resource(uri='/employees', readOnly= false, formats= ['json'])
class Employee {
     @BindUsing({ obj, source ->
        source['fullName']?.toLowerCase()?.tokenize(' ')*.capitalize().join(' ')
    })
    String fullName
    String institutionalMail
    String authority
    String identityCard
    String inss

    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
        institutionalMail email: true, unique: true, blank: false
        authority inList: ['Manager', 'Assistant'], maxSize: 255
        identityCard blank: false, unique: true
        inss blank: false, unique: true
    }

    static mapping = {
        version false
    }

    static belongsTo = Coordination
    static hasMany = [coordinations: Coordination]
}
