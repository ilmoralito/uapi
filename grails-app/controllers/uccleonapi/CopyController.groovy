package uccleonapi

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import uccleonapi.commands.CopyStatusCommand
import static java.util.Calendar.*
import org.hibernate.Criteria
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

    def generalCopyStatus() {
        respond Coordination.list().collect { coordination ->
            [
                name: coordination.name,
                quota: coordination.printQuota,
                copiesToDate: copyService.copiesToDateByCoordination(coordination),
                balanceToDate: coordination.printQuota - copyService.copiesToDateByCoordination(coordination)
            ]
        }.sort { a, b -> a.coordination <=> b.coordination }
    }

    def balanceByCoordination(Integer coordinationID) {
        Coordination coordination = Coordination.get(coordinationID)

        if (coordination) {
            render copyService.balanceByCoordination(coordination)
        } else {
            render status: NOT_FOUND
        }
    }

    def getAllNotifiedOrAuthorized() {
        respond copyService.getAllNotifiedOrAuthorized(), view: 'index'
    }

    def getAllRequestingAuthorization() {
        List<ExtraCopy> extraCopyList = copyService.getAllRequestingAuthorization()

        render extraCopyList.collect { extraCopy ->
            [
                id: extraCopy.id,
                employee: [fullName: extraCopy.employee.fullName],
                coordination: [name: extraCopy.coordination.name],
                documentDescription: extraCopy.documentDescription,
                copies: extraCopy.copies,
                dateCreated: extraCopy.dateCreated.format('yyyy-MM-dd HH:mm')
            ]
        } as JSON
    }

    def getAllByStatus(CopyStatusCommand command) {
        respond copyService.getAllByStatus(command.statusList), view: 'index'
    }

    def getAllOutOfRange(CopyStatusCommand command) {
        // TODO: This is working but its dificult to reason in table
        // respond copyService.getAllByStatus(command.statusList).groupBy { it.coordination } { it.employee }.collect { coordination ->
        //     [
        //         coordination: coordination.key.name,
        //         employees: coordination.value.collect { employee ->
        //             [
        //                 employee: employee.key.fullName,
        //                 copyList: employee.value.collect { copy ->
        //                     [
        //                         documentoDescription: copy.documentDescription,
        //                         copies: copy.copies,
        //                         status: copy.status,
        //                         dateCreated: copy.dateCreated
        //                     ]
        //                 }
        //             ]
        //         }
        //     ]
        // }

        respond copyService.getAllByStatus(command.statusList), view: 'index'
    }

    def filterNotifiedOrAuthorized(FilterCommand command) {
        respond copyService.filterNotifiedOrAuthorized(command.coordinationList, command.employeeList), view: 'index'
    }

    def filter(FilterCommand command) {
        respond copyService.filter(command.coordinationList, command.employeeList, command.copyStatusList), view: 'index'
    }

    def yearList() {
        respond Copy.executeQuery('SELECT DISTINCT YEAR(c.dateCreated) AS year FROM Copy AS c')
    }

    def report(Integer year, Integer month) {
        List<Copy> copyList = copyService.report(year ?: new Date()[YEAR], month ?: new Date()[MONTH])
        List report = copyList.groupBy { it.coordination.location } { it.coordination }.collect { root ->
            [
                location: root.key,
                totalCopies: copyList.findAll { !(it in ExtraCopy) && it.coordination.location == root.key }.copies.sum() ?: 0,
                totalExtraCopies: copyList.findAll { it in ExtraCopy && it.coordination.location == root.key }.copies.sum() ?: 0,
                totalCopiesInMonth: copyList.findAll { it.coordination.location == root.key }.copies.sum(),
                coordinations: root.value.collect {
                    [
                        name: it.key.name,
                        copyFee: it.key.printQuota,
                        copies: it.value.findAll { !(it in ExtraCopy) }.copies.sum() ?: 0,
                        extraCopies: it.value.findAll { it in ExtraCopy }.copies.sum() ?: 0,
                        totalCopies: it.value.copies.sum(),
                    ]
                }.sort { a, b -> a.name <=> b.name }
            ]
        }.sort { a, b -> a.location <=> b.location }

        respond report
    }

    def getDocumentDescriptionByCoordination(Integer coordinationID) {
        Coordination coordination = Coordination.get(coordinationID)

        if (!coordination) {
            render status: 404
        } else {
            respond Copy.executeQuery('''
                SELECT DISTINCT c.documentDescription
                FROM Copy AS c
                WHERE coordination = :coordination
                ORDER BY c.documentDescription ASC
            ''', [coordination: coordination])
        }
    }

    def summaryByEmployee(Integer employeeID) {
        Employee employee = Employee.get(employeeID)

        if (!employee) {
            render status: 404
        } else {
            List<Copy> copyList = Copy.where {
                coordination in employee.coordinations && status in ['ATTENDED', 'CANCELED'].collect {
                    status -> status as Status
                }
            }.list()

            if (copyList) {
                respond copyList.groupBy { it.coordination } { it.dateCreated[YEAR] }.collect { root ->
                    [
                        coordination: [ id: root.key.id, name: root.key.name ],
                        summary: root.value.collect {
                            [
                                year: it.key,
                                attended: it.value.findAll { it.status == Status.ATTENDED }.copies.sum() ?: 0,
                                canceled: it.value.findAll { it.status == Status.CANCELED }.copies.sum() ?: 0,
                                total: it.value.copies.sum()
                            ]
                        }
                    ]
                }
            } else {
                respond ([])
            }
        }
    }

    def summaryByCoordinationAndYear(Integer coordinationID, Integer year) {
        Coordination coordination = Coordination.get(coordinationID)

        if (!coordination) {
            render status: 404
        } else {
            Date date = new Date().clearTime()
            Date firstDayOfTheYear = date.clone()
            Date lastDayOfTheYear = date.clone()

            firstDayOfTheYear.set year: year, month: 0, date: 1
            lastDayOfTheYear.set year: year, month: 11, date: 31, hourOfDay: 23, minute: 59, second: 59

            List<Copy> copyList = Copy.where {
                coordination == coordination &&
                status in ['ATTENDED', 'CANCELED'].collect { status ->
                    status as Status
                } && dateCreated in firstDayOfTheYear..lastDayOfTheYear
            }.list()

            if (copyList) {
                Map metadata = [
                    coordination: [id: coordination.id, name: coordination.name],
                    year: year
                ]

                List summary = copyList.groupBy { it.dateCreated[MONTH] }.collect { root ->
                    [
                        months: (1..12).collect { month ->
                            [
                                month: month,
                                attended: root.value.findAll { root.key == month && it.status.toString() == 'ATTENDED' }.copies.sum() ?: 0,
                                canceled: root.value.findAll { root.key == month && it.status.toString() == 'CANCELED' }.copies.sum() ?: 0,
                                total: root.value.findAll { root.key == month }.copies.sum() ?: 0
                            ]
                        }
                    ]
                }

                metadata.summary = summary

                respond metadata
            } else {
                respond ([])
            }
        }
    }

    def summaryByCoordinationAndYearAndMonth(Integer coordinationID, Integer year, Integer month) {
        Coordination coordination = Coordination.get(coordinationID)

        if (!coordination) {
            render status: 404
        } else {
            Calendar calendar = Calendar.instance
            Integer lastDayOfMonth = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)
            Date date = new Date().clearTime()
            Date from = date.clone()
            Date to = date.clone()

            from.set year: year, month: month, date: 1
            to.set year: year, month: month, date: lastDayOfMonth, hourOfDay: 23, minute: 59

            List<Copy> copyList = Copy.createCriteria().list {
                eq 'coordination', coordination
                'in' 'status', ['ATTENDED', 'CANCELED'].collect { status ->
                    status as Status
                }
                between 'dateCreated', from, to
            }

            if (copyList) {
                Map metadata = [
                    metadata: [
                        coordination: coordination.name,
                        year: year,
                        month: month
                    ]
                ]

                List summary = copyList.groupBy { it.employee }.collect { root ->
                    [
                        requestedBy: root.key.fullName,
                        copyList: root.value.collect { copy ->
                            [
                                documentDescription: copy.documentDescription,
                                copies: copy.copies,
                                dateCreated: copy.dateCreated.format('yyyy-MM-dd HH:mm'),
                                status: copy.status.toString()
                            ]
                        }
                    ]
                }

                metadata['summary'] = summary

                respond metadata
            } else {
                respond ([])
            }
        }
    }
}

class FilterCommand {
    List<Coordination> coordinationList
    List<Employee> employeeList
    List<String> copyStatusList
}
