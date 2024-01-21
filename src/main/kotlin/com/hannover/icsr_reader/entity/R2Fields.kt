package com.hannover.icsr_reader.entity

data class R2Fields(
        var transmissionIdentification: TransMissionIdentification? = null, //
        var general: GeneralR2? = null, //
        var primarySources: List<PrimarySource> = listOf(), //
        var patient: PatientR2? = null,//
        var diseaseHistory: List<MedicalHistoryEpisode> = listOf(),//既往病史
        var drugHistory: List<PatientPastDrugTherapy> = listOf(),//既往用药史
        var parent: ParentR2? = null,//
        var death: PatientDeathR2? = null,//
        var parentDiseaseHistory: List<ParentMedicalHistoryEpisode> = listOf(),//父母-既往病史
        var parentDrugHistory: List<ParentPastDrugTherapy> = listOf(),//父母-既往用药史
        var reactions: List<Reaction> = listOf(),//事件
        var tests: List<TestR2> = listOf(),//实验室检查
        var drugs: List<DrugR2> = listOf(),//药品
        var summary: SummaryR2? = null,//分析

)


data class TransMissionIdentification(
        var messageType: String? = null,
        var messageFormatVersion: String? = null,
        var messageFormatRelease: String? = null,
        var messageNumb: String? = null,
        var messageSenderIdentifier: String? = null,
        var messageReceiverIdentifier: String? = null,
        var messageDateFormat: String? = null,
        var messageDate: String? = null,
)

data class GeneralR2(
        var safetyReportVersion: String? = null,
        var safetyReportId: String? = null,
        var primarySourceCountry: String? = null,
        var occurCountry: String? = null,
        var transmissionDateFormat: String? = null,
        var transmissionDate: String? = null,
        var reportType: String? = null,
        var serious: String? = null,
        var seriousnessDeath: String? = null,
        var seriousnessLifeThreatening: String? = null,
        var seriousnessHospitalization: String? = null,
        var seriousnessDisabling: String? = null,
        var seriousnessCongenitalAnomali: String? = null,
        var seriousnessOther: String? = null,
        var receiveDateFormat: String? = null,
        var receiveDate: String? = null,
        var receiptDateFormat: String? = null,
        var receiptDate: String? = null,
        var additionalDocument: String? = null,
        var documentList: String? = null,
        var fulfillExpediteCriteria: String? = null,
        var companyNumb: String? = null,
        var authorityNumb: String? = null,
        var duplicate: String? = null,
        var reportDuplicate: List<ReportDuplicate> = listOf(),
        var linkedReport: List<String> = listOf(), //linkedreport->linkedreportnumb
        var caseNullification: String? = null,
        var nullificationReason: String? = null,
        var medicallyConfirm: String? = null,
)

data class ReportDuplicate(
        var duplicateSource: String? = null,
        var duplicateNumb: String? = null
)

data class PrimarySource(
        var reporterTitle: String? = null,
        var reporterGiveName: String? = null,
        var reporterMiddleName: String? = null,
        var reporterFamilyName: String? = null,
        var reporterOrganization: String? = null,
        var reporterDepartment: String? = null,
        var reporterCity: String? = null,
        var reporterStreet: String? = null,
        var reporterState: String? = null,
        var reporterPostCode: String? = null,
        var reporterCountry: String? = null,
        var qualification: String? = null,
        var literatureReference: String? = null,
        var studyName: String? = null,
        var sponsorStudyNumb: String? = null,
        var observeStudyType: String? = null,
)

data class PatientR2(
        var patientInitial: String? = null,
        var patientGpMedicalRecordNumb: String? = null,
        var patientSpecialistRecordNumb: String? = null,
        var patientHospitalRecordNumb: String? = null,
        var patientInvestigationNumb: String? = null,
        var patientBirthdateFormat: String? = null,
        var patientBirthDate: String? = null,
        var patientOnsetAge: String? = null,
        var patientOnsetAgeUnit: String? = null,
        var gestationPeriod: String? = null,
        var gestationPeriodUnit: String? = null,
        var patientAgeGroup: String? = null,
        var patientWeight: String? = null,
        var patientHeight: String? = null,
        var patientSex: String? = null,
        var lastMenstrualDateFormat: String? = null,
        var patientLastMenstrualDate: String? = null,
        var patientMedicalHistoryText: String? = null,
        var resultsTestsProcedures: String? = null,
)

data class MedicalHistoryEpisode(
        var patientEpisodeNameMeddraVersion: String? = null,
        var patientEpisodeName: String? = null,
        var patientMedicalStartDateformat: String? = null,
        var patientMedicalStartDate: String? = null,
        var patientMedicalContinue: String? = null,
        var patientMedicalEndDateFormat: String? = null,
        var patientMedicalEndDate: String? = null,
        var patientMedicalComment: String? = null,
)

data class PatientPastDrugTherapy(
        var patientDrugName: String? = null,
        var patientDrugStartDateFormat: String? = null,
        var patientDrugStartDate: String? = null,
        var patientDrugEndDateFormat: String? = null,
        var patientDrugEndDate: String? = null,
        var patientIndicationMeddraVersion: String? = null,
        var patientDrugIndication: String? = null,
        var patientDrugReactionMeddraVersion: String? = null,
        var patientDrugReaction: String? = null,
)


data class PatientDeathR2(
        var patientDeathDateFormat: String? = null,
        var patientDeathDate: String? = null,
        var patientAutopsyYesNo: String? = null,
        var cause: List<DeathCause> = listOf(),
        var autopsy: List<Autopsy> = listOf()
)

data class DeathCause(
        var patientDeathReportMeddraVersion: String? = null,
        var patientDeathReport: String? = null,
)

data class Autopsy(
        var patientDetermineAutopsyMeddraVersion: String? = null,
        var patientDetermineAutopsy: String? = null,
)


data class ParentR2(
        var parentIdentification: String? = null,
        var parentBirthDateFormat: String? = null,
        var parentBirthDate: String? = null,
        var parentAge: String? = null,
        var parentAgeUnit: String? = null,
        var parentLastMenstrualDateFormat: String? = null,
        var parentLastMenstrualDate: String? = null,
        var parentWeight: String? = null,
        var parentHeight: String? = null,
        var parentSex: String? = null,
        var parentMedicalRelevantText: String? = null,
)

data class ParentMedicalHistoryEpisode(
        var parentMdEpisodeMeddraVersion: String? = null,
        var parentMedicalEpisodeName: String? = null,
        var parentMedicalStartDateFormat: String? = null,
        var parentMedicalStartDate: String? = null,
        var parentMedicalContinue: String? = null,
        var parentMedicalEndDateFormat: String? = null,
        var parentMedicalEndDate: String? = null,
        var parentMedicalComment: String? = null,
)

data class ParentPastDrugTherapy(
        var parentDrugName: String? = null,
        var parentDrugStartDateFormat: String? = null,
        var parentDrugStartDate: String? = null,
        var parentDrugEndDateFormat: String? = null,
        var parentDrugEndDate: String? = null,
        var parentDrugIndicationMeddraVersion: String? = null,
        var parentDrugIndication: String? = null,
        var parentDrugReactionMeddraVersion: String? = null,
        var parentDrugReaction: String? = null,

        )

data class Reaction(
        var primarySourceReaction: String? = null,
        var reactionMeddraVersionLlt: String? = null,
        var reactionMeddraLlt: String? = null,
        var reactionMeddraVersionPt: String? = null,
        var reactionMeddraPt: String? = null,
        var termHighLighted: String? = null,
        var reactionSateFormat: String? = null,
        var reactionStartDate: String? = null,
        var reactionEndDateFormat: String? = null,
        var reactionEndDate: String? = null,
        var reactionDuration: String? = null,
        var reactionDurationUnit: String? = null,
        var reactionFirstTime: String? = null,
        var reactionFirstTimeUnit: String? = null,
        var reactionLastTime: String? = null,
        var reactionLastTimeUnit: String? = null,
        var reactionOutcome: String? = null,
)

data class TestR2(
        var testDateFormat: String? = null,
        var testDate: String? = null,
        var testName: String? = null,
        var testResult: String? = null,
        var testUnit: String? = null,
        var lowTestRange: String? = null,
        var highTestRange: String? = null,
        var moreInformation: String? = null,
)

data class DrugR2(
        var drugCharacterization: String? = null,
        var medicinalProduct: String? = null,
        var obtainDrugCountry: String? = null,
        var drugBatchNumb: String? = null,
        var drugAuthorizationNumb: String? = null,
        var drugAuthorizationCountry: String? = null,
        var drugAuthorizationHolder: String? = null,
        var drugStructureDosageNumb: String? = null,
        var drugStructureDosageUnit: String? = null,
        var drugSeparateDosageNumb: String? = null,
        var drugIntervalDosageUnitNumb: String? = null,
        var drugIntervalDosageDefinition: String? = null,
        var drugCumulativeDosageNumb: String? = null,
        var drugCumulativeDosageUnit: String? = null,
        var drugDosageText: String? = null,
        var drugDosageForm: String? = null,
        var drugAdministrationRoute: String? = null,
        var drugParAdministration: String? = null,
        var reactionGestationPeriod: String? = null,
        var reactionGestationPeriodUnit: String? = null,
        var drugIndicationMeddraVersion: String? = null,
        var drugIndication: String? = null,
        var drugStartDateFormat: String? = null,
        var drugStartDate: String? = null,
        var drugStartPeriod: String? = null,
        var drugStartPeriodUnit: String? = null,
        var drugLastPeriod: String? = null,
        var drugLastPeriodUnit: String? = null,
        var drugEndDateFormat: String? = null,
        var drugEndDate: String? = null,
        var drugTreatmentDuration: String? = null,
        var drugTreatmentDurationUnit: String? = null,
        var actionDrug: String? = null,
        var drugRecurreAdministration: String? = null,
        var drugAdditional: String? = null,
        var ingredients: List<String> = listOf(),
        var recurEvents: List<RecurEvent> = listOf(),
        var assessments: List<AssessmentR2> = listOf(),


        )

data class RecurEvent(
        var drugRecurActionMeddraVersion: String? = null,
        var drugRecurAction: String? = null,
)

data class AssessmentR2(
        var drugReactionAssesMeddraVersion: String? = null,
        var drugReactionAsses: String? = null,
        var drugAssessmentSource: String? = null,
        var drugAssessmentMethod: String? = null,
        var drugResult: String? = null,

        )

data class SummaryR2(
        var narrativeIncludeClinical: String? = null,
        var reporterComment: String? = null,
        var senderDiagnosisMeddraVersion: String? = null,
        var senderDiagnosis: String? = null,
        var senderComment: String? = null,
)






