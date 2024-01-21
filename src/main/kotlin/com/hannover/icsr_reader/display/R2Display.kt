package com.hannover.icsr_reader.display

import com.hannover.icsr_reader.entity.*
import com.hannover.icsr_reader.enums.*
import com.hannover.icsr_reader.reader.R2Reader
import com.hannover.icsr_reader.service.DataDictionaryService
import com.hannover.icsr_reader.util.StringUtil
import jakarta.annotation.Resource
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.io.File
import java.io.FileWriter
import java.nio.charset.Charset

@Component
class R2Display {

    val dir = "D:\\EOCTZ\\R2\\"



    @Resource
    private lateinit var reader: R2Reader

    @Resource
    private lateinit var mongoTemplate: MongoTemplate


    @Resource
    private lateinit var dataDictionaryService: DataDictionaryService

    var edqmRoa = listOf<DataDictionaryItem>()



    fun display() {

        edqmRoa = dataDictionaryService.find("edqm_roa_en")?.children?: listOf()
//        val fileTreeWalk = File(dir).walk()
//        fileTreeWalk.filter { it.name.lowercase().endsWith(".xml") }.forEach {
//            val data = reader.process(it)
//            process(data)
//        }

        val file = File("D:\\EOCTZ\\E2B2028186_PRD640_20191216160519.XML")
        val data = reader.process(file)

        process(data)
    }

    fun process(data: List<R2Fields>) {

        data.forEach {

            val result = mutableListOf<String>()
            val globalUniqueCaseId = it.general?.safetyReportId
            val caseNumber = globalUniqueCaseId?.split("-")?.last()

            processGeneral(it.general, result)
            processReporter(it.primarySources,result)
            processPatient(it.patient,result)
            processDiseaseHistory(it.diseaseHistory,result)
            processMedicalHistory(it.drugHistory,result)
            processDeathInfo(it.death,result)
            processDeathReason(it.death?.cause,result)
            processAutopsy(it.death?.autopsy,result)
            processParent(it.parent,result)

            val meddraVersion = it.reactions.find { e-> !e.reactionMeddraVersionLlt.isNullOrEmpty() }?.reactionMeddraVersionLlt
            processLab(it.tests,meddraVersion,result)
            processLabResult(it.patient,result)
            processEvent(it.reactions,result)
            processDrug(it.drugs,result)
            processAnalyse(it.summary,result)

            val out = FileWriter("D:\\EOCTZ\\display\\R2\\${caseNumber}.txt", Charsets.UTF_8)
            out.use { o ->
                result.forEach { line ->
                    o.write(line + "\n")
                }
                o.flush()
            }

        }
    }

    fun processGeneral(general: GeneralR2?, result: MutableList<String>) {
        result.add("A.1.0.1 发送者的（病例）安全报告唯一识别码:  ${general?.safetyReportId ?: ""}")
        result.add("A.1.1 发生国家:                         ${general?.primarySourceCountry ?: ""}(${Country.get(general?.primarySourceCountry)?.text ?: ""})")
        result.add("A.1.2 事件发生国家:                      ${general?.occurCountry ?: ""}(${Country.get(general?.occurCountry)?.text ?: ""})")
        result.add("A.1.4 报告类型 :                        ${general?.reportType ?: ""}(${ReportTypeCn.get(general?.reportType?.toIntOrNull())?.label ?: ""})")
        result.add("A.1.5.1 严重性 :                        ${yesAndNo(general?.serious)}")
        result.add("A.1.5.2a 导致死亡:                      ${yesAndNo(general?.seriousnessDeath)}")
        result.add("A.1.5.2b 危及生命:                      ${yesAndNo(general?.seriousnessLifeThreatening)}")
        result.add("A.1.5.2c 导致住院/延长住院时间:           ${yesAndNo(general?.seriousnessHospitalization)}")
        result.add("A.1.5.2d 残疾/功能丧失:                  ${yesAndNo(general?.seriousnessDisabling)}")
        result.add("A.1.5.2e 先天性异常或出生缺陷:            ${yesAndNo(general?.seriousnessCongenitalAnomali)}")
        result.add("A.1.5.2f 其他重要医学事件:                ${yesAndNo(general?.seriousnessOther)}")
        result.add("A.1.6b: 首次收到日期:                    ${general?.receiveDate}")
        result.add("A.1.7b: 随访报告收到日期:                 ${general?.receiptDate}")
        result.add("A.1.8.1: 是否有附件:                     ${yesAndNo(general?.additionalDocument)}")
        result.add("A.1.8.2 附件:                           ${general?.documentList?:""}")
        result.add("A.1.9 是否满足本地加速报告的要求:           ${yesAndNo(general?.fulfillExpediteCriteria)}")
        result.add("A.1.10.2 全球唯一病例识别码 :              ${general?.companyNumb ?: ""}")
        general?.reportDuplicate?.forEach {
            result.add("A.1.11.1 病例识别码的来源 :              ${it.duplicateSource}")
            result.add("A.1.11.2 病例识别码 :                   ${it.duplicateNumb}")
        }

        result.add("A.1.12.r 与本报告相关的报告识别编号 :  ${general?.linkedReport?.joinToString(";") ?: ""}")
        result.add("A.1.13   修正/作废 :  ${ReviseOrCancel.get(general?.caseNullification?.toIntOrNull())?.label?:""}")
        result.add("A.1.13.1 修正/作废理由 :  ${general?.nullificationReason?:""}")
        result.add("A.1.14  医学确认 :   ${yesAndNo(general?.medicallyConfirm)}")

    }

    fun processReporter(reporters: List<PrimarySource>?, result: MutableList<String>) {

        result.add("")
        result.add("")
        result.add("=====================报告者 =====================")
        reporters?.forEachIndexed { i, r ->
            result.add("---------------------#${i + 1}---------------------")
            r.reporterTitle?.let { result.add("A.2.1.1a 报告者的职称:  $it") }
            r.reporterGiveName?.let { result.add("A.2.1.1b 报告者的名字:  $it") }
            r.reporterMiddleName?.let { result.add("A.2.1.1c 报告者的中间名:  $it") }
            r.reporterFamilyName?.let { result.add("A.2.1.1d 报告者的姓氏:  $it") }
            r.reporterOrganization?.let { result.add("A.2.1.2a 报告者所在机构:  $it") }
            r.reporterDepartment?.let { result.add("A.2.1.2b 报告者所在部门:  $it") }
            r.reporterStreet?.let { result.add("A.2.1.2c 报告者所在街道地址:  $it") }
            r.reporterCity?.let { result.add("A.2.1.2d 报告者所在城市:  $it") }
            r.reporterState?.let { result.add("A.2.1.2e 报告者所在州或省:  $it") }
            r.reporterPostCode?.let { result.add("A.2.1.2f 报告者的邮政编码:  $it") }
            r.reporterCountry?.let { result.add("A.2.1.3 报告者的国家代码:  ${Country.get(it)?.text ?: ""}") }
            r.qualification?.let { result.add("A.2.1.4 报告者资质:  ${ReporterQualification.get(it.toIntOrNull())?.label ?: ""}") }
            result.add("")
            if(!r.literatureReference.isNullOrEmpty()){
                result.add("A.2.2 文献:   ${r.literatureReference?:""}")
            }
            if(!r.studyName.isNullOrEmpty()){
                result.add("A.2.3.1 研究名称:   ${r.studyName?:""}")
            }
            if(!r.sponsorStudyNumb.isNullOrEmpty()){
                result.add("A.2.3.2 申办者研究编号:   ${r.sponsorStudyNumb?:""}")
            }
            if(r.observeStudyType!=null){
                result.add("A.2.3.3 研究类型:   ${r.observeStudyType?:""}")
            }
        }

    }

    fun processPatient(patient:PatientR2?, result: MutableList<String>) {
        result.add("")
        result.add("")
        result.add("=====================患者 =====================")
        patient?.patientInitial?.let { result.add("B.1.1 患者(姓名或姓名首字母缩写):  $it")}
        patient?.patientGpMedicalRecordNumb?.let { result.add("B.1.1.1a GP医疗记录号:  $it")}
        patient?.patientSpecialistRecordNumb?.let { result.add("B.1.1.1b 专家记录编号:  $it")}
        patient?.patientHospitalRecordNumb?.let { result.add("B.1.1.1c 医院记录编号:  $it")}
        patient?.patientInvestigationNumb?.let { result.add("B.1.1.1d 受试者编号:  $it")}
        patient?.patientBirthDate?.let { result.add("B.1.2.1b 出生日期:  $it")}
        patient?.patientOnsetAge?.let { result.add("B.1.2.2a 年龄:  $it")}
        patient?.patientOnsetAgeUnit?.let { result.add("B.1.2.2b 年龄单位:  ${R2TimeUnitMapping.getByR2(it)?.label?:""}")}
        patient?.gestationPeriod?.let { result.add("B.1.2.2.1a 胎儿被观察时的孕龄:  $it")}
        patient?.gestationPeriodUnit?.let { result.add("B.1.2.2.1b 胎儿被观察时的孕龄单位:   ${R2TimeUnitMapping.getByR2(it)?.label?:""}")}
        patient?.patientAgeGroup?.let { result.add("B.1.2.3 年龄段:   ${AgeRange.get(it.toIntOrNull())?.label?:""}")}
        patient?.patientWeight?.let { result.add("B.1.3 体重:   $it")}
        patient?.patientHeight?.let { result.add("B.1.4 身高:   $it")}
        patient?.patientSex?.let { result.add("B.1.5 性别:   ${Sex.get(it.toIntOrNull())?.text?:""}")}
        patient?.patientLastMenstrualDate?.let { result.add("B.1.6b:   $it")}
        patient?.patientMedicalHistoryText?.let { result.add("B.1.7.2 相关病史及并发疾病的文本说明:   $it")}
    }


    fun processDiseaseHistory(diseaseHistory: List<MedicalHistoryEpisode>, result: MutableList<String>) {
        if(diseaseHistory.isEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================既往病史及并发疾病 =====================")
        diseaseHistory.forEachIndexed { i, d ->
            result.add("---------------------#${i + 1}---------------------")
            val nameCode = getMeddra(d.patientEpisodeName,d.patientEpisodeNameMeddraVersion)
            val meddraText = StringUtil.showText(nameCode?.nameEn?:"","(",")")
            d.patientEpisodeNameMeddraVersion?.let { result.add("B.1.7.1a.1 疾病名称编码版本:   $it")}
            d.patientEpisodeName?.let { result.add("B.1.7.1a.2 疾病名称编码:   $it$meddraText")}
            d.patientMedicalStartDate?.let { result.add("B.1.7.1c 开始日期:   $it")}
            d.patientMedicalContinue?.let { result.add("B.1.7.1d 持续:   ${yesAndNo(it)}")}
            d.patientMedicalEndDate?.let { result.add("B.1.7.1f 结束日期:   $it")}
            d.patientMedicalComment?.let { result.add("B.1.7.1g 备注:   $it")}
            result.add("")
        }
    }


    fun processMedicalHistory(diseaseHistory: List<PatientPastDrugTherapy>, result: MutableList<String>) {
        if(diseaseHistory.isEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================既往用药史 =====================")
        diseaseHistory.forEachIndexed { i, d ->
            result.add("---------------------#${i + 1}---------------------")
            d.patientDrugName?.let { result.add("B.1.8a 药物名称:   $it")}
            d.patientDrugStartDate?.let { result.add("B.1.8c 开始日期:   $it")}
            d.patientDrugEndDate?.let { result.add("B.1.8e 停用日期:   $it")}

            val indicationCode = getMeddra(d.patientDrugIndication,d.patientIndicationMeddraVersion)
            val indicationText = StringUtil.showText(indicationCode?.nameEn?:"","(",")")
            d.patientDrugIndication?.let { result.add("B.1.8f.1 适应症编码版本:   $it")}
            d.patientIndicationMeddraVersion?.let { result.add("B.1.8f.2 适应症编码:   $it$indicationText")}
            val reactionCode = getMeddra(d.patientDrugReaction,d.patientDrugReactionMeddraVersion)
            val reactionText = StringUtil.showText(reactionCode?.nameEn?:"","(",")")
            d.patientDrugReactionMeddraVersion?.let { result.add("B.1.8g.1 不良反应编码版本:   $it")}
            d.patientDrugReaction?.let { result.add("B.1.8g.2 不良反应编码:   $it$reactionText")}
            result.add("")
        }
    }

    fun processDeathInfo(patient:PatientDeathR2?, result: MutableList<String>) {
        result.add("")
        result.add("")
        result.add("=====================死亡信息 =====================")
        patient?.patientDeathDate?.let { result.add("BB.1.9.1b 死亡日期:   $it") }
        patient?.patientAutopsyYesNo?.let { result.add("BB.1.9.3 是否尸检:   ${yesAndNo(it)}") }
    }

    fun processDeathReason(deathReason:List<DeathCause>?, result: MutableList<String>) {
        if(deathReason.isNullOrEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================报告的死因 =====================")
        deathReason.forEachIndexed { i, d ->
            result.add("---------------------#${i + 1}---------------------")
            val reasonCode = getMeddra(d.patientDeathReport,d.patientDeathReportMeddraVersion)
            val reasonText = StringUtil.showText(reasonCode?.nameEn?:"","(",")")
            d.patientDeathReportMeddraVersion?.let { result.add("B.1.9.2.a 死亡原因编码版本:   $it") }
            d.patientDeathReport?.let { result.add("B.1.9.2.b 死亡原因编码:   $it$reasonText") }
            result.add("")
        }

    }

    fun processAutopsy(autopsy:List<Autopsy>?, result: MutableList<String>) {
        if(autopsy.isNullOrEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================报告的死因 =====================")
        autopsy.forEachIndexed { i, d ->
            result.add("---------------------#${i + 1}---------------------")
            val autopsyCode = getMeddra(d.patientDetermineAutopsy,d.patientDetermineAutopsyMeddraVersion)
            val autopsyText = StringUtil.showText(autopsyCode?.nameEn?:"","(",")")
            d.patientDetermineAutopsyMeddraVersion?.let { result.add("B.1.9.4.a 死亡原因编码版本:   $it") }
            d.patientDetermineAutopsy?.let { result.add("B.1.9.4.b 死亡原因编码:   $it$autopsyText") }
            result.add("")
        }
    }

    fun processParent(parent:ParentR2?, result: MutableList<String>){
        result.add("")
        result.add("")
        result.add("=====================患者父母 =====================")
        parent?.parentIdentification?.let { result.add("B.1.10.1 父母识别:   $it") }
        parent?.parentBirthDate?.let { result.add("B.1.10.2.1b 父母的出生日期:   $it") }
        parent?.parentAge?.let { result.add("B.1.10.2.2a 父母的年龄:   $it") }
        parent?.parentAgeUnit?.let { result.add("B.1.10.2.2b 父母的年龄单位:   ${R2TimeUnitMapping.getByR2(it)?.label?:""}") }
        parent?.parentLastMenstrualDate?.let { result.add("B.1.10.3b 父母末次月经:   $it") }
        parent?.parentWeight?.let { result.add("B.1.10.4 父母身高:   $it") }
        parent?.parentHeight?.let { result.add("B.1.10.5 父母体重:   $it") }
        parent?.parentSex?.let { result.add("B.1.10.6 父母性别:   ${Sex.get(it.toIntOrNull())?.text?:""}") }
    }

    fun processEvent(events:List<Reaction>, result: MutableList<String>){
        if(events.isEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================事件 =====================")
        events.forEachIndexed { i, e ->
            result.add("---------------------#${i + 1}---------------------")
            e.primarySourceReaction?.let {  result.add("B.2.i.0 用母语报告的术语:   $it") }

            val eventCode = getMeddra(e.reactionMeddraLlt,e.reactionMeddraVersionLlt)
            val eventText = StringUtil.showText(eventCode?.nameEn?:"","(",")")
            e.reactionMeddraVersionLlt?.let {  result.add("B.2.i.1a 事件名称编码版本:   $it") }
            e.reactionMeddraLlt?.let {  result.add("B.2.i.1a 事件名称编码:   $it$eventText") }
            e.termHighLighted?.let {  result.add("B.2.i.3 由报告者强调的术语:   ${ReporterTerm.get(it.toIntOrNull())?.label}") }
            e.reactionStartDate?.let {  result.add("B.2.i.4b 事件开始时间:   $it") }
            e.reactionEndDate?.let {  result.add("B.2.i.5b 事件结束时间:   $it") }
            e.reactionDuration?.let {  result.add("B.2.i.6a 持续时间(数值) :   $it") }
            e.reactionDurationUnit?.let {  result.add("B.2.i.6b 持续时间(单位) :   ${R2IntervalUnitMapping.getByR2(it)?.label?:""}") }
            e.reactionFirstTime?.let {  result.add("B.2.i.7.1a 持首剂间隔(数值) :   $it") }
            e.reactionFirstTimeUnit?.let {  result.add("B.2.i.7.1b 首剂间隔(单位)  :   ${R2IntervalUnitMapping.getByR2(it)?.label?:""}") }
            e.reactionLastTime?.let {  result.add("B.2.i.6b 事件发生距末次用药的期间(数值) :   $it") }
            e.reactionLastTimeUnit?.let {  result.add("B.2.i.6b 事件发生距末次用药的期间(单位) :   ${R2IntervalUnitMapping.getByR2(it)?.label?:""}") }
            e.reactionOutcome?.let {  result.add("B.2.i.6b 事件发生距末次用药的期间(数值) :   ${EventResult.get(it.toIntOrNull())}") }
            result.add("")
        }

    }

    fun processLab(labs:List<TestR2>,meddraVersion: String?,result: MutableList<String>){
        if(labs.isEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================实验室检查 =====================")
        labs.forEachIndexed { i, l ->
            result.add("---------------------#${i + 1}---------------------")
            val labCode = getMeddra(l.testName,meddraVersion)
            val labText = StringUtil.showText(labCode?.nameEn?:"","(",")")
            l.testDate?.let {  result.add("B.3.1b 检查日期:   $it") }
            l.testName?.let {  result.add("B.3.1c 检查项:   $it${labText}") }
            l.testResult?.let {  result.add("B.3.1d 检查结果:   $it") }
            l.testUnit?.let {  result.add("B.3.1e 检查结果单位:   $it") }
            l.lowTestRange?.let {  result.add("B.3.1.1 正常范围最低值:   $it") }
            l.highTestRange?.let {  result.add("B.3.1.2 正常范围最高值:   $it") }
            l.moreInformation?.let {  result.add("B.3.1.3 正常范围最高值:   ${yesAndNo(it)}") }
            result.add("")
        }

    }

    fun processLabResult(patient: PatientR2?, result: MutableList<String>){
        if(!patient?.resultsTestsProcedures.isNullOrEmpty()){
            result.add("B.3.2 R2检查:   ${patient?.resultsTestsProcedures}")
            result.add("")
        }

    }

    fun processDrug(drugs:List<DrugR2>, result: MutableList<String>){
        if(drugs.isEmpty()) return
        result.add("")
        result.add("")
        result.add("=====================药品 =====================")
        drugs.forEachIndexed { i, d ->
            result.add("---------------------#${i + 1}---------------------")
            d.drugCharacterization?.let {  result.add("B.4.k.1 产品特征:   ${DrugCharacterCn.get(it.toIntOrNull())?.text?:""}") }
            d.medicinalProduct?.let {  result.add("B.4.k.2.1 产品名称:   $it") }
            d.obtainDrugCountry?.let {  result.add("B.4.k.2.2 药品获得国的标识:   ${Country.get(it)?.text?:""}") }
            d.drugAuthorizationNumb?.let {  result.add("B.4.k.4.1 上市许可/申请编号:   $it") }
            d.drugAuthorizationCountry?.let {  result.add("B.4.k.4.2 上市许可/申请国家:   ${Country.get(it)?.text?:""}") }
            d.drugAuthorizationHolder?.let {  result.add("B.4.k.4.3 持有者/申请者名称:   $it") }
            d.drugDosageForm?.let {  result.add("B.4.k.7 剂型 :  $it") }

            if(d.ingredients.isNotEmpty()){
                result.add("成分信息")
            }
            d.ingredients.forEach {
                  result.add("- 成分:   $it")
            }

            //适应症

            if(!d.drugIndicationMeddraVersion.isNullOrEmpty() || !d.drugIndication.isNullOrEmpty()){
                result.add("")
                result.add("适应症")
                val indicationCode = getMeddra(d.drugIndication,d.drugIndicationMeddraVersion)
                val indicationText = StringUtil.showText(indicationCode?.nameEn?:"","(",")")
                d.drugIndicationMeddraVersion?.let {  result.add("B.4.k.11a 适应症编码版本:   $it") }
                d.drugIndication?.let {  result.add("B.4.k.11b 适应症编码:   $it${indicationText}") }
            }


            result.add("")
            result.add("给药信息")
            d.drugStartDate?.let {  result.add("B.4.k.12b 开始用药时间 :   $it") }
            d.drugEndDate?.let {  result.add("B.4.k.14b 结束用药时 :   $it") }
            d.drugTreatmentDuration?.let {  result.add("B.4.k.15a给药的持续时间（数值） :   $it") }
            d.drugTreatmentDurationUnit?.let {  result.add("B.4.k.15b 给药的持续时间（单位） :  ${R2IntervalUnitMapping.getByR2(it)?.label}") }
            d.drugStructureDosageNumb?.let {  result.add("B.4.k.5.1 单次剂量 :   $it") }
            d.drugStructureDosageUnit?.let {  result.add("B.4.k.5.2 单次剂量单位 :   ${DoseUnitR2.getByR2(it)?.code?:""}") }
            d.drugIntervalDosageUnitNumb?.let {  result.add("B.4.k.5.4 间隔时间（数值） :   $it") }
            d.drugIntervalDosageDefinition?.let {  result.add("B.4.k.5.5 时间间隔单位的定义 :   ${R2IntervalUnitMapping.getByR2(it)?.label}") }
            d.drugBatchNumb?.let {  result.add("B.4.k.3 批次/批号:   $it") }
            d.drugDosageText?.let {  result.add("B.4.k.6 剂量文本 :   $it") }
            d.drugAdministrationRoute?.let {  result.add("B.4.k.8 给药途径术语ID :   ${edqmRoa.find { r -> r.value?.endsWith("|${it}") == true }?.label}") }
            d.drugParAdministration?.let {  result.add("B.4.k.9 父母的给药途径术语ID :   ${edqmRoa.find { r-> r.value?.endsWith("|${it}") == true }?.label}") }
            d.drugSeparateDosageNumb?.let {  result.add("B.4.k.5.3 时间间隔内用药次数 :   $it") }




            result.add("")
            result.add("用药情况描述")
            d.drugCumulativeDosageNumb?.let {  result.add("B.4.k.5.6 首次发生反应时的累积剂量(数值) :   $it") }
            d.drugCumulativeDosageNumb?.let {  result.add("B.4.k.5.7 首次发生反应时的累积剂量(单位) :   ${DoseUnitR2.getByR2(it)?.code?:""}") }
            d.reactionGestationPeriod?.let {  result.add("B.4.k.10a 暴露时的孕龄(单位) :   $it") }
            d.reactionGestationPeriodUnit?.let {  result.add("B.4.k.10b 暴露时的孕龄(单位) :   ${R2TimeUnitMapping.getByR2(it)?.label}") }
            d.actionDrug?.let {  result.add("B.4.k.16 对药物采取的措施 :   ${DrugMeasureR2.get(it.toIntOrNull())?.text}") }
            d.drugRecurreAdministration?.let {  result.add("B.4.k.17.1 再激发 :   ${ProductUseRecur.get(it.toIntOrNull())?.label}") }
            d.drugAdditional?.let {  result.add("B.4.k.19 药物的附加信息 :   $it") }

            val other =
            d.drugStartPeriod != null || !d.drugStartPeriodUnit.isNullOrEmpty() || d.drugLastPeriod!= null || !d.drugLastPeriodUnit.isNullOrEmpty()
            if(d.assessments.isNotEmpty() || other){
                result.add("")
                result.add("评估")
            }

            d.drugStartPeriod?.let {  result.add("B.4.k.13.1a Time Interval between Beginning of Drug Administration and Start of Reaction / Event (number)  :   $it") }
            d.drugStartPeriodUnit?.let {  result.add("B.4.k.13.1b Time Interval between Beginning of Drug Administration and Start of Reaction / Event (unit)  :   ${R2IntervalUnitMapping.getByR2(it)?.label}") }
            d.drugLastPeriod?.let {  result.add("B.4.k.13.2a Time Interval between Last Dose of Drug and Start of Reaction / Event (number) :   $it") }
            d.drugLastPeriodUnit?.let {  result.add("B.4.k.13.2b Time Interval between Last Dose of Drug and Start of Reaction / Event (unit)  :   ${R2IntervalUnitMapping.getByR2(it)?.label}") }

            d.assessments.forEachIndexed { i,es->
                result.add("---------------------# 评估${i + 1}---------------------")

                val eventText = if(es.drugReactionAsses?.toIntOrNull() != null){
                    val eventCode = getMeddra(es.drugReactionAsses,es.drugReactionAssesMeddraVersion)
                    val eventText = StringUtil.showText(eventCode?.nameEn?:"","(",")")
                    "${es.drugReactionAsses}${eventText}"
                }else{
                    es.drugReactionAsses
                }
                es.drugReactionAsses?.let {  result.add("B.4.k.18.1b 事件名称编码:   $eventText") }
                es.drugAssessmentSource?.let {  result.add("B.4.k.18.2 评估来源:   $it") }
                es.drugAssessmentMethod?.let {  result.add("B.4.k.18.3 评估方法:   $it") }
                es.drugResult?.let {  result.add("B.4.k.18.4 评估结果:   $it") }

            }

            result.add("")
        }
    }

    fun processAnalyse(analyse: SummaryR2?, result: MutableList<String>){
        analyse?.narrativeIncludeClinical?.let {  result.add("B.5.1 病例叙述:   $it") }
        result.add("======================")
        result.add("======================")
        result.add("======================")
        analyse?.reporterComment?.let {  result.add("B.5.2 报告者的评论:   $it") }
        analyse?.senderComment?.let {  result.add("B.5.4 公司评论:   $it") }
    }



    private fun yesAndNo(value: String?): String {
        return when (value) {
            "1" -> {
                "是"
            }

            "2" -> {
                "否"
            }

            else -> {
                ""
            }
        }
    }

    private fun yesAndNo(value: Boolean?): String {
        return when (value) {
            true -> {
                "是"
            }

            false -> {
                "否"
            }

            else -> {
                ""
            }
        }
    }

    private fun getMeddra(code:String?,version:String?):Dictionary?{
        if (code.isNullOrEmpty() || version.isNullOrEmpty()){
            return null
        }
        val collection  = "meddra_${version.replace(".","_")}"
        return mongoTemplate.findOne(Query.query(Criteria.where("code").`is`(code)),Dictionary::class.java,collection)
    }
}