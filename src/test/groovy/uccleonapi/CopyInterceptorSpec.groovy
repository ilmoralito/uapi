package uccleonapi


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CopyInterceptor)
class CopyInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test copy interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"copy")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
