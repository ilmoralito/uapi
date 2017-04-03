package uccleonapi

import grails.util.Environment

class CopyInterceptor {
    String origin = Environment.current == Environment.DEVELOPMENT ? 'http://localhost:8181' : 'http://192.168.7.254/platform-ucc'

    CopyInterceptor() {
        match controller: 'copy', action: 'getDocumentDescriptionByCoordination'
    }

    boolean before() {
        header 'Access-Control-Allow-Origin', origin
        header 'Access-Control-Allow-Credentials', 'true'
        header 'Access-Control-Allow-Methods', 'GET'
        header 'Access-Control-Max-Age', '3600'

        true
    }

    boolean after() { true }

    void afterView() { }
}
