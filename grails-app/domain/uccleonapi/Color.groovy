package uccleonapi

import groovy.transform.ToString

@ToString
class Color {
    String name

    static constraints = {
        name blank: false
    }

    static belongsTo = [coordination: Coordination]
}
