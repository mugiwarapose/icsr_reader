package com.hannover.icsr_reader.reader

import com.hannover.icsr_reader.entity.*
import org.dom4j.Element
import org.dom4j.io.SAXReader
import org.springframework.stereotype.Component
import java.io.File

@Component
class R2Reader {

    fun process(file: File) :List<R2Fields>{
        val document = SAXReader().read(file)
        val rootElement = document.rootElement

        val data = mutableListOf<R2Fields>()

        val header = rootElement.element("ichicsrmessageheader")
        val transmissionIdentification = readTransmissionIdentification(header)
        //读取report信息
        val reportXmlList = rootElement.elements("safetyreport")
        reportXmlList.forEach { ele ->
            val general = readerGeneral(ele)
            var primarySource = readPrimarySource(ele)
            val patientNode = ele.element("patient")
            var patient = readPatient(patientNode)
            val medicalHistoryEpisode = readMedicalHistoryEpisode(patientNode)
            val patientPastDrugTherapy = readPatientPastDrugTherapy(patientNode)
            val death = readDeath(patientNode)
            val parentNode = patientNode.element("parent")
            val parent = readParent(parentNode)
            val parentMedicalHistoryEpisode = readParentMedicalHistoryEpisode(parentNode)
            val parentPastDrugTherapy = readParentPastDrugTherapy(parentNode)
            val reactions = readReaction(patientNode)
            val tests = readTest(patientNode)
            val drugs = readDrug(patientNode)
            val summary = readSummary(patientNode.element("summary"))

            data.add(R2Fields(
                    transmissionIdentification = transmissionIdentification,
                    general = general,
                    primarySources = primarySource,
                    patient = patient,
                    diseaseHistory = medicalHistoryEpisode,
                    drugHistory = patientPastDrugTherapy,
                    death = death,
                    parent = parent,
                    parentDiseaseHistory = parentMedicalHistoryEpisode,
                    parentDrugHistory = parentPastDrugTherapy,
                    reactions = reactions,
                    tests = tests,
                    drugs = drugs,
                    summary = summary,
            ))
        }
        return data
    }

    fun readTransmissionIdentification(element: Element): TransMissionIdentification {
        val messageType = element.elementText("messagetype")
        val messageFormatVersion = element.elementText("messageformatversion")
        val messageFormatRelease = element.elementText("messageformatrelease")
        val messageNumb = element.elementText("messagenumb")
        val messageSenderIdentifier = element.elementText("messagesenderidentifier")
        val messageReceiverIdentifier = element.elementText("messagereceiveridentifier")
        val messageDateFormat = element.elementText("messagedateformat")
        val messageDate = element.elementText("messagedate")
        return TransMissionIdentification(
                messageType = messageType,
                messageFormatVersion = messageFormatVersion,
                messageFormatRelease = messageFormatRelease,
                messageNumb = messageNumb,
                messageSenderIdentifier = messageSenderIdentifier,
                messageReceiverIdentifier = messageReceiverIdentifier,
                messageDateFormat = messageDateFormat,
                messageDate = messageDate,
        )
    }

    fun readerGeneral(element: Element): GeneralR2 {

        val safetyReportVersion = element.elementText("safetyreportversion")
        val safetyReportId = element.elementText("safetyreportid")
        val primarySourceCountry = element.elementText("primarysourcecountry")
        val occurCountry = element.elementText("occurcountry")
        val transmissionDateFormat = element.elementText("transmissiondateformat")
        val transmissionDate = element.elementText("transmissiondate")
        val reportType = element.elementText("reporttype")
        val serious = element.elementText("serious")
        val seriousnessDeath = element.elementText("seriousnessdeath")
        val seriousnessLifeThreatening = element.elementText("seriousnesslifethreatening")
        val seriousnessHospitalization = element.elementText("seriousnesshospitalization")
        val seriousnessDisabling = element.elementText("seriousnessdisabling")
        val seriousnessCongenitalAnomali = element.elementText("seriousnesscongenitalanomali")
        val seriousnessOther = element.elementText("seriousnessother")
        val receiveDateFormat = element.elementText("receivedateformat")
        val receiveDate = element.elementText("receivedate")
        val receiptDateFormat = element.elementText("receiptdateformat")
        val receiptDate = element.elementText("receiptdate")
        val additionalDocument = element.elementText("additionaldocument")
        val documentList = element.elementText("documentlist")
        val fulfillExpediteCriteria = element.elementText("fulfillexpeditecriteria")
        val companyNumb = element.elementText("companynumb")
        val authorityNumb = element.elementText("authoritynumb")
        val duplicate = element.elementText("duplicate")
        val reportDuplicate = element.elements("reportduplicate").map {
            ReportDuplicate(
                    duplicateSource = it.elementText("duplicatesource"),
                    duplicateNumb = it.elementText("duplicatenumb"),
            )
        }
        val linkedReport = element.elements("linkedreport").map {
            it.elementText("linkreportnumb")
        }
        val caseNullification = element.elementText("caseNullification")
        val nullificationReason = element.elementText("caseNullification")
        val medicallyConfirm = element.elementText("caseNullification")

        return GeneralR2(
                safetyReportVersion = safetyReportVersion,
                safetyReportId = safetyReportId,
                primarySourceCountry = primarySourceCountry,
                occurCountry = occurCountry,
                transmissionDateFormat = transmissionDateFormat,
                transmissionDate = transmissionDate,
                reportType = reportType,
                serious = serious,
                seriousnessDeath = seriousnessDeath,
                seriousnessLifeThreatening = seriousnessLifeThreatening,
                seriousnessHospitalization = seriousnessHospitalization,
                seriousnessDisabling = seriousnessDisabling,
                seriousnessCongenitalAnomali = seriousnessCongenitalAnomali,
                seriousnessOther = seriousnessOther,
                receiveDateFormat = receiveDateFormat,
                receiveDate = receiveDate,
                receiptDateFormat = receiptDateFormat,
                receiptDate = receiptDate,
                additionalDocument = additionalDocument,
                documentList = documentList,
                fulfillExpediteCriteria = fulfillExpediteCriteria,
                companyNumb = companyNumb,
                authorityNumb = authorityNumb,
                duplicate = duplicate,
                reportDuplicate = reportDuplicate,
                linkedReport = linkedReport,
                caseNullification = caseNullification,
                nullificationReason = nullificationReason,
                medicallyConfirm = medicallyConfirm,
        )
    }

    fun readPrimarySource(element: Element): List<PrimarySource> {
        return element.elements("primarysource").map {
            PrimarySource(
                    reporterTitle = it.elementText("reportertitle"),
                    reporterGiveName = it.elementText("reportergivename"),
                    reporterMiddleName = it.elementText("reportermiddlename"),
                    reporterFamilyName = it.elementText("reporterfamilyname"),
                    reporterOrganization = it.elementText("reporterorganization"),
                    reporterDepartment = it.elementText("reporterdepartment"),
                    reporterCity = it.elementText("reportercity"),
                    reporterStreet = it.elementText("reporterstreet"),
                    reporterState = it.elementText("reporterstate"),
                    reporterPostCode = it.elementText("reporterpostcode"),
                    reporterCountry = it.elementText("reportercountry"),
                    qualification = it.elementText("qualification"),
                    literatureReference = it.elementText("literaturereference"),
                    studyName = it.elementText("studyname"),
                    sponsorStudyNumb = it.elementText("sponsorstudynumb"),
                    observeStudyType = it.elementText("observestudytype"),
            )
        }
    }


    fun readPatient(element: Element): PatientR2 {
        return PatientR2(
                patientInitial = element.elementText("patientinitial"),
                patientGpMedicalRecordNumb = element.elementText("patientgpmedicalrecordnumb"),
                patientSpecialistRecordNumb = element.elementText("patientspecialistrecordnumb"),
                patientHospitalRecordNumb = element.elementText("patienthospitalrecordnumb"),
                patientInvestigationNumb = element.elementText("patientinvestigationnumb"),
                patientBirthdateFormat = element.elementText("patientbirthdateformat"),
                patientBirthDate = element.elementText("patientbirthdate"),
                patientOnsetAge = element.elementText("patientonsetage"),
                patientOnsetAgeUnit = element.elementText("patientonsetageunit"),
                gestationPeriod = element.elementText("gestationperiod"),
                gestationPeriodUnit = element.elementText("gestationperiodunit"),
                patientAgeGroup = element.elementText("patientagegroup"),
                patientWeight = element.elementText("patientweight"),
                patientHeight = element.elementText("patientweight"),
                patientSex = element.elementText("patientsex"),
                lastMenstrualDateFormat = element.elementText("lastmenstrualdateformat"),
                patientLastMenstrualDate = element.elementText("patientlastmenstrualdate"),
                patientMedicalHistoryText = element.elementText("patientmedicalhistorytext"),
                resultsTestsProcedures = element.elementText("resultstestsprocedures"),
        )
    }

    fun readMedicalHistoryEpisode(element: Element): List<MedicalHistoryEpisode> {
        return element.elements("medicalhistoryepisode").map {
            MedicalHistoryEpisode(
                    patientEpisodeNameMeddraVersion = it.elementText("patientepisodenamemeddraversion"),
                    patientEpisodeName = it.elementText("patientepisodename"),
                    patientMedicalStartDateformat = it.elementText("patientmedicalstartdateformat"),
                    patientMedicalStartDate = it.elementText("patientmedicalstartdate"),
                    patientMedicalContinue = it.elementText("patientmedicalcontinue"),
                    patientMedicalEndDateFormat = it.elementText("patientmedicalenddateformat"),
                    patientMedicalEndDate = it.elementText("patientmedicalenddate"),
                    patientMedicalComment = it.elementText("patientmedicalcomment"),
            )
        }
    }

    fun readPatientPastDrugTherapy(element: Element): List<PatientPastDrugTherapy> {
        return element.elements("patientpastdrugtherapy").map {
            PatientPastDrugTherapy(
                    patientDrugName = it.elementText("patientdrugname"),
                    patientDrugStartDateFormat = it.elementText("patientdrugstartdateformat"),
                    patientDrugStartDate = it.elementText("patientdrugstartdate"),
                    patientDrugEndDateFormat = it.elementText("patientdrugenddateformat"),
                    patientDrugEndDate = it.elementText("patientdrugenddate"),
                    patientIndicationMeddraVersion = it.elementText("patientindicationmeddraversion"),
                    patientDrugIndication = it.elementText("patientdrugindication"),
                    patientDrugReactionMeddraVersion = it.elementText("patientdrgreactionmeddraversion"),
                    patientDrugReaction = it.elementText("patientdrugreaction"),
            )
        }
    }

    fun readDeath(element: Element?): PatientDeathR2 {
        val deathNode = element?.element("death")
        val patientDeathDateFormat = deathNode?.elementText("patientdeathdateformat")
        val patientDeathDate = deathNode?.elementText("patientdeathdate")
        val patientAutopsyYesNo = deathNode?.elementText("patientautopsyyesno")
        val cause = deathNode?.elements("patientdeathcause")?.map {
            DeathCause(
                    patientDeathReportMeddraVersion = it.elementText("patientdeathreportmeddraversion"),
                    patientDeathReport = it.elementText("patientdeathreport")
            )
        }?: listOf()
        val autopsy = deathNode?.elements("patientautopsy")?.map {
            Autopsy(
                    patientDetermineAutopsyMeddraVersion = it.elementText("patientdetermautopsmeddraversion"),
                    patientDetermineAutopsy = it.elementText("patientdetermineautopsy"),

                    )
        }?: listOf()
        return PatientDeathR2(
                patientDeathDateFormat = patientDeathDateFormat,
                patientDeathDate = patientDeathDate,
                patientAutopsyYesNo = patientAutopsyYesNo,
                cause = cause,
                autopsy = autopsy,
        )
    }

    fun readParent(element: Element?): ParentR2 {

        return ParentR2(
                parentIdentification = element?.elementText("parentidentification"),
                parentBirthDateFormat = element?.elementText("parentbirthdateformat"),
                parentBirthDate = element?.elementText("parentbirthdate"),
                parentAge = element?.elementText("parentage"),
                parentAgeUnit = element?.elementText("parentageunit"),
                parentLastMenstrualDateFormat = element?.elementText("parentlastmenstrualdateformat"),
                parentLastMenstrualDate = element?.elementText("parentlastmenstrualdate"),
                parentWeight = element?.elementText("parentweight"),
                parentHeight = element?.elementText("parentheight"),
                parentSex = element?.elementText("parentsex"),
                parentMedicalRelevantText = element?.elementText("parentmedicalrelevanttext"),
        )
    }

    fun readParentMedicalHistoryEpisode(element: Element?): List<ParentMedicalHistoryEpisode> {
        return element?.elements("patientpastdrugtherapy")?.map {
            ParentMedicalHistoryEpisode(
                    parentMdEpisodeMeddraVersion = it.elementText("parentmedicalhistoryepisode"),
                    parentMedicalEpisodeName = it.elementText("parentmedicalstartdateformat"),
                    parentMedicalStartDateFormat = it.elementText("parentmedicalstartdateformat"),
                    parentMedicalStartDate = it.elementText("parentmedicalstartdate"),
                    parentMedicalContinue = it.elementText("parentmedicalcontinue"),
                    parentMedicalEndDateFormat = it.elementText("parentmedicalenddateformat"),
                    parentMedicalEndDate = it.elementText("parentmedicalenddate"),
                    parentMedicalComment = it.elementText("parentmedicalcomment"),
            )
        }?: listOf()
    }

    fun readParentPastDrugTherapy(element: Element?): List<ParentPastDrugTherapy> {
        return element?.elements("patientpastdrugtherapy")?.map {
            ParentPastDrugTherapy(
                    parentDrugName = it.elementText("parentdrugname"),
                    parentDrugStartDateFormat = it.elementText("parentdrugstartdateformat"),
                    parentDrugStartDate = it.elementText("parentdrugstartdate"),
                    parentDrugEndDateFormat = it.elementText("parentdrugenddateformat"),
                    parentDrugEndDate = it.elementText("parentdrugenddate"),
                    parentDrugIndicationMeddraVersion = it.elementText("parentdrgindicationmeddraversion"),
                    parentDrugIndication = it.elementText("parentdrugindication"),
                    parentDrugReactionMeddraVersion = it.elementText("parentmedicalcomment"),
                    parentDrugReaction = it.elementText("parentdrugreaction"),
            )
        }?: listOf()
    }

    fun readReaction(element: Element): List<Reaction> {
        return element.elements("reaction").map {
            Reaction(
                    primarySourceReaction = it.elementText("primarysourcereaction"),
                    reactionMeddraVersionLlt = it.elementText("reactionmeddraversionllt"),
                    reactionMeddraLlt = it.elementText("reactionmeddrallt"),
                    reactionMeddraVersionPt = it.elementText("reactionmeddraversionpt"),
                    reactionMeddraPt = it.elementText("reactionmeddrapt"),
                    termHighLighted = it.elementText("termhighlighted"),
                    reactionSateFormat = it.elementText("reactionstartdateformat"),
                    reactionStartDate = it.elementText("reactionenddate"),
                    reactionEndDateFormat = it.elementText("reactionenddateformat"),
                    reactionEndDate = it.elementText("reactionenddate"),
                    reactionDuration = it.elementText("reactionduration"),
                    reactionDurationUnit = it.elementText("reactiondurationunit"),
                    reactionFirstTime = it.elementText("reactionfirsttime"),
                    reactionFirstTimeUnit = it.elementText("reactionfirsttimeunit"),
                    reactionLastTime = it.elementText("reactionlasttime"),
                    reactionLastTimeUnit = it.elementText("reactionlasttimeunit"),
                    reactionOutcome = it.elementText("reactionoutcome"),
            )
        }
    }

    fun readTest(element: Element): List<TestR2> {
        return element.elements("test").map {
            TestR2(
                    testDateFormat = it.elementText("testdateformat"),
                    testDate = it.elementText("testdate"),
                    testName = it.elementText("testname"),
                    testResult = it.elementText("testresult"),
                    testUnit = it.elementText("testunit"),
                    lowTestRange = it.elementText("lowtestrange"),
                    highTestRange = it.elementText("hightestrange"),
                    moreInformation = it.elementText("moreinformation"),
            )
        }
    }

    fun readDrug(element: Element): List<DrugR2> {
        val drugs = mutableListOf<DrugR2>()
        element.elements("drug").forEach {
            val drug = DrugR2(
                    drugCharacterization = it.elementText("drugcharacterization"),
                    medicinalProduct = it.elementText("medicinalproduct"),
                    obtainDrugCountry = it.elementText("obtaindrugcountry"),
                    drugBatchNumb = it.elementText("drugbatchnumb"),
                    drugAuthorizationNumb = it.elementText("drugauthorizationnumb"),
                    drugAuthorizationCountry = it.elementText("drugauthorizationcountry"),
                    drugAuthorizationHolder = it.elementText("drugauthorizationholder"),
                    drugStructureDosageNumb = it.elementText("drugstructuredosagenumb"),
                    drugStructureDosageUnit = it.elementText("drugstructuredosageunit"),
                    drugSeparateDosageNumb = it.elementText("drugseparatedosagenumb"),
                    drugIntervalDosageUnitNumb = it.elementText("drugintervaldosageunitnumb"),
                    drugIntervalDosageDefinition = it.elementText("drugintervaldosagedefinition"),
                    drugCumulativeDosageNumb = it.elementText("drugcumulativedosagenumb"),
                    drugCumulativeDosageUnit = it.elementText("drugcumulativedosageunit"),
                    drugDosageText = it.elementText("drugdosagetext"),
                    drugDosageForm = it.elementText("drugdosageform"),
                    drugAdministrationRoute = it.elementText("drugadministrationroute"),
                    drugParAdministration = it.elementText("drugparadministration"),
                    reactionGestationPeriod = it.elementText("reactiongestationperiod"),
                    reactionGestationPeriodUnit = it.elementText("reactiongestationperiodunit"),
                    drugIndicationMeddraVersion = it.elementText("drugindicationmeddraversion"),
                    drugIndication = it.elementText("drugindication"),
                    drugStartDateFormat = it.elementText("drugstartdateformat"),
                    drugStartDate = it.elementText("drugstartdate"),
                    drugStartPeriod = it.elementText("drugstartperiod"),
                    drugStartPeriodUnit = it.elementText("drugstartperiodunit"),
                    drugLastPeriod = it.elementText("druglastperiod"),
                    drugLastPeriodUnit = it.elementText("druglastperiodunit"),
                    drugEndDateFormat = it.elementText("drugenddateformat"),
                    drugEndDate = it.elementText("drugenddate"),
                    drugTreatmentDuration = it.elementText("drugtreatmentduration"),
                    drugTreatmentDurationUnit = it.elementText("drugtreatmentdurationunit"),
                    actionDrug = it.elementText("actiondrug"),
                    drugRecurreAdministration = it.elementText("drugrecurreadministration"),
                    drugAdditional = it.elementText("drugadditional"),
                    ingredients = it.elements("activesubstance").map { ele -> ele.elementText("activesubstancename") },
                    recurEvents = it.elements("drugrecurrence").map { ele ->
                        RecurEvent(
                                drugRecurActionMeddraVersion = ele.elementText("drugrecuractionmeddraversion"),
                                drugRecurAction = ele.elementText("drugrecuraction"),
                        )
                    },
                    assessments = readAssessment(it)
            )
            drugs.add(drug)

        }
        return drugs
    }

    fun readAssessment(element: Element): List<AssessmentR2> {
        return element.elements("drugreactionrelatedness").map {
            AssessmentR2(
                    drugReactionAssesMeddraVersion = it.elementText("drugreactionassesmeddraversion"),
                    drugReactionAsses = it.elementText("drugreactionasses"),
                    drugAssessmentSource = it.elementText("drugassessmentsource"),
                    drugAssessmentMethod = it.elementText("drugassessmentmethod"),
                    drugResult = it.elementText("drugresult"),
            )
        }
    }

    fun readSummary(element: Element): SummaryR2 {
        return SummaryR2(
                narrativeIncludeClinical = element.elementText("narrativeincludeclinical"),
                reporterComment = element.elementText("reportercomment"),
                senderDiagnosisMeddraVersion = element.elementText("senderdiagnosismeddraversion"),
                senderDiagnosis = element.elementText("senderdiagnosis"),
                senderComment = element.elementText("sendercomment"),
        )
    }
}

