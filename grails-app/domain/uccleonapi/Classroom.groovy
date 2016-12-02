package uccleonapi

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class Classroom {
    @BindUsing({
        obj, source -> source['code']?.toUpperCase()
    })
    String code

    @BindUsing({
        obj, source -> source['name']?.capitalize()
    })
    String name
    Integer capacity
    Boolean airConditioned = false

    static constraints = {
        code blank: false, unique: true, validator: { code ->
            List<String> buildingCode = ['B', 'C', 'D', 'E', 'K']
            String letter = code[0].toUpperCase()
            String floor = code[1]

            if (!(letter in buildingCode)) {
                return 'not.valid.letter.code'
            }

            if (!(floor in ['1', '2'])) {
                return 'not.valid.floor.number'
            }

            if (!(code.size() in [4, 5])) {
                return 'not.valid.code.size'
            }
        }
        name nullable: true
        capacity nullable: true, validator: { capacity ->
            if (capacity) {
                capacity > 0
            }
        }
    }
}
