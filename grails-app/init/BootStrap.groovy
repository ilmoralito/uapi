import uccleonapi.*
import grails.util.Environment

class BootStrap {
    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.current == Environment.PRODUCTION) {
            production()
        }
    }

    private void development() {
        // COLORS
        Color red = Color.findOrSaveByName('Rojo')
        Color gray = Color.findOrSaveByName('Gris')
        Color green = Color.findOrSaveByName('Verde')
        Color orange = Color.findOrSaveByName('Naranja')
        Color institutional = Color.findOrSaveByName('Institucional')

        // COORDINATIONS
        Coordination protocol = new Coordination(
            printQuota: 100,
            name: 'Protocolo',
            extensionNumber: '117',
            location: 'Administrative'
        )

        protocol
            .addToColors(red)
            .addToColors(gray)
            .addToColors(green)
            .addToColors(orange)
            .addToColors(institutional)

        protocol.save failOnError: true

        Coordination technicalSupport = new Coordination(
            printQuota: 50,
            extensionNumber: '129',
            name: 'Soporte tecnico',
            location: 'Administrative'
        )

        technicalSupport.save failOnError: true

        Coordination administration = new Coordination(
            printQuota: 200,
            name: 'Administracion',
            extensionNumber: '132',
            location: 'Administrative'
        )

        administration.save failOnError: true

        Coordination sport = new Coordination(
            printQuota: 100,
            name: 'Deportes',
            extensionNumber: '117',
            location: 'Administrative'
        )

        sport.save failOnError: true

        // EMPLOYEES
        Employee protocolManager = new Employee(
            inss: '1234567885',
            authority: 'Manager',
            fullName: 'Orlando Gaitan',
            identityCard: '291-300287-0001W',
            institutionalMail: 'orlando.gaitan@ucc.edu.ni'
        )

        protocolManager.addToCoordinations(protocol)

        protocolManager.save failOnError: true

        Employee technicalSupportAssistant = new Employee(
            inss: '1234567891',
            authority: 'Assistant',
            fullName: 'Mario Martinez',
            identityCard: '291-290129-0001W',
            institutionalMail: 'mario.martinez@ucc.edu.ni'
        )

        technicalSupportAssistant.addToCoordinations(technicalSupport)

        technicalSupportAssistant.save failOnError: true

        Employee administrationManager = new Employee(
            inss: '1234567892',
            authority: 'Manager',
            fullName: 'Jorge Rojas',
            identityCard: '291-011170-0001W',
            institutionalMail: 'jorge.rojas@ucc.edu.ni'
        )

        administrationManager.addToCoordinations(administration)

        administrationManager.save failOnError: true

        Employee sportManager = new Employee(
            inss: '1234567800',
            authority: 'Manager',
            fullName: 'Roberto Nesme',
            identityCard: '281-291229-0001W',
            institutionalMail: 'roberto.nesme@ucc.edu.ni'
        )

        sportManager.addToCoordinations(sport)

        sportManager.save failOnError: true

        Employee administrationAssistant = new Employee(
            fullName: 'Cristina Rojas',
            institutionalMail: 'cristina.rojas@ucc.edu.ni',
            authority: 'Assistant',
            identityCard: '291-291130-0001W',
            inss: '1234567893'
        )

        administrationAssistant.addToCoordinations(administration)

        administrationAssistant.save failOnError: true

        // Classrooms
        new Classroom(
            code: 'C101',
            name: 'Auditorio menor',
            capacity: 200,
            airConditioned: true
        ).save failOnError: true

        new Classroom(
            code: 'D109',
            name: 'Sala de audiovisuales',
            capacity: 100,
            airConditioned: true
        ).save failOnError: true

        new Classroom(
            code: 'C106',
            capacity: 45
        ).save failOnError: true

        new Classroom(
            code: 'E112',
            capacity: 112,
            airConditioned: true
        ).save failOnError: true
    }

    private void production() {
        // COLORS
        Color red = Color.findOrSaveByName('Rojo')
        Color gray = Color.findOrSaveByName('Gris')
        Color green = Color.findOrSaveByName('Verde')
        Color orange = Color.findOrSaveByName('Naranja')
        Color institutional = Color.findOrSaveByName('Institucional')

        // COORDINATIONS
        Coordination protocol = Coordination.findByName('Protocolo')
        if (!protocol) {
            protocol = new Coordination(
                name: 'Protocolo',
                extensionNumber: '117',
                location: 'Administrative',
                printQuota: 100
            )

            protocol
                .addToColors(green)
                .addToColors(orange)
                .addToColors(gray)
                .addToColors(red)
                .addToColors(institutional)
                .save failOnError: true
        }

        Coordination administration = Coordination.findByName('Administracion')
        if (!administration) {
            administration = new Coordination(
                name: 'Administracion',
                extensionNumber: '132',
                location: 'Administrative',
                printQuota: 200
            ).save failOnError: true
        }

        Coordination technicalSupport = Coordination.findByName('Soporte tecnico')
        if (!technicalSupport) {
            technicalSupport = new Coordination(
                name: 'Soporte tecnico',
                extensionNumber: '129',
                location: 'Administrative',
                printQuota: 50
            ).save failOnError: true
        }

        Coordination vrg = Coordination.findByName('Vice rectoria general')
        if (!vrg) {
            vrg = new Coordination(
                name: 'Vice rectoria general',
                extensionNumber: '109',
                location: 'Academic',
                printQuota: 300
            ).save failOnError: true
        }

        Coordination academicDirection = Coordination.findByName('Direccion academica')
        if (!academicDirection) {
            academicDirection = new Coordination(
                name: 'Direccion academica',
                extensionNumber: '127',
                location: 'Academic',
                printQuota: 200
            ).save failOnError: true
        }

        Coordination sports = Coordination.findByName('Deportes')
        if (!sports) {
            sports = new Coordination(
                name: 'Deportes',
                extensionNumber: '117',
                location: 'Administrative',
                printQuota: 100
            ).save failOnError: true
        }

        Coordination project = Coordination.findByName('Proyecto')
        if (!project) {
            project = new Coordination(
                name: 'Proyecto',
                extensionNumber: '126',
                location: 'Administrative',
                printQuota: 100
            ).save failOnError: true
        }

        Coordination salesCenter = Coordination.findByName('Comisariato')
        if (!salesCenter) {
            salesCenter = new Coordination(
                name: 'Comisariato',
                extensionNumber: '124',
                location: 'Administrative',
                printQuota: 50
            ).save failOnError: true
        }

        Coordination transportation = Coordination.findByName('Transporte')
        if (!transportation) {
            transportation = new Coordination(
                name: 'Transporte',
                extensionNumber: '133',
                location: 'Administrative',
                printQuota: 100
            ).save failOnError: true
        }

        Coordination specialization = Coordination.findByName('Especializacion')
        if (!specialization) {
            specialization = new Coordination(
                name: 'Especializacion',
                extensionNumber: '112',
                location: 'Academic',
                printQuota: 500
            ).save failOnError: true
        }

        Coordination academicSecretary = Coordination.findByName('Registro academico')
        if (!academicSecretary) {
            academicSecretary = new Coordination(
                name: 'Registro academico',
                extensionNumber: '119',
                location: 'Academic',
                printQuota: 500
            ).save failOnError: true
        }

        // EMPLOYEES
        Employee technicalSupportAssistant = Employee.findByInstitutionalMail('mario.martinez@ucc.edu.ni')
        if (!technicalSupportAssistant) {
            technicalSupportAssistant = new Employee(
                fullName: 'Mario Martinez',
                institutionalMail: 'mario.martinez@ucc.edu.ni',
                authority: 'Assistant',
                identityCard: '291-290129-0001W',
                inss: '1234567891'
            ).addToCoordinations(technicalSupport).save failOnError: true
        }

        Employee administrationManager = Employee.findByInstitutionalMail('jorge.rojas@ucc.edu.ni')
        if (!administrationManager) {
            administrationManager = new Employee(
                fullName: 'Jorge Rojas',
                institutionalMail: 'jorge.rojas@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '291-011170-0001W',
                inss: '1234567892'
            ).addToCoordinations(administration).save failOnError: true
        }

        Employee administrationAssistant = Employee.findByInstitutionalMail('cristina.rojas@ucc.edu.ni')
        if (!administrationAssistant) {
            administrationAssistant = new Employee(
                fullName: 'Cristina Rojas',
                institutionalMail: 'cristina.rojas@ucc.edu.ni',
                authority: 'Assistant',
                identityCard: '291-291130-0001W',
                inss: '1234567893'
            ).addToCoordinations(administration).save failOnError: true
        }

        Employee academicaDirectionManager = Employee.findByInstitutionalMail('rosita.mollineda@ucc.edu.ni')
        if (!academicaDirectionManager) {
            academicaDirectionManager = new Employee(
                fullName: 'Rosita Mollineda',
                institutionalMail: 'rosita.mollineda@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '291-291128-0001W',
                inss: '1234567894'
            ).addToCoordinations(academicDirection).save failOnError: true
        }

        Employee protocolManager = Employee.findByInstitutionalMail('orlando.gaitan@ucc.edu.ni')
        if (!protocolManager) {
            protocolManager = new Employee(
                fullName: 'Orlando Gaitan',
                institutionalMail: 'orlando.gaitan@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '291-300287-0001W',
                inss: '1234567885'
            ).addToCoordinations(protocol).save failOnError: true
        }

        Employee protocolAssistant1 = Employee.findByInstitutionalMail('williams.juarez@ucc.edu.ni')
        if (!protocolAssistant1) {
            protocolAssistant1 = new Employee(
                fullName: 'Williams Juarez',
                institutionalMail: 'williams.juarez@ucc.edu.ni',
                authority: 'Assistant',
                identityCard: '291-121170-0001W',
                inss: '1234567895'
            ).addToCoordinations(protocol).save failOnError: true
        }

        Employee protocolAssistant2 = Employee.findByInstitutionalMail('neyling.carrero@ucc.edu.ni')
        if (!protocolAssistant2) {
            protocolAssistant2 = new Employee(
                fullName: 'Neyling Carrero',
                institutionalMail: 'neyling.carrero@ucc.edu.ni',
                authority: 'Assistant',
                identityCard: '291-191170-0001W',
                inss: '1234567896'
            ).addToCoordinations(protocol).save failOnError: true
        }

        Employee vrgManager = Employee.findByInstitutionalMail('nejama.narvaez@ucc.edu.ni')
        if (!vrgManager) {
            vrgManager = new Employee(
                fullName: 'Nejama Narvaez',
                institutionalMail: 'nejama.narvaez@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '291-101080-0001W',
                inss: '1234567897'
            ).addToCoordinations(vrg).save failOnError: true
        }

        Employee sportsManager = Employee.findByInstitutionalMail('roberto.nesme@ucc.edu.ni')
        if (!sportsManager) {
            sportsManager = new Employee(
                fullName: 'Roberto Nesme',
                institutionalMail: 'roberto.nesme@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '281-291229-0001W',
                inss: '1234567800'
            ).addToCoordinations(sports).save failOnError: true
        }

        Employee apicolaManager = Employee.findByInstitutionalMail('martin.cisneros@ucc.edu.ni')
        if (!apicolaManager) {
            apicolaManager = new Employee(
                fullName: 'Martin Cisneros',
                institutionalMail: 'martin.cisneros@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '281-121229-0001W',
                inss: '1234560000'
            ).addToCoordinations(project).save failOnError: true
        }

        Employee salesCenterManager = Employee.findByInstitutionalMail('leyla.carmendia@ucc.edu.ni')
        if (!salesCenterManager) {
            salesCenterManager = new Employee(
                fullName: 'Leyla Garmendia',
                institutionalMail: 'leyla.carmendia@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '271-121289-0001W',
                inss: '1234000000'
            ).addToCoordinations(salesCenter).save failOnError: true
        }

        Employee transportationManager = Employee.findByInstitutionalMail('cristiano.gutierrez@ucc.edu.ni')
        if (!transportationManager) {
            transportationManager = new Employee(
                fullName: 'Cristiano Gutierrez',
                institutionalMail: 'cristiano.gutierrez@ucc.edu.ni',
                authority: 'Manager',
                identityCard: '271-111189-0001W',
                inss: '1134000000'
            ).addToCoordinations(transportation).save failOnError: true
        }

        Employee assistantSpecialization = Employee.findByInstitutionalMail('yendri.iglesias@ucc.edu.ni')
        if (!assistantSpecialization) {
            assistantSpecialization = new Employee(
                fullName: 'Yendri Iglesias',
                institutionalMail: 'yendri.iglesias@ucc.edu.ni',
                authority: 'Assistant',
                identityCard: '291-290180-0001X',
                inss: '1134001400'
            )

            assistantSpecialization.addToCoordinations(specialization)
            assistantSpecialization.save failOnError: true
        }

        Employee internSpecialization = Employee.findByInstitutionalMail('boanerge.moody@ucc.edu.ni')
        if (!internSpecialization) {
            internSpecialization = new Employee(
                fullName: 'Boanerge Moody',
                institutionalMail: 'boanerge.moody@ucc.edu.ni',
                authority: 'Assistant',
                identityCard: '291-290180-0001Y',
                inss: '1134004180'
            )

            internSpecialization.addToCoordinations(specialization)
            internSpecialization.save failOnError: true
        }
    }

    def destroy = {}
}
