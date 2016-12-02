package uccleonapi

import grails.rest.*
import grails.converters.*
import grails.gorm.DetachedCriteria

class ClassroomController extends RestfulController {
    static responseFormats = ['json', 'xml']
    ClassroomController() {
        super(Classroom)
    }

    def getByCodeLetter(String letter) {
        if (letter) {
            DetachedCriteria query = Classroom.where {
                code ==~ "${letter.toUpperCase()}%"
            }

            respond query.list()
        } else {
            respond([])
        }
    }

    def searchByName(String name) { 
        if(name) {
            DetachedCriteria query = Classroom.where { 
                name ==~ "%${name}%"
            }

            respond query.list() 
        }
        else {
            respond([]) 
        }
    }
}
