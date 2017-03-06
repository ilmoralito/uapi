package uccleonapi.thirdParty

import grails.transaction.Transactional
import uccleonapi.ThirdParty

@Transactional
class ThirdPartyService {

    List<ThirdParty> getAllByEnabled(final Boolean enabled = true) {
        ThirdParty.findAllByEnabled enabled
    }
}
