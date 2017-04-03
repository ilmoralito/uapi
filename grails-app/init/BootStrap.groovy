import uccleonapi.*
import grails.util.Environment

class BootStrap {
    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.current == Environment.PRODUCTION) {
            // production()
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

        protocol.save flush: true

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

        Coordination coordinationOfAgronomy = new Coordination(
            printQuota: 250,
            name: 'Coordinacion de agronomia',
            extensionNumber: '118',
            location: 'Academic'
        )

        coordinationOfAgronomy.save failOnError: true

        Coordination architecturalAndCivilCoordination = new Coordination(
            printQuota: 300,
            name: 'Coordinacion de arquitectura y civil',
            extensionNumber: '118',
            location: 'Academic'
        )

        architecturalAndCivilCoordination.save failOnError: true

        // EMPLOYEES
        Employee protocolManager = new Employee(
            inss: '1234567885',
            authority: 'Manager',
            fullName: 'Orlando Gaitan',
            identityCard: '291-300287-0001W',
            institutionalMail: 'orlando.gaitan@ucc.edu.ni'
        )

        protocolManager.addToCoordinations(protocol)

        protocolManager.save flush: true

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

        Employee assistantAgronomyAndArchitecture = new Employee(
            fullName: 'Sadie Chavarria',
            institutionalMail: 'sadie.chavarria@ucc.edu.ni',
            authority: 'Assistant',
            identityCard: '291-280228-0001W',
            inss: '7414859600'
        )

        assistantAgronomyAndArchitecture
            .addToCoordinations(coordinationOfAgronomy)
            .addToCoordinations(architecturalAndCivilCoordination)
            .save failOnError: true

        Employee coordinatorOfAgronomy = new Employee(
            fullName: 'Juan Mairena',
            institutionalMail: 'juan.mairena@ucc.edu.ni',
            authority: 'Manager',
            identityCard: '291-280328-0001W',
            inss: '7414859617'
        )

        coordinatorOfAgronomy
            .addToCoordinations(coordinationOfAgronomy)
            .save failOnError: true

        Employee architecturalAndCivilCoordinator = new Employee(
            fullName: 'Cesar Balladares',
            institutionalMail: 'cesar.balladares@ucc.edu.ni',
            authority: 'Manager',
            identityCard: '291-300328-0001W',
            inss: '7414858717'
        )

        architecturalAndCivilCoordinator
            .addToCoordinations(architecturalAndCivilCoordination)
            .save failOnError: true

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

        // COPIES
        Date now = new Date()

        new Copy(
            documentDescription: 'Lorem ipsum dolor sit ament',
            copies: 15,
            coordination: protocol,
            employee: protocolManager,
            dateNotified: now
        ).save flush: true

        new Copy(
            documentDescription: 'Lorem ipsum dolor sit ament',
            copies: 20,
            coordination: protocol,
            employee: protocolManager,
            status: Status.ATTENDED,
            fateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'For this class, we will use the Cloud Foundry running on Pivotal Web Services. You must have an account to continue',
            copies: 5,
            coordination: protocol,
            employee: protocolManager,
            status: Status.ATTENDED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'Compare CF public services',
            copies: 6,
            coordination: coordinationOfAgronomy,
            employee: assistantAgronomyAndArchitecture,
            status: Status.ATTENDED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'Compare CF public services vs Heroku',
            copies: 10,
            coordination: coordinationOfAgronomy,
            employee: assistantAgronomyAndArchitecture,
            status: Status.ATTENDED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'Compare CF public services vs Heroku',
            copies: 15,
            coordination: architecturalAndCivilCoordination,
            employee: assistantAgronomyAndArchitecture,
            status: Status.ATTENDED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'Please review the requirement below and complete the following prerequisites before class',
            copies: 8,
            coordination: coordinationOfAgronomy,
            employee: coordinatorOfAgronomy,
            status: Status.NOTIFIED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'This training is for people with',
            copies: 15,
            coordination: architecturalAndCivilCoordination,
            employee: architecturalAndCivilCoordinator,
            status: Status.NOTIFIED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'This training is for people with corage',
            copies: 30,
            coordination: architecturalAndCivilCoordination,
            employee: architecturalAndCivilCoordinator,
            status: Status.ATTENDED,
            dateNotified: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'This training is for people with corage',
            copies: 18,
            coordination: architecturalAndCivilCoordination,
            employee: architecturalAndCivilCoordinator,
            status: Status.ATTENDED,
            dateNotified: now
        ).save failOnError: true

        new ExtraCopy(
            documentDescription: 'Document description',
            copies: 120,
            coordination: protocol,
            employee: protocolManager,
            status: Status.AUTHORIZED,
            dateNotified: now,
            description: 'For example, in the previous listing, if you were to define grails-app/views/index.gson and grails-app/views/index.gsp views, these would be used if the client requested application/json or text/html media types respectively. Thus allowing.',
            authorizedBy: administrationManager,
            dateAuthorized: now
        ).save failOnError: true

        new Copy(
            documentDescription: 'Some copy document can goes here',
            copies: 5,
            coordination: architecturalAndCivilCoordination,
            employee: architecturalAndCivilCoordinator,
            status: Status.ATTENDED,
            dateNotified: now,
        ).save failOnError: true

        new ExtraCopy(
            documentDescription: 'Another document description',
            copies: 200,
            coordination: protocol,
            employee: protocolManager,
            status: Status.REQUEST_AUTHORIZATION,
            dateNotified: now,
            description: 'Some other description goes here'
        ).save failOnError: true

        new ExtraCopy(
            documentDescription: 'Controlling the Priority of Media Types',
            copies: 500,
            coordination: protocol,
            employee: protocolManager,
            status: Status.REQUEST_AUTHORIZATION,
            dateNotified: now,
            description: 'For example, in the previous listing, if you were to define grails-app/views/index.gson and grails-app/views/index.gsp views, these would be used if the client requested application/json or text/html media types respectively. Thus allowing you to define a single backend capible of serving responses to a web browser or representing your application’s API'
        ).save failOnError: true

        new ExtraCopy(
            documentDescription: 'Using Views to Output JSON Responses',
            copies: 600,
            coordination: protocol,
            employee: protocolManager,
            status: Status.CANCELED,
            dateNotified: now,
            dateCanceled: now,
            canceledBy: administrationManager,
            reasonForCancellation: 'Unlike the JsonBuilder class which creates a data structure in memory, which is handy in those situations where you want to alter the structure programatically before output, the StreamingJsonBuilder streams to a writer directly without any memory data structure. So if you don\'t need to modify the structure, and want a more memory-efficient approach, please use the StreamingJsonBuilder',
            description: 'If you define a view (either a GSP or a JSON View) then Grails will render the view when using the respond method by calculating a model from the argument passed to respond'
        ).save failOnError: true

        // THIRD PARTY
        new ThirdParty(name: 'Fotocopias Leoncito')
            .addToThirdPartyEmployees(new ThirdPartyEmployee(fullName: 'Emma Hernandez', email: 'fotocopiasleoncito@hotmail.com'))
            .addToThirdPartyEmployees(new ThirdPartyEmployee(fullName: 'Leon Hernandez', email: 'leon@hotmail.com'))
            .save failOnError: true
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
