package uccleonapi

import static java.util.Calendar.*
import grails.transaction.Transactional
import org.hibernate.criterion.CriteriaSpecification

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
        coordination.printQuota - copiesToDateByCoordination(coordination)
    }
}

