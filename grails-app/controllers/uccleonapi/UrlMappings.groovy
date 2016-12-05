package uccleonapi

class UrlMappings {

    static mappings = {
        '/classrooms'(resources: 'classroom') {
            collection {
                '/getByCodeLetter' (controller: 'classroom', action: 'getByCodeLetter')
                '/searchByName' (controller:'classroom', action:'searchByName')
            }
        }

        '/coordinations'(resources: 'coordination')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        '/' controller: 'application', action:'index'
        '500' view: '/error'
        '404' view: '/notFound'
    }
}
