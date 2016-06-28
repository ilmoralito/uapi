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

        // EMPLOYEES
        Employee jorgeRojas = new Employee(
            fullName: "Jorge Rojas",
            institutionalMail: "jorge.rojas@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290179-0001W",
            inss: "123456789"
        ).save failOnError: true

        Employee cristinaRojas = new Employee(
            fullName: "Cristina Rojas",
            institutionalMail: "cristina.rojas@ucc.edu.ni",
            position: "Assistant",
            authority: "Assistant",
            identityCard: "291-290280-0001W",
            inss: "789456541"
        ).save failOnError: true

        Employee rositaMollineda = new Employee(
            fullName: "Rosita Mollineda",
            institutionalMail: "rosita.mollineda@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290160-0001W",
            inss: "123456788"
        ).save failOnError: true

        Employee orlandoGaitan = new Employee(
            fullName: "Orlando Gaitan",
            institutionalMail: "orlando.gaitan@ucc.edu.ni",
            position: "Director",
            authority: "Manager",
            identityCard: "291-290188-0001W",
            inss: "123456688"
        ).save failOnError: true

        Employee cesarValladares = new Employee(
            fullName: "Cesar Valladares",
            institutionalMail: "cesar.valladares@ucc.edu.ni",
            position: "Coordinador",
            authority: "Manager",
            identityCard: "298-121269-0002W",
            inss: "189654789"
        ).save failOnError: true

        Employee juanMairena = new Employee(
            fullName: "Juan Mairena",
            institutionalMail: "juan.mairena@ucc.edu.ni",
            position: "Coordinador",
            authority: "Manager",
            identityCard: "298-121279-0002W",
            inss: "289654789"
        ).save failOnError: true

        Employee sadieChavarria = new Employee(
            fullName: "Sadie Chavarria",
            institutionalMail: "sadie.chavarria@ucc.edu.ni",
            position: "Assistant",
            authority: "Assistant",
            identityCard: "298-121285-0002W",
            inss: "389654789"
        ).save failOnError: true

        Employee sergioLopez = new Employee(
            fullName: "Sergio Lopez",
            institutionalMail: "sergio.lopez@ucc.edu.ni",
            position: "IT",
            authority: "Manager",
            identityCard: "291-290165-0001W",
            inss: "432156789"
        ).save failOnError: true

        Employee marioMartinez = new Employee(
            fullName: "Mario Martinez",
            institutionalMail: "mario.martinez@ucc.edu.ni",
            position: "IT",
            authority: "Assistant",
            identityCard: "291-290180-0001W",
            inss: "123456798"
        ).save failOnError: true

        Employee davidMartinez = new Employee(
            fullName: "David Martinez",
            institutionalMail: "david.martinez@ucc.edu.ni",
            position: "Manager",
            authority: "Manager",
            identityCard: "281-291180-0001W",
            inss: "12348888"

        ).save failOnError: true

        Employee marthaTorres = new Employee(
            fullName: "Marta Torres",
            institutionalMail: "martha.torres@ucc.edu.ni",
            position: "FESE",
            authority: "Manager",
            identityCard: "299-290180-0001W",
            inss: "123457898"
        ).save failOnError: true

        Employee gustavoCastillo = new Employee(
            fullName: "Gustavo Castillo Espinoza",
            institutionalMail: "gustavo.castillo@ucc.edu.ni",
            position: "Supervidor",
            authority: "Manager",
            identityCard: "299-02051960-0001W",
            inss: "123457887"
        ).save failOnError: true

        // COORDINATIONS
        // ADMINISTRATIVE
        Coordination administracion = new Coordination(
            name: "Administracion",
            extensionNumber: "230",
            location: "Administrative"
        )

        administracion
            .addToEmployees(jorgeRojas)
            .addToEmployees(cristinaRojas)
            .save failOnError: true

        Coordination protocolo = new Coordination(
            name: "Protocolo",
            extensionNumber: "232",
            location: "Administrative"
        )

        protocolo
            .addToEmployees(orlandoGaitan)
            .save failOnError: true

        Coordination it = new Coordination(
            name: "Soporte tecnico",
            extensionNumber: "245",
            location: "Administrative"
        )
            .addToEmployees(sergioLopez)
            .addToEmployees(marioMartinez)
            .save failOnError: true

        Coordination contabilidad = new Coordination(
            name: "Contabilidad",
            extensionNumber: "222",
            location: "Administrative"
        )

        contabilidad
            .addToEmployees(davidMartinez)
            .save failOnError: true

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
            .addToEmployees(rositaMollineda)
            .save failOnError: true

        Coordination arquitectura = new Coordination(
            name: "Arquitectura",
            extensionNumber: "245",
            location: "Academic"
        )

        arquitectura
            .addToColors(gris)
            .addToEmployees(cesarValladares)
            .addToEmployees(sadieChavarria)
            .save failOnError: true

        Coordination agronomia = new Coordination(
            name: "Agronomia",
            extensionNumber: "245",
            location: "Academic"
        )

        agronomia
            .addToColors(gris)
            .addToEmployees(juanMairena)
            .addToEmployees(sadieChavarria)
            .save failOnError: true

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
            .addToEmployees(marthaTorres)
            .save failOnError: true
    }

    def destroy = { }
}
