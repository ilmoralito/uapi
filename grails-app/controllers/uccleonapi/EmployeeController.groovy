package uccleonapi

import grails.rest.*
import grails.converters.*
import grails.transaction.*
import grails.gorm.DetachedCriteria
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

            employee.save(flush: true)

            if (employee.hasErrors()) {
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

    @Transactional
    def update(UpdateEmployeeCommand command) {
        if (command.hasErrors()) {
            respond command.errors
        } else {
            Employee employee = Employee.get(command.id)

            if (employee) {
                employee.fullName = command.fullName
                employee.institutionalMail = command.institutionalMail
                employee.authority = command.authority
                employee.identityCard = command.identityCard
                employee.inss = command.inss

                employee.save(flush: true)

                List temp = []
                temp += employee.coordinations

                temp.each { coordination ->
                    coordination.removeFromEmployees(employee)
                }

                command.coordinations.each { coordinationName ->
                    Coordination coordination = Coordination.findByName(coordinationName)

                    coordination.addToEmployees(employee)
                }

                render status: OK
            }
        }
    }

    def getEmployeeByInstitutionalMail(final String institutionalMail) {
        if (institutionalMail) {
            DetachedCriteria query = Employee.where {
                institutionalMail == institutionalMail
            }

            respond query.get()
        } else {
            respond([])
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

class UpdateEmployeeCommand {
    Long id
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
