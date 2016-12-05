package uccleonapi

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class Coordination {
    @BindUsing({
        obj, source -> source['name']?.capitalize()
    })
    String name
    String extensionNumber
    String location
    Integer printQuota

    static constraints = {
        name blank: false, unique: true
        extensionNumber blank: false
        location inList: ['Administrative', 'Academic'], maxSize: 150
        printQuota blank: false, min: 1
        colors nullable: true
    }

    static mapping = {
        sort 'name'
        version false
        colors cascade: 'all-delete-orphan'
    }

    static hasMany = [colors: Color, employees: Employee]
}
