package uccleonapi

import grails.rest.*
import grails.converters.*
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class CoordinationController extends RestfulController {
    static responseFormats = ['json', 'xml']
    CoordinationController() {
        super(Coordination)
    }

    @Transactional
    def save(CoordinationCommand command) {
        if (command.hasErrors()) {
            respond command.errors
        }

        Coordination coordination = new Coordination(
            name: command.name,
            location: command.location,
            printQuota: command.printQuota,
            extensionNumber: command.extensionNumber
        )

        if (command.colors) {
            command.colors.each { color ->
                Color colorInstance = Color.get(color)

                coordination.addToColors(colorInstance)
            }
        }

        if (!coordination.save()) {
            respond coordination.errors
        } else {
            render status: CREATED
        }
    }

    def show(Coordination coordination) {
        respond coordination
    }

    @Transactional
    def update(Coordination coordination) {
        if (!coordination) {
            render status: NOT_FOUND
        } else {
            if (coordination.hasErrors()) {
                respond coordination.errors
            } else {
                coordination.save flush: true

                withFormat {
                    '*' { render status: OK }
                }
            }
        }
    }

    def searchByName(String name) {
        if (name) {
            Coordination coordination = Coordination.findByName(name)

            respond coordination
        } else {
            respond [:]
        }
    }
}

class CoordinationCommand {
    String name
    String location
    String extensionNumber
    Integer printQuota
    Set colors

    static constraints = {
        importFrom Coordination
    }
}

