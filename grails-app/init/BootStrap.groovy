import uccleonapi.*
import grails.util.Environment
import grails.util.DomainBuilder

class BootStrap {
    def init = { servletContext ->
        if (Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.PRODUCTION) {
            production()
        }
    }

    private development() {
        DomainBuilder builder = new DomainBuilder()
        builder.classNameResolver = "uccleonapi"
        List<Classroom> classrooms = []
        List<Coordination> coordinations = []
        List<Employee> employees = []

        classrooms << builder.classroom(code: "B101", name: "Auditorio mayor", airConditioned: true)
        classrooms << builder.classroom(code: "B201", name: "Mesanini 1", airConditioned: true)
        classrooms << builder.classroom(code: "B202", name: "Mesanini 2", airConditioned: true)
        classrooms << builder.classroom(code: "C101", name: "Auditorio menor", airConditioned: true)
        classrooms << builder.classroom(code: "C102", name: "Desarrollo y proyeccion", airConditioned: true)
        classrooms << builder.classroom(code: "C103", airConditioned: false)
        classrooms << builder.classroom(code: "C104", airConditioned: false)

        classrooms.each { c ->
            c.save failOnError: true
        }

        coordinations << builder.coordination(
            name: "Soporte tecnico",
            extensionNumber: "129",
            location: "Administrative",
            id: "SOPORTETECNICO"
        )
        coordinations << builder.coordination(
            name: "Contabilidad",
            extensionNumber: "131",
            location: "Administrative",
            id: "CONTABILIDAD"
        )
        coordinations << builder.coordination(
            name: "Ciencias economicas",
            extensionNumber: "111",
            location: "Academic",
            id: "CCEE"
        )
        coordinations << builder.coordination(
            name: "Estudios por encuentro",
            extensionNumber: "115",
            location: "Academic",
            id: "FESE"
        )

        coordinations.each { coord ->
            coord.save failOnError: true
        }

        employees << builder.employee(
            fullName: "Sergio Lopez",
            institutionalMail: "sergio.lopez@ucc.edu.ni",
            position: "IT",
            authority: "Manager",
            identityCard: "291-290165-0001W",
            inss: "123456789"
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
            fullName: "Sadie Chavarria",
            institutionalMail: "sadie.chavarria@ucc.edu.ni",
            position: "CCEE",
            authority: "Assistant",
            identityCard: "281-290165-0001W",
            inss: "123456777"
        ) {
            coordination(refId: "CCEE")
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

        employees.each { e ->
            e.save failOnError: true
        }
    }

    def destroy = { }
}
