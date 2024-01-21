package com.hannover.icsr_reader.enums


enum class ReportTypeCn(val label: String, val value: Int) {
    SpontaneousReport("自发性报告", 1),
    FromResearch("来自研究的报告", 2),
    Other("其它", 3),
    Unknown("无法获知（不详）", 4);

    companion object {
        fun get(value: Int?): ReportTypeCn? {
            return ReportTypeCn.values().find { it.value == value }
        }
    }
}

enum class Country(val code: String, val text: String) {
    AD("AD","安道尔"),
    AE("AE","阿联酋"),
    AF("AF","阿富汗"),
    AG("AG","安提瓜和巴布达"),
    AI("AI","安圭拉"),
    AL("AL","阿尔巴尼亚"),
    AM("AM","亚美尼亚"),
    AO("AO","安哥拉"),
    AQ("AQ","南极洲"),
    AR("AR","阿根廷"),
    AS("AS","美属萨摩亚"),
    AT("AT","奥地利"),
    AU("AU","澳大利亚"),
    AW("AW","阿鲁巴"),
    AX("AX","奥兰群岛"),
    AZ("AZ","阿塞拜疆"),
    BA("BA","波黑"),
    BB("BB","巴巴多斯"),
    BD("BD","孟加拉"),
    BE("BE","比利时"),
    BF("BF","布基纳法索"),
    BG("BG","保加利亚"),
    BH("BH","巴林"),
    BI("BI","布隆迪"),
    BJ("BJ","贝宁"),
    BL("BL","圣巴泰勒米岛"),
    BM("BM","百慕大"),
    BN("BN","文莱"),
    BO("BO","玻利维亚"),
    BQ("BQ","荷兰加勒比区"),
    BR("BR","巴西"),
    BS("BS","巴哈马"),
    BT("BT","不丹"),
    BV("BV","布韦岛"),
    BW("BW","博茨瓦纳"),
    BY("BY","白俄罗斯"),
    BZ("BZ","伯利兹"),
    CA("CA","加拿大"),
    CC("CC","科科斯群岛"),
    CD("CD","刚果（金）"),
    CF("CF","中非"),
    CG("CG","刚果（布）"),
    CH("CH","瑞士"),
    CI("CI","科特迪瓦"),
    CK("CK","库克群岛"),
    CL("CL","智利"),
    CM("CM","喀麦隆"),
    CN("CN","中国"),
    CO("CO","哥伦比亚"),
    CR("CR","哥斯达黎加"),
    CU("CU","古巴"),
    CV("CV","佛得角"),
    CW("CW","库腊索岛"),
    CX("CX","圣诞岛"),
    CY("CY","塞浦路斯"),
    CZ("CZ","捷克"),
    DE("DE","德国"),
    DJ("DJ","吉布提"),
    DK("DK","丹麦"),
    DM("DM","多米尼克"),
    DO("DO","多米尼加"),
    DZ("DZ","阿尔及利亚"),
    EC("EC","厄瓜多尔"),
    EE("EE","爱沙尼亚"),
    EG("EG","埃及"),
    EH("EH","西撒哈拉"),
    ER("ER","厄立特里亚"),
    ES("ES","西班牙"),
    ET("ET","埃塞俄比亚"),
    EU("EU","欧盟"),
    FI("FI","芬兰"),
    FJ("FJ","斐济群岛"),
    FK("FK","马尔维纳斯群岛（福克兰）"),
    FM("FM","密克罗尼西亚联邦"),
    FO("FO","法罗群岛"),
    FR("FR","法国"),
    GA("GA","加蓬"),
    GB("GB","英国"),
    GD("GD","格林纳达"),
    GE("GE","格鲁吉亚"),
    GF("GF","法属圭亚那"),
    GG("GG","根西岛"),
    GH("GH","加纳"),
    GI("GI","直布罗陀"),
    GL("GL","格陵兰"),
    GM("GM","冈比亚"),
    GN("GN","几内亚"),
    GP("GP","瓜德罗普"),
    GQ("GQ","赤道几内亚"),
    GR("GR","希腊"),
    GS("GS","南乔治亚岛和南桑威奇群岛"),
    GT("GT","危地马拉"),
    GU("GU","关岛"),
    GW("GW","几内亚比绍"),
    GY("GY","圭亚那"),
    HK("HK","香港"),
    HM("HM","赫德岛和麦克唐纳群岛"),
    HN("HN","洪都拉斯"),
    HR("HR","克罗地亚"),
    HT("HT","海地"),
    HU("HU","匈牙利"),
    ID("ID","印尼"),
    IE("IE","爱尔兰"),
    IL("IL","以色列"),
    IM("IM","马恩岛"),
    IN("IN","印度"),
    IO("IO","英属印度洋领地"),
    IQ("IQ","伊拉克"),
    IR("IR","伊朗"),
    IS("IS","冰岛"),
    IT("IT","意大利"),
    JE("JE","泽西岛"),
    JM("JM","牙买加"),
    JO("JO","约旦"),
    JP("JP","日本"),
    KE("KE","肯尼亚"),
    KG("KG","吉尔吉斯斯坦"),
    KH("KH","柬埔寨"),
    KI("KI","基里巴斯"),
    KM("KM","科摩罗"),
    KN("KN","圣基茨和尼维斯"),
    KP("KP","朝鲜"),
    KR("KR","韩国"),
    KW("KW","科威特"),
    KY("KY","开曼群岛"),
    KZ("KZ","哈萨克斯坦"),
    LA("LA","老挝"),
    LB("LB","黎巴嫩"),
    LC("LC","圣卢西亚"),
    LI("LI","列支敦士登"),
    LK("LK","斯里兰卡"),
    LR("LR","利比里亚"),
    LS("LS","莱索托"),
    LT("LT","立陶宛"),
    LU("LU","卢森堡"),
    LV("LV","拉脱维亚"),
    LY("LY","利比亚"),
    MA("MA","摩洛哥"),
    MC("MC","摩纳哥"),
    MD("MD","摩尔多瓦"),
    ME("ME","黑山"),
    MF("MF","法属圣马丁"),
    MG("MG","马达加斯加"),
    MH("MH","马绍尔群岛"),
    MK("MK","马其顿"),
    ML("ML","马里"),
    MM("MM","缅甸"),
    MN("MN","蒙古"),
    MO("MO","澳门"),
    MP("MP","北马里亚纳群岛"),
    MQ("MQ","马提尼克"),
    MR("MR","毛里塔尼亚"),
    MS("MS","蒙塞拉特岛"),
    MT("MT","马耳他"),
    MU("MU","毛里求斯"),
    MV("MV","马尔代夫"),
    MW("MW","马拉维"),
    MX("MX","墨西哥"),
    MY("MY","马来西亚"),
    MZ("MZ","莫桑比克"),
    NA("NA","纳米比亚"),
    NC("NC","新喀里多尼亚"),
    NE("NE","尼日尔"),
    NF("NF","诺福克岛"),
    NG("NG","尼日利亚"),
    NI("NI","尼加拉瓜"),
    NL("NL","荷兰"),
    NO("NO","挪威"),
    NP("NP","尼泊尔"),
    NR("NR","瑙鲁"),
    NU("NU","纽埃"),
    NZ("NZ","新西兰"),
    OM("OM","阿曼"),
    PA("PA","巴拿马"),
    PE("PE","秘鲁"),
    PF("PF","法属波利尼西亚"),
    PG("PG","巴布亚新几内亚"),
    PH("PH","菲律宾"),
    PK("PK","巴基斯坦"),
    PL("PL","波兰"),
    PM("PM","圣皮埃尔和密克隆"),
    PN("PN","皮特凯恩群岛"),
    PR("PR","波多黎各"),
    PS("PS","巴勒斯坦"),
    PT("PT","葡萄牙"),
    PW("PW","帕劳"),
    PY("PY","巴拉圭"),
    QA("QA","卡塔尔"),
    RE("RE","留尼汪"),
    RO("RO","罗马尼亚"),
    RS("RS","塞尔维亚"),
    RU("RU","俄罗斯"),
    RW("RW","卢旺达"),
    SA("SA","沙特阿拉伯"),
    SB("SB","所罗门群岛"),
    SC("SC","塞舌尔"),
    SD("SD","苏丹"),
    SE("SE","瑞典"),
    SG("SG","新加坡"),
    SH("SH","圣赫勒拿"),
    SI("SI","斯洛文尼亚"),
    SJ("SJ","斯瓦尔巴群岛和扬马延岛"),
    SK("SK","斯洛伐克"),
    SL("SL","塞拉利昂"),
    SM("SM","圣马力诺"),
    SN("SN","塞内加尔"),
    SO("SO","索马里"),
    SR("SR","苏里南"),
    SS("SS","南苏丹"),
    ST("ST","圣多美和普林西比"),
    SV("SV","萨尔瓦多"),
    SX("SX","圣马尔滕(荷兰部分)"),
    SY("SY","叙利亚"),
    SZ("SZ","斯威士兰"),
    TC("TC","特克斯和凯科斯群岛"),
    TD("TD","乍得"),
    TF("TF","法属南部领地"),
    TG("TG","多哥"),
    TH("TH","泰国"),
    TJ("TJ","塔吉克斯坦"),
    TK("TK","托克劳"),
    TL("TL","东帝汶"),
    TM("TM","土库曼斯坦"),
    TN("TN","突尼斯"),
    TO("TO","汤加"),
    TR("TR","土耳其"),
    TT("TT","特立尼达和多巴哥"),
    TV("TV","图瓦卢"),
    TW("TW","中华民国（台湾）"),
    TZ("TZ","坦桑尼亚"),
    UA("UA","乌克兰"),
    UG("UG","乌干达"),
    UM("UM","美国本土外小岛屿"),
    US("US","美国"),
    UY("UY","乌拉圭"),
    UZ("UZ","乌兹别克斯坦"),
    VA("VA","梵蒂冈"),
    VC("VC","圣文森特和格林纳丁斯"),
    VE("VE","委内瑞拉"),
    VG("VG","英属维尔京群岛"),
    VI("VI","美属维尔京群岛"),
    VN("VN","越南"),
    VU("VU","瓦努阿图"),
    WF("WF","瓦利斯和富图纳"),
    WS("WS","萨摩亚"),
    YE("YE","也门"),
    YT("YT","马约特"),
    ZA("ZA","南非"),
    ZM("ZM","赞比亚"),
    ZW("ZW","津巴布韦");

    override fun toString(): String {
        return text
    }

    companion object {
        fun get(code: String?): Country? {
            return values().find { it.code == code }
        }
    }
}

enum class ReviseOrCancel(val label: String, val value: Int) {
    Amendment("修正", 2),
    Nullification("作废", 1);

    companion object {
        fun get(value: Int?): ReviseOrCancel? {
            return ReviseOrCancel.values().find { it.value == value }
        }
    }
}

enum class ReporterQualification(val label: String, val value: Int) {
    Physician("医生", 1),
    Pharmacist("药剂师", 2),
    Other("其他医疗保健专业人士", 3),
    Lawyer("律师", 4),
    Consumer("消费者或其他非医疗保健专业人士", 5);

    companion object {
        fun get(value: Int?): ReporterQualification? {
            return ReporterQualification.values().find { it.value == value }
        }
    }
}

enum class IntervalUnit(val code:String,val label:String?="",val labelEn:String? = ""){
    SECOND("s","秒","second"),
    MINUTE("min","分钟","minute"),
    HOUR("h","小时","hour"),
    DAY("d","天","day"),
    WEEK("wk","周","week"),
    MONTH("mo","月","month"),
    YEAR("a","年","year"),
    DECADE("10.a","纪","Decade"),
    CYCLICAL("{cyclical}","周期性(用药)","cyclical"),
    ASNECESSARY("{asnecessary}","必要时(用药)","asnecessary"),
    TOTAL("{total}","总计","total");
    companion object {
        fun get(code: String?): IntervalUnit? {
            return values().find { it.code == code }
        }
    }

}

enum class UcmcTime(val code:String,val label:String? = "",val labelEn:String=""){
    MINUTE("min","分钟","Minute"),
    HOUR("h","小时","Hour"),
    DAY("d","天","Day"),
    WEEK("wk","周","Week"),
    MONTH("mo","月","Month");
    companion object {
        fun get(code: String?): UcmcTime? {
            return values().find { it.code == code }
        }
    }
}

enum class R2IntervalUnitMapping(val valueR2: String, val valueR3:String?, val label: String) {
    Decade("800", "10.a","Decade"),
    Year("801", "a","Year"),
    Month("802", "mo","Month"),
    Week("803", "wk","Week"),
    Day("804", "d","Day"),
    Hour("805", "h","Hour"),
    Minute("806", "min","Minute"),
    Second("807", "s","Second"),
    Trimester("810", "{trimester}","Trimester"),
    Cyclical("811", "{cyclical}","Cyclical"),
    AsNecessary("812", "{asnecessary}","As Necessary"),
    Total("813", "{total}","Total");

    companion object {
        fun getByR2(value: String?): R2IntervalUnitMapping? {
            return values().find { it.valueR2 == value }
        }
    }
}

enum class R2TimeUnitMapping(val valueR2: String, val valueR3:String?, val label: String) {
    Decade("800", "{Decade}","Decade"),
    Year("801", "a","Year"),
    Month("802", "mo","Month"),
    Week("803", "wk","Week"),
    Day("804", "d","Day"),
    Hour("805", "h","Hour"),
    Minute("806", "min","Minute"),
    Second("807", "s","Second"),
    Trimester("810", "{trimester}","Trimester"),
    Cyclical("811", "{cyclical}","Cyclical"),
    AsNecessary("812", "{asnecessary}","As Necessary"),
    Total("813", "{total}","Total");

    companion object {
        fun getByR2(value: String?): R2TimeUnitMapping? {
            return values().find { it.valueR2 == value }
        }
    }
}

enum class AgeRange(val label: String, val value: Int) {
    Foetus("胎儿", 0),
    Neonate("婴儿（早产和足月新生儿）", 1),
    Infant("幼儿", 2),
    Child("儿童", 3),
    Adolescent("青少年", 4),
    Adult("成年", 5),
    Elderly("老年", 6);

    companion object {
        fun get(value: Int?): AgeRange? {
            return AgeRange.values().find { it.value == value }
        }
    }
}

enum class Sex(var code: Int, var text: String) {
    MALE(1, "男性"),
    FEMALE(2, "女性");

    override fun toString(): String {
        return text
    }

    companion object {
        fun get(code: Int?): Sex? {
            return Sex.values().find { it.code == code }
        }
    }
}

enum class ReporterTerm(val label: String, val value: Int) {
    R1("是，由报告者强调，非严重", 1),
    R2("否，报告者未强调，非严重", 2),
    R3("是，由报告者强调，严重", 3),
    R4("否，报告者未强调，严重", 4);

    companion object {
        fun get(value: Int?): ReporterTerm? {
            return ReporterTerm.values().find { it.value == value }
        }
    }
}

enum class EventResult(var code: Int, var text: String,var textAdr:String) {
    LEVEL1(1, "痊愈","痊愈"),
    LEVEL2(2, "好转/缓解","好转"),
    LEVEL3(3, "未好转/未缓解/持续","未好转/未缓解/持续"),
    LEVEL4(4, "痊愈但伴有后遗症","痊愈但伴有后遗症"),
    LEVEL5(5, "致死","致死"),
    LEVEL0(0, "未知","未知");

    override fun toString(): String {
        return text
    }

    companion object {
        fun get(code: Int?): EventResult? {
            return EventResult.values().find { it.code == code }
        }
        fun getByText(text:String?) : EventResult? {
            return EventResult.values().find { it.text == text || it.textAdr == text }
        }
    }
}


enum class DrugCharacterCn(var code: Int, var text: String) {
    SUSPECT(1,"怀疑用药"),
    CONCOMITANT(2,"合并用药"),
    INTERACTING(3,"相互作用"),
    NO_SUPPLY(4,"未给药");

    companion object {
        fun get(code: Int?): DrugCharacterCn? {
            return values().find { it.code == code }
        }
    }

    override fun toString(): String {
        return text
    }

}

enum class DoseUnitR2(val code: String,val r2: String){
    D001("kg","001"),
    D002("g","002"),
    D003("mg","003"),
    D004("ug","004"),
    D005("ng","005"),
    D006("pg","006"),
    D007("mg/kg","007"),
    D008("ug/kg","008"),
    D009("mg/m2","009"),
    D010("ug/m2","010"),
    D011("L","011"),
    D012("mL","012"),
    D013("uL","013"),
    D014("Bq","014"),
    D015("GBq","015"),
    D016("MBq","016"),
    D017("kBq","017"),
    D018("Ci","018"),
    D019("mCi","019"),
    D020("uCi","020"),
    D021("nCi","021"),
    D022("mol","022"),
    D023("mmol","023"),
    D024("umol","024"),
    D025("[iU]","025"),
    D026("k[iU]","026"),
    D027("M[iU]","027"),
    D028("[iU]/kg","028"),
    D029("meq","029"),
    D030("%","030"),
    D031("[drp]","031"),
    D032("{DF}","032");

    companion object {
        fun get(code: String?): DoseUnitR2? {
            return values().find { it.code == code }
        }

        fun getByR2(r2: String?): DoseUnitR2? {
            return values().find { it.r2 == r2 }
        }
    }

}

enum class DrugMeasureR2(var code: Int,val r2Code:Int, var text: String) {
    F1(1, 1,"Drug withdrawn"),
    F2(2, 2,"Dose reduced"),
    F3(3, 3,"Dose increased"),
    F4(4, 4,"Dose not changed"),
    F0(0, 5,"Unknown"),
    F9(9, 6,"Not applicable");

    override fun toString(): String {
        return text
    }

    companion object {
        fun get(code: Int?): DrugMeasureR2? {
            return values().find { it.code == code }
        }
        fun getByR2(code: Int?): DrugMeasureR2? {
            return values().find { it.code == code }
        }
    }
}

enum class ProductUseRecur(val label: String, val value: Int){
    One("是", 1),
    Two("否", 2),
    Three("未知", 3);
    companion object {
        fun get(value: Int?): ProductUseRecur? {
            return ProductUseRecur.values().find { it.value == value }
        }
    }
}
