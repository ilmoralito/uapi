package uccleonapi

import grails.transaction.Transactional
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class EmployeeService {

    List<Map> printQuota(final Employee employee) {
        employee.coordinations.collect { coordination ->
            [
                id: coordination.id,
                name: coordination.name,
                quota: coordination.printQuota
            ]
        }
    }
}
