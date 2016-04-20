package uccleonapi

import grails.rest.*
import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
@Resource(uri= "/classrooms", readOnly= false, formats = ["json"])
class Classroom {
    String code

    @BindUsing({
        obj, source -> source["name"]?.capitalize()
    })
    String name
    Boolean airConditioned = true

    static constraints = {
        code blank: false, unique: true, validator: { code ->
            List<String> letterBuilding = ["B", "C", "D", "E", "K"]

            if (!(code[0] in letterBuilding) || !(code[1] in ["1", "2"])) {
                return "no.valid.code"
            }
        }
        name nullable: true
    }
}
