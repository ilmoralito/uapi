package uccleonapi.thirdParty

import grails.rest.*
import grails.converters.*
import uccleonapi.ThirdParty

class ThirdPartyController extends RestfulController {
    ThirdPartyService thirdPartyService

    static responseFormats = ['json']

    ThirdPartyController() {
        super(ThirdParty)
    }

    def getAllByEnabled(Boolean enabled) {
        respond thirdPartyService.getAllByEnabled(enabled), view: 'index'
    }
}
