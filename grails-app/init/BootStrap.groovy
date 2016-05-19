import uccleonapi.*
import grails.util.Environment
import grails.util.DomainBuilder

class BootStrap {
    def grailsApplication

    def init = { servletContext ->
        if (Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.PRODUCTION) {
            production()
        }
    }

    private development() {
        ConfigObject config = grailsApplication.config.uccleonapi
        DomainBuilder builder = new DomainBuilder()
        List<Classroom> classrooms = []
        List<Coordination> coordinations = []
        List<Employee> employees = []

        builder.classNameResolver = "uccleonapi"

        classrooms << builder.classroom(code: "B101", name: "Auditorio mayor", capacity: 400, airConditioned: true)
        classrooms << builder.classroom(code: "B201", name: "Mesanini 1", capacity: 84, airConditioned: true)
        classrooms << builder.classroom(code: "B202", name: "Mesanini 2", capacity: 70, airConditioned: true)
        classrooms << builder.classroom(code: "C101", name: "Auditorio menor", capacity: 120, airConditioned: true)
        classrooms << builder.classroom(code: "C102", name: "Desarrollo y proyeccion", airConditioned: true)
        classrooms << builder.classroom(code: "C109A", capacity: 40, airConditioned: true)
        classrooms << builder.classroom(code: "E112", capacity: 100, airConditioned: true)
        classrooms << builder.classroom(code: "C205", capacity: 53, airConditioned: true)
        classrooms << builder.classroom(code: "D109",name: "Sala de proyeccion", capacity: 70, airConditioned: true)
        classrooms << builder.classroom(code: "C103")
        classrooms << builder.classroom(code: "C104")

        classrooms.each { c ->
            c.save failOnError: true
        }

        // COORDINATIONS
        coordinations << builder.coordination(
            name: "Administracion",
            extensionNumber: "230",
            location: "Administrative",
            id: "ADMINISTRACION"
        ) {
            color(name: "Verde institucional")
        }

        coordinations << builder.coordination(
            name: "Direccion academica",
            extensionNumber: "231",
            location: "Academic",
            id: "DIRECCION ACADEMICA"
        ) {
            config.institutionalColors.each { institutionalColor ->
                color(name: institutionalColor)
            }
        }

        coordinations << builder.coordination(
            name: "Protocolo",
            extensionNumber: "232",
            location: "Administrative",
            id: "PROTOCOLO"
        ) {
            config.institutionalColors.each { institutionalColor ->
                color(name: institutionalColor)
            }
        }

        coordinations << builder.coordination(
            name: "Arquitectura",
            extensionNumber: "245",
            location: "Academic",
            id: "ARQUITECTURA"
        ) {
            color(name: "Gris")
        }

        coordinations << builder.coordination(
            name: "Agronomia",
            extensionNumber: "245",
            location: "Academic",
            id: "AGRONOMIA"
        ) {
            color(name: "Verde limon")
        }

        coordinations << builder.coordination(
            name: "Soporte tecnico",
            extensionNumber: "129",
            location: "Administrative",
            id: "SOPORTETECNICO"
        ) {
            color(name: "Verde institucional")
        }

        coordinations << builder.coordination(
            name: "Contabilidad",
            extensionNumber: "131",
            location: "Administrative",
            id: "CONTABILIDAD"
        ) {
            color(name: "Verde institucional")
        }

        coordinations << builder.coordination(
            name: "Ciencias economicas",
            extensionNumber: "111",
            location: "Academic",
            id: "CCEE"
        ) {
            color(name: "Verde institucional")
        }

        coordinations << builder.coordination(
            name: "Estudios por encuentro",
            extensionNumber: "115",
            location: "Academic",
            id: "FESE"
        ) {
            config.institutionalColors[1..-1].each { institutionalColor ->
                color(name: institutionalColor)
            }
        }

        coordinations.each { coord ->
            coord.save failOnError: true
        }

        // EMPLOYESS
        employees << builder.employee(
            fullName: "Jorge Rojas",
            institutionalMail: "jorge.rojas@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290179-0001W",
            inss: "123456789"
        ) {
            coordination(refId: "ADMINISTRACION")
        }

        employees << builder.employee(
            fullName: "Rosa Leon",
            institutionalMail: "rosa.leon@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290178-0001W",
            inss: "123456778"
        ) {
            coordination(refId: "DIRECCION ACADEMICA")
        }

        employees << builder.employee(
            fullName: "Orlando Gaitan",
            institutionalMail: "orlando.gaitan@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-020272-0002W",
            inss: "987654321"
        ) {
            coordination(refId: "PROTOCOLO")
        }

        employees << builder.employee(
            fullName: "Cesar Valladares",
            institutionalMail: "cesar.valladares@ucc.edu.ni",
            position: "Coordinador",
            authority: "Manager",
            identityCard: "298-121269-0002W",
            inss: "189654789"
        ) {
            coordination(refId: "ARQUITECTURA")
        }

        employees << builder.employee(
            fullName: "Juan Mairena",
            institutionalMail: "juan.mairena@ucc.edu.ni",
            position: "Coordinador",
            authority: "Manager",
            identityCard: "298-121279-0002W",
            inss: "289654789"
        ) {
            coordination(refId: "AGRONOMIA")
        }

        employees << builder.employee(
            fullName: "Sadie Chavarria",
            institutionalMail: "sadie.chavarria@ucc.edu.ni",
            position: "Assistant",
            authority: "Assistant",
            identityCard: "298-121285-0002W",
            inss: "389654789"
        ) {
            coordination(refId: "AGRONOMIA")
        }

        employees << builder.employee(
            fullName: "Sergio Lopez",
            institutionalMail: "sergio.lopez@ucc.edu.ni",
            position: "IT",
            authority: "Manager",
            identityCard: "291-290165-0001W",
            inss: "432156789"
        ) {
            coordination(refId: "SOPORTETECNICO")
        }

        employees << builder.employee(
            fullName: "Mario Martinez",
            institutionalMail: "mario.martinez@ucc.edu.ni",
            position: "IT",
            authority: "Assistant",
            identityCard: "291-290180-0001W",
            inss: "123456798"
        ) {
            coordination(refId: "SOPORTETECNICO")
        }

        employees << builder.employee(
            fullName: "Marta Torres",
            institutionalMail: "martha.torres@ucc.edu.ni",
            position: "FESE",
            authority: "Manager",
            identityCard: "299-290180-0001W",
            inss: "123457898"
        ) {
            coordination(refId: "FESE")
        }

        // BIRTHDAYS
        employees << builder.employee(
            fullName: "Gustavo Castillo Espinoza",
            institutionalMail: "gustavo.castillo@ucc.edu.ni",
            position: "Supervidor",
            authority: "Manager",
            identityCard: "299-02051960-0001W",
            inss: "123457887"
        ) {
            coordination(refId: "FESE")
        }

        employees << builder.employee(
            fullName: "Francisco Suarez Gonzales",
            institutionalMail: "francisco.suares@ucc.edu.ni",
            position: "Profesor",
            authority: "Assistant",
            identityCard: "299-11051960-1111W",
            inss: "123411887"
        ) {
            coordination(refId: "FESE")
        }

        employees << builder.employee(
            fullName: "Pedro Antonio Salazar Zelaya",
            institutionalMail: "pedro.antonio@ucc.edu.ni",
            position: "Profesor",
            authority: "Assistant",
            identityCard: "299-11051960-1221W",
            inss: "123411227"
        ) {
            coordination(refId: "FESE")
        }

        employees << builder.employee(
            fullName: "Fatima Masias Carrion",
            institutionalMail: "fatima.masias@ucc.edu.ni",
            position: "Profesor",
            authority: "Assistant",
            identityCard: "299-13051960-0221W",
            inss: "123411027"
        ) {
            coordination(refId: "FESE")
        }

        employees.each { e ->
            e.save failOnError: true
        }
    }

    def destroy = { }
}
