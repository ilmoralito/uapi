package uccleonapi

class UrlMappings {

    static mappings = {
        '/classrooms' (resources: 'classroom') {
            collection {
                '/searchByName' controller:'classroom', action:'searchByName'
                '/getByCodeLetter' controller: 'classroom', action: 'getByCodeLetter'
            }
        }

        '/coordinations' (resources: 'coordination') {
            collection {
                '/searchByName' controller: 'coordination', action: 'searchByName'
                '/employeesGroupedByCoordination' controller: 'coordination', action: 'employeesGroupedByCoordination'
            }
        }

        '/employees' (resources: 'employee') {
            collection {
                '/coordinationsPrintQuota' controller: 'employee', action: 'coordinationsPrintQuota'
                '/getEmployeeByInstitutionalMail' controller: 'employee', action: 'getEmployeeByInstitutionalMail'
            }
        }

        '/copies' (resources: 'copy') {
            collection {
                '/copyStatus' controller: 'copy', action: 'copyStatus'
                '/saveExtraCopy' controller: 'copy', action: 'saveExtraCopy'
                '/balanceByCoordination' controller: 'copy', action: 'balanceByCoordination'
                '/copiesToDateByEmployee' controller: 'copy', action: 'copiesToDateByEmployee'
            }
        }

        '/' controller: 'application', action: 'index'
        '500' view: '/error'
        '404' view: '/notFound'
    }
}
