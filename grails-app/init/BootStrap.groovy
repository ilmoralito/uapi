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
        testDataGORM()
    }

    private testDataGORM() {
        // CLASSROOMS
        List classrooms = [
            [code: "B101", name: "Auditorio mayor", capacity: 400, airConditioned: true],
            [code: "B201", name: "Mesanini 1", capacity: 84, airConditioned: true],
            [code: "B202", name: "Mesanini 2", capacity: 70, airConditioned: true],
            [code: "C101", name: "Auditorio menor", capacity: 120, airConditioned: true],
            [code: "C102", name: "Desarrollo y proyeccion", airConditioned: true],
            [code: "C109A", capacity: 40, airConditioned: true],
            [code: "E112", capacity: 100, airConditioned: true],
            [code: "C205", capacity: 53, airConditioned: true],
            [code: "D109",name: "Sala de proyeccion", capacity: 70, airConditioned: true],
            [code: "C103"],
            [code: "C104"]
        ]

        classrooms.each { classroom ->
            new Classroom(classroom).save failOnError: true
        }

        // COLORS
        Color verde = new Color(name: "verde").save failOnError: true
        Color naranja = new Color(name: "naranja").save failOnError: true
        Color gris = new Color(name: "gris").save failOnError: true
        Color rojo = new Color(name: "rojo").save failOnError: true
        Color institucional = new Color(name: "institucional").save failOnError: true

        // COORDINATIONS
        // ADMINISTRATIVE
        Coordination administracion = new Coordination(
            name: "Administracion",
            extensionNumber: "230",
            location: "Administrative"
        ).save failOnError: true

        Coordination protocolo = new Coordination(
            name: "Protocolo",
            extensionNumber: "232",
            location: "Administrative"
        ).save failOnError: true

        Coordination it = new Coordination(
            name: "Soporte tecnico",
            extensionNumber: "245",
            location: "Administrative"
        ).save failOnError: true

        Coordination contabilidad = new Coordination(
            name: "Contabilidad",
            extensionNumber: "222",
            location: "Administrative"
        ).save failOnError: true

        // ACADEMIC
        Coordination direccionAcademica = new Coordination(
            name: "Direccion academica",
            extensionNumber: "231",
            location: "Academic"
        )

        direccionAcademica
            .addToColors(verde)
            .addToColors(naranja)
            .addToColors(gris)
            .addToColors(rojo)
            .addToColors(institucional)

        direccionAcademica.save failOnError: true

        Coordination arquitectura = new Coordination(
            name: "Arquitectura",
            extensionNumber: "245",
            location: "Academic"
        )

        arquitectura.addToColors(gris).save failOnError: true

        Coordination agronomia = new Coordination(
            name: "Agronomia",
            extensionNumber: "245",
            location: "Academic"
        )

        agronomia.addToColors(gris).save failOnError: true

        Coordination ccee = new Coordination(
            name: "Ciencias economicas",
            extensionNumber: "288",
            location: "Academic"
        )

        ccee.addToColors(institucional).save failOnError: true

        Coordination fese = new Coordination(
            name: "Estudios por encuentro",
            extensionNumber: "299",
            location: "Academic"
        )

        fese
            .addToColors(verde)
            .addToColors(naranja)
            .addToColors(gris)
            .addToColors(rojo)
            .addToColors(institucional)
            .save failOnError: true

        // EMPLOYEES
        Employee jorgeRojas = new Employee(
            fullName: "Jorge Rojas",
            institutionalMail: "jorge.rojas@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290179-0001W",
            inss: "123456789",
            coordination: administracion,
        ).save failOnError: true

        Employee rositaMollineda = new Employee(
            fullName: "Rosita Mollineda",
            institutionalMail: "rosita.mollineda@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290160-0001W",
            inss: "123456788",
            coordination: direccionAcademica,
        ).save failOnError: true

        Employee orlandoGaitan = new Employee(
            fullName: "Orlando Gaitan",
            institutionalMail: "orlando.gaitan@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290188-0001W",
            inss: "123456688",
            coordination: protocolo,
        ).save failOnError: true

        Employee cesarValladares = new Employee(
            fullName: "Cesar Valladares",
            institutionalMail: "cesar.valladares@ucc.edu.ni",
            position: "Coordinador",
            authority: "Manager",
            identityCard: "298-121269-0002W",
            inss: "189654789",
            coordination: arquitectura
        ).save failOnError: true

        Employee juanMairena = new Employee(
            fullName: "Juan Mairena",
            institutionalMail: "juan.mairena@ucc.edu.ni",
            position: "Coordinador",
            authority: "Manager",
            identityCard: "298-121279-0002W",
            inss: "289654789",
            coordination: agronomia
        ).save failOnError: true

        Employee sadieChavarria = new Employee(
            fullName: "Sadie Chavarria",
            institutionalMail: "sadie.chavarria@ucc.edu.ni",
            position: "Assistant",
            authority: "Assistant",
            identityCard: "298-121285-0002W",
            inss: "389654789",
            coordination: agronomia
        ).save failOnError: true

        Employee sergioLopez = new Employee(
            fullName: "Sergio Lopez",
            institutionalMail: "sergio.lopez@ucc.edu.ni",
            position: "IT",
            authority: "Manager",
            identityCard: "291-290165-0001W",
            inss: "432156789",
            coordination: it
        ).save failOnError: true

        Employee marioMartinez = new Employee(
            fullName: "Mario Martinez",
            institutionalMail: "mario.martinez@ucc.edu.ni",
            position: "IT",
            authority: "Assistant",
            identityCard: "291-290180-0001W",
            inss: "123456798",
            coordination: it
        ).save failOnError: true

        Employee marthaTorres = new Employee(
            fullName: "Marta Torres",
            institutionalMail: "martha.torres@ucc.edu.ni",
            position: "FESE",
            authority: "Manager",
            identityCard: "299-290180-0001W",
            inss: "123457898",
            coordination: fese
        ).save failOnError: true

        Employee gustavoCastillo = new Employee(
            fullName: "Gustavo Castillo Espinoza",
            institutionalMail: "gustavo.castillo@ucc.edu.ni",
            position: "Supervidor",
            authority: "Manager",
            identityCard: "299-02051960-0001W",
            inss: "123457887",
            coordination: agronomia
        ).save failOnError: true
    }

    private testDataObjectGraphBuilder() {
        ConfigObject config = grailsApplication.config.uccleonapi
        DomainBuilder builder = new DomainBuilder()
        List<Classroom> classrooms = []
        List<Color> colors = []
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

        COLORS
        colors << builder.color(name: "Institucional", id: "institucional")
        colors << builder.color(name: "Verde limon", id: "verde")
        colors << builder.color(name: "Naranja", id: "naranja")
        colors << builder.color(name: "Gris", id: "gris")
        colors << builder.color(name: "Rojo vino", id: "rojo")

        colors.each { color ->
            color.save failOnError: true
        }

        COORDINATIONS
        coordinations << builder.coordination(
            name: "Administracion",
            extensionNumber: "230",
            location: "Administrative",
            id: "ADMINISTRACION"
        ) {
            color(refId: "institucional")
        }

        coordinations << builder.coordination(
            name: "Administracion",
            extensionNumber: "230",
            location: "Administrative",
            id: "ADMINISTRACION"
        ) {
            color(refId: "institucional")
        }

        coordinations.each { coordination ->
            coordination.save failOnError: true
        }

        coordinations << builder.coordination(
            name: "Direccion academica",
            extensionNumber: "231",
            location: "Academic",
            id: "DIRECCION ACADEMICA"
        ) {
            colors.each { c ->
                color(refId: c.id)
            }
        }

        coordinations << builder.coordination(
            name: "Protocolo",
            extensionNumber: "232",
            location: "Administrative",
            id: "PROTOCOLO"
        ) {
            colors.each { c ->
                color(refId: c.id)
            }
        }

        coordinations << builder.coordination(
            name: "Arquitectura",
            extensionNumber: "245",
            location: "Academic",
            id: "ARQUITECTURA"
        ) {
            color(refId: "gris")
        }

        coordinations << builder.coordination(
            name: "Agronomia",
            extensionNumber: "245",
            location: "Academic",
            id: "AGRONOMIA"
        ) {
            color(refId: "verde")
        }

        coordinations << builder.coordination(
            name: "Soporte tecnico",
            extensionNumber: "129",
            location: "Administrative",
            id: "SOPORTETECNICO"
        ) {
            color(refId: "verde")
        }

        coordinations << builder.coordination(
            name: "Contabilidad",
            extensionNumber: "131",
            location: "Administrative",
            id: "CONTABILIDAD"
        ) {
            color(refId: "institucional")
        }

        coordinations << builder.coordination(
            name: "Ciencias economicas",
            extensionNumber: "111",
            location: "Academic",
            id: "CCEE"
        ) {
            color(refId: "institucional")
        }

        coordinations << builder.coordination(
            name: "Estudios por encuentro",
            extensionNumber: "115",
            location: "Academic",
            id: "FESE"
        ) {
            color(refId: "verde")
            color(refId: "naranja")
            color(refId: "gris")
            color(refId: "rojo")
            color(refId: "institucional")
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
            fullName: "Rosita Leon",
            institutionalMail: "rosita.mollineda@ucc.edu.ni",
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
