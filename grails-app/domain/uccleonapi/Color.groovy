package uccleonapi

import grails.rest.*

@Resource(uri= '/colors', readOnly= false, formats = ['json'])
class Color {
    String name

    static constraints = {
        name blank: false
    }

    static mapping = {
        version false
    }

    static belongsTo = Coordination
    static hasMany = [coordination: Coordination]
}
