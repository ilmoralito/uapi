package uccleonapi

class ConstraintsUtils {
    static Closure AreCopiesValid() {
        { copies, obj, errors ->
            Long balance = obj.copyService.balanceByCoordination(obj.coordination)
            String copyType = obj in ExtraCopy ? 'extraCopy' : 'copy'
            Long scope = balance - copies

            if (copyType == 'copy') {
                if (copies >= 1 && balance > 0 && scope >= 0) {
                    return false
                }
            } else {
                if (copies >= 1 && scope < 0) {
                    return false
                }
            }
        }
    }

    static Closure authorizedByCannotBeNull() {
        { authorizedBy, obj ->
            if (obj.status == Status.AUTHORIZED) {
                if (authorizedBy == null) {
                    return false
                }
            }
        }
    }

    static Closure canceledByCannotBeNull() {
        { canceledBy, obj ->
            if (obj.status == Status.CANCELED) {
                if (canceledBy == null) {
                    return false
                }
            }
        }
    }

    static Closure reasonForCancellationCannotBeNull() {
        { reasonForCancellation, obj ->
            if (obj.status == Status.CANCELED) {
                if (reasonForCancellation == null) {
                    return false
                }
            }
        }
    }
}
