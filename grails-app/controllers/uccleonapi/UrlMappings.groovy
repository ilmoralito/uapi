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
                '/getAllByStatus' controller: 'copy', action: 'getAllByStatus'
                '/balanceByCoordination' controller: 'copy', action: 'balanceByCoordination'
                '/copiesToDateByEmployee' controller: 'copy', action: 'copiesToDateByEmployee'
                '/getAllNotifiedOrAuthorized' controller: 'copy', action: 'getAllNotifiedOrAuthorized'
                '/getAllRequestingAuthorization' controller: 'copy', action: 'getAllRequestingAuthorization'
                '/filterNotifiedOrAuthorized' controller: 'copy', action: 'filterNotifiedOrAuthorized'
                '/filter' controller: 'copy', action: 'filter'
                '/yearList' controller: 'copy', action: 'yearList'
                '/report' controller: 'copy', action: 'report'
                '/getAllOutOfRange' controller: 'copy', action: 'getAllOutOfRange'
                '/generalCopyStatus' controller: 'copy', action: 'generalCopyStatus'
                '/getDocumentDescriptionByCoordination' controller: 'copy', action: 'getDocumentDescriptionByCoordination'
                '/summaryByEmployee' controller: 'copy', action: 'summaryByEmployee'
                '/summaryByCoordinationAndYear' controller: 'copy', action: 'summaryByCoordinationAndYear'
                '/summaryByCoordinationAndYearAndMonth' controller: 'copy', action: 'summaryByCoordinationAndYearAndMonth'
            }
        }

        '/thirdparties' (resources: 'thirdParty') {
            '/thirdpartyemployees' (resources: 'thirdPartyEmployee')
            collection {
                '/getAllByEnabled' controller: 'thirdParty', action: 'getAllByEnabled'
            }
        }

        '/' controller: 'application', action: 'index'
        '500' view: '/error'
        '404' view: '/notFound'
    }
}
