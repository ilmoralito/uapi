package uccleonapi

import grails.rest.*
import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
@Resource(uri="/coordinations", readOnly= false, formats= ["json"])
class Coordination {
    @BindUsing({
        obj, source -> source["name"]?.capitalize()
    })
    String name
    String extensionNumber
    String location

    static constraints = {
        name blank: false, unique: true
        extensionNumber blank: false
        location inList: ["Administrative", "Academic"], maxSize: 150
        colors nullable: true, validator: { colors, obj ->
            if (obj.location == "Academic") {
                colors != null
            }
        }
    }

    static mapping = {
        colors cascade: "all-delete-orphan"
    }

    static hasMany = [colors: Color, employees: Employee]
}
