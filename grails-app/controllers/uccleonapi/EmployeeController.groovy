package uccleonapi

import grails.rest.*
import grails.converters.*
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class EmployeeController extends RestfulController {
    static responseFormats = ['json', 'xml']

    EmployeeController() {
        super(Employee)
    }

    @Transactional
    def save(EmployeeCommand command) {
        if (command.hasErrors()) {
            respond command.errors
        } else {
            Employee employee = new Employee(
                fullName: command.fullName,
                institutionalMail: command.institutionalMail,
                authority: command.authority,
                identityCard: command.identityCard,
                inss: command.inss
            )

            if (!employee.save()) {
                respond employee.errors
            } else {
                command.coordinations.each { coordination ->
                    Coordination coordinationInstance = Coordination.findByName(coordination)

                    if (coordinationInstance) {
                        coordinationInstance.addToEmployees(employee)
                    }

                    coordinationInstance.save()
                }

                render status: CREATED
            }
        }
    }
}

class EmployeeCommand {
    String inss
    String fullName
    String authority
    String identityCard
    String institutionalMail
    List<String> coordinations

    static constraints = {
        importFrom Employee
        coordinations nullable: false, minSize: 1
    }
}
