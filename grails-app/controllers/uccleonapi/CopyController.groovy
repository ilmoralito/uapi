package uccleonapi

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.transaction.*
import grails.converters.*
import grails.rest.*

class CopyController extends RestfulController {
    EmployeeService employeeService
    CopyService copyService

    static responseFormats = ['json']
    static allowedMethods = [saveExtraCopy: 'POST']

    CopyController() {
        super(Copy)
    }

    @Override
    def show() {
        [copy: queryForResource(params.id)]
    }

    def copiesToDateByEmployee(Integer employeeID) {
        Employee employee = Employee.get(employeeID)

        if (employee) {
            respond copyService.copiesToDateByEmployee(employee)
        } else {
            render status: NOT_FOUND
        }
    }

    @Transactional
    def saveExtraCopy(ExtraCopy extraCopy) {
        if (extraCopy.hasErrors()) {
            respond extraCopy.errors
        } else {
            extraCopy.save flush: true

            withFormat {
                '*' { render status: CREATED }
            }
        }
    }

    def copyStatus() {
        Employee employee = Employee.get(params.int('employeeID'))

        if (!employee) {
            render status: 404
        } else {
            List printQuota = employeeService.printQuota(employee)
            List copiesToDate = copyService.copiesToDate(employee)

            respond printQuota.collect { l1 ->
                Integer copies = copiesToDate.find { it.name == l1.name }?.copies ?: 0

                [
                    id: l1.id,
                    name: l1.name,
                    quota: l1.quota,
                    copiesToDate: copies,
                    balanceToDate: l1.quota - copies
                ]
            }.sort { a, b -> a.name <=> b.name }
        }
    }

    def balanceByCoordination(Integer coordinationID) {
        Coordination coordination = Coordination.get(coordinationID)

        if (coordination) {
            render copyService.balanceByCoordination(coordination)
        } else {
            render status: NOT_FOUND
        }
    }
}

