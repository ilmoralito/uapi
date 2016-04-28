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
    String color

    static constraints = {
        name blank: false, unique: true
        extensionNumber blank: false
        location inList: ["Administrative", "Academic"], maxSize: 150
        color nullable: true, validator: { color, coordination ->
            if (coordination.location == "Academic") {
                color != ""
            }
        }
    }
}
