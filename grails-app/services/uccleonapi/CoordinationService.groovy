package uccleonapi

import grails.transaction.Transactional

@Transactional
class CoordinationService {

    List employeesGroupedByCoordination() {
        Coordination.list().collect { coordination ->
            [
                id: coordination.id,
                coordination: coordination.name,
                employees: coordination.employees.collect { employee ->
                    [
                        id: employee.id,
                        fullName: employee.fullName,
                    ]
                }.sort { a, b -> a.fullName <=> b.fullName }
            ]
        }
    }
}

