package uccleonapi

import org.hibernate.criterion.CriteriaSpecification
import grails.transaction.Transactional
import grails.gorm.DetachedCriteria

@Transactional
class CopyService {
    private final Calendar calendar = Calendar.instance
    private final Integer lastDayOfMonth = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)

    private final List<Date> getFromAndToDate() {
        Date from = new Date().clearTime()
        Date to = from.clone()

        from.set date: 1
        to.set date: lastDayOfMonth

        [from, to]
    }

    List<Map> copiesToDate(final Employee employee) {
        def(from, to) = getFromAndToDate()

        List result = Copy.createCriteria().list {
            ne 'status', Status.CANCELED
            'in' 'coordination', employee.coordinations
            between 'dateCreated', from, to
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections {
                sum 'copies', 'copies'
                groupProperty 'coordination', 'coordination'
            }
        }

        result.collect { instance ->
            [name: instance.coordination.name, copies: instance.copies]
        }
    }

    List copiesToDateByEmployee(final Employee employee) {
        def (from, to) = getFromAndToDate()

        Copy.createCriteria().list {
            eq 'employee', employee
            ne 'status', Status.ATTENDED
            between 'dateCreated', from, to
        }.groupBy { it.coordination }.collect {
            [
                coordination: it.key.name,
                copies: it.value.collect { copy ->
                    [
                        id: copy.id,
                        copies: copy.copies,
                        status: copy.status.name(),
                        documentDescription: copy.documentDescription,
                        copyType: copy in ExtraCopy ? 'extraCopy' : 'copy'
                    ]
                }
            ]
        }
    }

    Long copiesToDateByCoordination(final Coordination coordination) {
        Date from = new Date().clearTime()
        Date to = from.clone()

        from.set date: 1
        to.set date: lastDayOfMonth

        Copy.createCriteria().get {
            ne 'status', Status.CANCELED
            eq 'coordination', coordination
            between 'dateCreated', from, to

            projections {
                sum 'copies'
            }
        } ?: 0
    }

    Long balanceByCoordination(final Coordination coordination) {
        coordination.printQuota - this.copiesToDateByCoordination(coordination)
    }

    List<Copy> getAllNotifiedOrAuthorized() {
        query().list()
    }

    List<ExtraCopy> getAllRequestingAuthorization() {
        Copy.where { status == Status.REQUEST_AUTHORIZATION }.list()
    }

    List<Copy> getAllByStatus(final List<String> statusList) {
        Copy.where {
            status in statusList.collect { it as Status }
        }.list()
    }

    List<Copy> filterNotifiedOrAuthorized(final List<Coordination> coordinationList = [], final List<Employee> employeeList = []) {
        Copy.createCriteria().list {
            or {
                eq 'status', Status.NOTIFIED
                eq 'status', Status.AUTHORIZED
            }

            if (coordinationList) {
                'in' 'coordination', coordinationList
            }

            if (employeeList) {
                'in' 'employee', employeeList
            }
        }
    }

    List<Copy> filter(List<Coordination> coordinationList = [], List<Employee> employeeList = [], List<String> copyStatusList = []) {
        Copy.createCriteria().list {
            if (coordinationList) {
                'in' 'coordination', coordinationList
            }

            if (employeeList) {
                'in' 'employee', employeeList
            }

            // if (authorizedByList) {
            //     'in' 'authorizedBy', authorizedByList
            // }

            // if (canceledByList) {
            //     'in' 'canceledBy', canceledByList
            // }

            if (copyStatusList) {
                'in' 'status', copyStatusList
            }
        }
    }

    List<Copy> report(final Integer year, final Integer month) {
        Date date = new Date().clearTime()
        Date from = date.clone()
        Date to = date.clone()

        from.set year: year, month: month, date: 1
        to.set year: year, month: month, date: 31

        Copy.where { dateCreated in from..to && status == Status.ATTENDED }.list()
    }

    private DetachedCriteria query() {
        Copy.where { status == 'NOTIFIED' || status == 'AUTHORIZED' }
    }
}
