#!/bin/sh
#
# NAME
#   nlslang.sh
#
# DESCRIPTION
#   This script generates the NLS_LANG value derived from the target
#   system's locale configuration.
#
#   Mapping rule from Linux locale to NLS_LANG follows.
#
#   A. LANGUAGE and TERRITORY
#
#    (1) "AMERICAN_AMERICA" when the locale doesn't map to any
#         Oracle LANGUAGE and TERROTORY
#    (2) "AMERICAN_<territory>" when the locale doesn't map to Oracle
#        LANGUAGE but maps to TERRITORY
#    (3) "<language>_<default-territory-of-language>" when the locale
#        maps to Oracle LANGUAGE but not to TERRITORY
#    (4) "<language>_<territory>" when the locale maps to both Oracle
#        LANGUAGE and TERRITORY
#
#   B. CHARACTERSET
#
#    (1) Map from the locale charmap to Oracle CHARACTERSET using the
#        following table.
#    (2) AL32UTF8 is used when the charmap is not found in the table
#
#        charmap --> CHARACTERSET
#    -----------     --------------
#          UTF-8     AL32UTF8
#     ISO-8859-1     WE8ISO8859P1
#     ISO-8859-2     EE8ISO8859P2
#     ISO-8859-3     SE8ISO8859P3
#     ISO-8859-4     NEE8ISO8859P4
#     ISO-8859-5     CL8ISO8859P5
#     ISO-8859-6     AR8ISO8859P6
#     ISO-8859-7     EL8ISO8859P7
#     ISO-8859-8     IW8ISO8859P8
#     ISO-8859-9     WE8ISO8859P9
#    ISO-8859-13     BLT8ISO8859P13
#    ISO-8859-14     CEL8ISO8859P14
#    ISO-8859-15     WE8ISO8859P15
#           BIG5     ZHT16BIG5
#     BIG5-HKSCS     ZHT16HKSCS
#         CP1251     CL8MSWIN1251
#         CP1255     IW8MSWIN1255
#         EUC-JP     JA16EUC
#         EUC-KR     KO16KSC5601
#         EUC-TW     ZHT32EUC
#        GB18030     ZHS32GB18030
#         GB2312     ZHS16CGB231280
#            GBK     ZHS16GBK
#        TIS-620     TH8TISASCII
#         KOI8-R     CL8KOI8R
#         KOI8-U     CL8KOI8U

#
# MODIFIED   (MM/DD/YY)
#   mtozawa   11/22/05 - rollback the workawound of bug 4598613 (ORA-02248 
#                        with SWITZERLAND) for XE production 
#   mtozawa   11/03/05 - workaround bug 4598613 (ORA-02248 with SWITZERLAND 
#                        territory) for XE beta 
#   mtozawa   10/24/05 - bug 4667489 - change CIS to RUSSIA 
#   svaggu    10/21/05 - svaggu_new_elements
#   mtozawa   10/04/05 - Created
#

# Determine the CHARACTERSET part of NLS_LANG
# we query the charmap of the current locale, and map it to the
# corresponding Oracle character set name.
#
case "`locale charmap`" in
  UTF-8)       charset='AL32UTF8';;
  ISO-8859-1)  charset='WE8ISO8859P1';;
  ISO-8859-2)  charset='EE8ISO8859P2';;
  ISO-8859-3)  charset='SE8ISO8859P3';;
  ISO-8859-4)  charset='NEE8ISO8859P4';;
  ISO-8859-5)  charset='CL8ISO8859P5';;
  ISO-8859-6)  charset='AR8ISO8859P6';;
  ISO-8859-7)  charset='EL8ISO8859P7';;
  ISO-8859-8)  charset='IW8ISO8859P8';;
  ISO-8859-9)  charset='WE8ISO8859P9';;
  ISO-8859-13) charset='BLT8ISO8859P13';;
  ISO-8859-14) charset='CEL8ISO8859P14';;
  ISO-8859-15) charset='WE8ISO8859P15';;
  BIG5)        charset='ZHT16BIG5';;
  BIG5-HKSCS)  charset='ZHT16HKSCS';;
  CP1251)      charset='CL8MSWIN1251';;
  CP1255)      charset='IW8MSWIN1255';;
  EUC-JP)      charset='JA16EUC';;
  EUC-KR)      charset='KO16KSC5601';;
  EUC-TW)      charset='ZHT32EUC';;
  GB18030)     charset='ZHS32GB18030';;
  GB2312)      charset='ZHS16CGB231280';;
  GBK)         charset='ZHS16GBK';;
  TIS-620)     charset='TH8TISASCII';;
  KOI8-R)      charset='CL8KOI8R';;
  KOI8-U)      charset='CL8KOI8U';;
  *)           charset='AL32UTF8';;
esac

# Detertmine the LANGUAGE_TERRITORY part of NLS_LANG
# we derive it from the current locale by inspecting the LC_ALL and
# the LANG environment variable. Other LC_* environment variables
# are not inspected.
#
if [ -n "$LC_ALL" ]; then
  locale=$LC_ALL
elif [ -n "$LANG" ]; then
  locale=$LANG
else
  locale=
fi

# Truncate .<charset> or @<variant>.
# locale vi_VN.tcvn requires explicitly reset OS default locale
# in order for the sed to run properly.  unset LC_ALL is not enough.
# Without it, sed: -e expression #1 Unterminated 's' command error
# is printed.
#
export LC_ALL=C
locale=`echo $locale | sed 's/[.@].*$//'`

# Map it to Oracle LANGUAGE_TERRITORY
#
default_language_territory='AMERICAN_AMERICA';
default_language='AMERICAN';

case "$locale" in
# Afrikaans - SOUTH AFRICA // no mapping to Oracle LANGUAGE
  af_ZA) nlslang=${default_language}'_SOUTH AFRICA';;

# Aragonese - SPAIN // no mapping to Oracle LANGUAGE
  an_ES) nlslang=${default_language}'_SPAIN';;

# Arabic - UNITED ARAB EMIRATES
  ar_AE) nlslang='ARABIC_UNITED ARAB EMIRATES';;

# Arabic - BAHRAIN
  ar_BH) nlslang='ARABIC_BAHRAIN';;

# Arabic - ALGERIA
  ar_DZ) nlslang='ARABIC_ALGERIA';;

# Arabic - EGYPT
  ar_EG) nlslang='ARABIC_EGYPT';;

# Arabic - INDIA
  ar_IN) nlslang='ARABIC_INDIA';;

# Arabic - IRAQ
  ar_IQ) nlslang='ARABIC_IRAQ';;

# Arabic - JORDAN
  ar_JO) nlslang='ARABIC_JORDAN';;

# Arabic - KUWAIT
  ar_KW) nlslang='ARABIC_KUWAIT';;

# Arabic - LEBANON
  ar_LB) nlslang='ARABIC_LEBANON';;

# Arabic - LIBYAN ARAB JAMAHIRIYA
  ar_LY) nlslang='ARABIC_LIBYA';;

# Arabic - MOROCCO
  ar_MA) nlslang='ARABIC_MOROCCO';;

# Arabic - OMAN
  ar_OM) nlslang='ARABIC_OMAN';;

# Arabic - QATAR
  ar_QA) nlslang='ARABIC_QATAR';;

# Arabic - SAUDI ARABIA
  ar_SA) nlslang='ARABIC_SAUDI ARABIA';;

# Arabic - SUDAN
  ar_SD) nlslang='ARABIC_SUDAN';;

# Arabic - SYRIAN ARAB REPUBLIC
  ar_SY) nlslang='ARABIC_SYRIA';;

# Arabic - TUNISIA
  ar_TN) nlslang='ARABIC_TUNISIA';;

# Arabic - YEMEN
  ar_YE) nlslang='ARABIC_YEMEN';;

# Byelorussian - BELARUS // no mapping to Oracle LANGUAGE & TERRITORY
  be_BY) nlslang=${default_language_territory};;

# Bulgarian - BULGARIA
  bg_BG) nlslang='BULGARIAN_BULGARIA';;

# Breton - FRANCE // // no mapping to Oracle LANGUAGE
  br_FR) nlslang=${default_language}'_FRANCE';;

# Bosnian - BOSNIA AND HERZEGOVINA // no mapping to Oracle LANGUAGE & TERRITORY
  bs_BA) nlslang=${default_language_territory};;

# Catalan - SPAIN
  ca_ES) nlslang='CATALAN_SPAIN';;

# Czech - CZECH REPUBLIC
  cs_CZ) nlslang='CZECH_CZECH REPUBLIC';;

# Welsh - UNITED KINGDOM // no mapping to Oracle LANGUAGE
  cy_GB) nlslang=${default_language}'_UNITED KINGDOM';;

# Danish - DENMARK
  da_DK) nlslang='DANISH_DENMARK';;

# German - AUSTRIA
  de_AT) nlslang='GERMAN_AUSTRIA';;

# German - BELGIUM
  de_BE) nlslang='GERMAN_BELGIUM';;

# German - SWITZERLAND
  de_CH) nlslang='GERMAN_SWITZERLAND';;

# German - GERMANY
  de_DE) nlslang='GERMAN_GERMANY';;

# German - LUXEMBOURG
  de_LU) nlslang='GERMAN_LUXEMBOURG';;

# Greek - GREECE
  el_GR) nlslang='GREEK_GREECE';;

# English - AUSTRALIA
  en_AU) nlslang='ENGLISH_AUSTRALIA';;

# English - BOTSWANA // no mapping to Oracle TERRITORY
  en_BW) nlslang='ENGLISH_UNITED KINGDOM';;

# English - CANADA
  en_CA) nlslang='ENGLISH_CANADA';;

# English - DENMARK
  en_DK) nlslang='ENGLISH_DENMARK';;

# English - UNITED KINGDOM
  en_GB) nlslang='ENGLISH_UNITED KINGDOM';;

# English - HONG KONG
  en_HK) nlslang='ENGLISH_HONG KONG';;

# English - IRELAND
  en_IE) nlslang='ENGLISH_IRELAND';;

# English - INDIA
  en_IN) nlslang='ENGLISH_INDIA';;

# English - NEW ZEALAND
  en_NZ) nlslang='ENGLISH_NEW ZEALAND';;

# English - PHILIPPINES
  en_PH) nlslang='ENGLISH_PHILIPPINES';;

# English - SINGAPORE
  en_SG) nlslang='ENGLISH_SINGAPORE';;

# English/American - UNITED STATES
  en_US) nlslang='AMERICAN_AMERICA';;

# English - SOUTH AFRICA
  en_ZA) nlslang='ENGLISH_SOUTH AFRICA';;

# English - ZIMBABWE // no mapping to Oracle TERRITORY
  en_ZW) nlslang='ENGLISH_UNITED KINGDOM';;

# Spanish - ARGENTINA
  es_AR) nlslang='LATIN AMERICAN SPANISH_ARGENTINA';;

# Spanish - BOLIVIA // no mapping to Oracle TERRITORY
  es_BO) nlslang='LATIN AMERICAN SPANISH_AMERICA';;

# Spanish - CHILE
  es_CL) nlslang='LATIN AMERICAN SPANISH_CHILE';;

# Spanish - COLOMBIA
  es_CO) nlslang='LATIN AMERICAN SPANISH_COLOMBIA';;

# Spanish - COSTA RICA
  es_CR) nlslang='LATIN AMERICAN SPANISH_COSTA RICA';;

# Spanish - DOMINICAN REPUBLIC
  es_DO) nlslang='LATIN AMERICAN SPANISH_AMERICA';;

# Spanish - ECUADOR
  es_EC) nlslang='LATIN AMERICAN SPANISH_ECUADOR';;

# Spanish - SPAIN
  es_ES) nlslang='SPANISH_SPAIN';;

# Spanish - GUATEMALA
  es_GT) nlslang='LATIN AMERICAN SPANISH_GUATEMALA';;

# Spanish - HONDURAS // no mapping to Oracle TERRITORY
  es_HN) nlslang='LATIN AMERICAN SPANISH_AMERICA';;

# Spanish - MEXICO
  es_MX) nlslang='MEXICAN SPANISH_MEXICO';;

# Spanish - NICARAGUA
  es_NI) nlslang='LATIN AMERICAN SPANISH_NICARAGUA';;

# Spanish - PANAMA
  es_PA) nlslang='LATIN AMERICAN SPANISH_PANAMA';;

# Spanish - PERU
  es_PE) nlslang='LATIN AMERICAN SPANISH_PERU';;

# Spanish - PUERTO RICO
  es_PR) nlslang='LATIN AMERICAN SPANISH_PUERTO RICO';;

# Spanish - PARAGUAY // no mapping to Oracle TERRITORY
  es_PY) nlslang='LATIN AMERICAN SPANISH_AMERICA';;

# Spanish - EL SALVADOR
  es_SV) nlslang='LATIN AMERICAN SPANISH_EL SALVADOR';;

# Spanish - UNITED STATES
  es_US) nlslang='LATIN AMERICAN SPANISH_AMERICA';;

# Spanish - URUGUAY // no mapping to Oracle TERRITORY
  es_UY) nlslang='LATIN AMERICAN SPANISH_AMERICA';;

# Spanish - VENEZUELA
  es_VE) nlslang='LATIN AMERICAN SPANISH_VENEZUELA';;

# Estonian - ESTONIA
  et_EE) nlslang='ESTONIAN_ESTONIA';;

# Basque - SPAIN // no mapping to Oracle LANGUAGE
  eu_ES) nlslang=${default_language}'_SPAIN';;

# Persian - IRAN, ISLAMIC REPUBLIC OF // no mapping to Oracle LANGUAGE & TERRITORY
  fa_IR) nlslang=${default_language_territory};;

# Finnish - FINLAND
  fi_FI) nlslang='FINNISH_FINLAND';;

# Faeroese - FAROE ISLANDS // no mapping to Oracle LANGUAGE & TERRITORY
  fo_FO) nlslang=${default_language_territory};;

# French - BELGIUM
  fr_BE) nlslang='FRENCH_BELGIUM';;

# French - CANADA
  fr_CA) nlslang='CANADIAN FRENCH_CANADA';;

# French - SWITZERLAND
  fr_CH) nlslang='FRENCH_SWITZERLAND';;

# French - FRANCE
  fr_FR) nlslang='FRENCH_FRANCE';;

# French - LUXEMBOURG
  fr_LU) nlslang='FRENCH_LUXEMBOURG';;

# Irish - IRELAND // no mapping to Oracle LANGUAGE
  ga_IE) nlslang=${default_language}'_IRELAND';;

# Galician - SPAIN // no mapping to Oracle LANGUAGE
  gl_ES) nlslang=${default_language}'_SPAIN';;

# Manx - UNITED KINGDOM // no mapping to Oracle LANGUAGE
  gv_GB) nlslang=${default_language}'_UNITED KINGDOM';;

# Hebrew - ISRAEL
  he_IL) nlslang='HEBREW_ISRAEL';;

# Hindi - INDIA
  hi_IN) nlslang='HINDI_INDIA';;

# Croatian - CROATIA
  hr_HR) nlslang='CROATIAN_CROATIA';;

# Hungarian - HUNGARY
  hu_HU) nlslang='HUNGARIAN_HUNGARY';;

# Indonesian - INDONESIA
  id_ID) nlslang='INDONESIAN_INDONESIA';;

# Icelandic - ICELAND
  is_IS) nlslang='ICELANDIC_ICELAND';;

# Italian - SWITZERLAND
  it_CH) nlslang='ITALIAN_SWITZERLAND';;

# Italian - ITALY
  it_IT) nlslang='ITALIAN_ITALY';;

# Hebrew - ISRAEL
  iw_IL) nlslang='HEBREW_ISRAEL';;

# Japanese - JAPAN
  ja_JP) nlslang='JAPANESE_JAPAN';;

# Georgian - GEORGIA // no mapping to Oracle LANGUAGE & TERRITORY
  ka_GE) nlslang=${default_language_territory};;

# Greenlandic - GREENLAND // no mapping to Oracle LANGUAGE & TERRITORY
  kl_GL) nlslang=${default_language_territory};;

# Korean - KOREA, REPUBLIC OF
  ko_KR) nlslang='KOREAN_KOREA';;

# Cornish - UNITED KINGDOM // no mapping to Oracle LANGUAGE
  kw_GB) nlslang=${default_language}'_UNITED KINGDOM';;

# Lao - LAO PEOPLE'S DEMOCRATIC REPUBLIC // no mapping to Oracle LANGUAGE & TERRITORY
  lo_LA) nlslang=${default_language_territory};;

# Lithuanian - LITHUANIA
  lt_LT) nlslang='LITHUANIAN_LITHUANIA';;

# Latvian/Lettish - LATVIA
  lv_LV) nlslang='LATVIAN_LATVIA';;

# Maori - NEW ZEALAND // no mapping to Oracle LANGUAGE
  mi_NZ) nlslang=${default_language}'_NEW ZEALAND';;

# Macedonian - MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF
  mk_MK) nlslang='MACEDONIAN_FYR MACEDONIA';;

# Marathi - INDIA
  mr_IN) nlslang='MARATHI_INDIA';;

# Malay - MALAYSIA
  ms_MY) nlslang='MALAY_MALAYSIA';;

# Maltese - MALTA // no mapping to Oracle LANGUAGE & TERRITORY
  mt_MT) nlslang=${default_language_territory};;

# Norwegian Bokmal/Bokmal/Norwegian - NORWAY
  nb_NO) nlslang='NORWEGIAN_NORWAY';;

# Dutch - BELGIUM
  nl_BE) nlslang='DUTCH_BELGIUM';;

# Dutch - NETHERLANDS
  nl_NL) nlslang='DUTCH_THE NETHERLANDS';;

# Norwegian Nynorsk/Nynorsk/Norwegian - NORWAY
  nn_NO) nlslang='NORWEGIAN_NORWAY';;

# Norwegian - NORWAY
  no_NO) nlslang='NORWEGIAN_NORWAY';;

# Occitan - FRANCE // no mapping to Oracle LANGUAGE
  oc_FR) nlslang=${default_language}'_FRANCE';;

# Polish - POLAND
  pl_PL) nlslang='POLISH_POLAND';;

# Portuguese - BRAZIL
  pt_BR) nlslang='BRAZILIAN PORTUGUESE_BRAZIL';;

# Portuguese - PORTUGAL
  pt_PT) nlslang='PORTUGUESE_PORTUGAL';;

# Romanian - ROMANIA
  ro_RO) nlslang='ROMANIAN_ROMANIA';;

# Russian - RUSSIAN FEDERATION
  ru_RU) nlslang='RUSSIAN_RUSSIA';;

# Russian - UKRAINE
  ru_UA) nlslang='RUSSIAN_UKRAINE';;

# Northern Sami - NORWAY // no mappig to Oracle LANGUAGE
  se_NO) nlslang=${default_language}'_NORWAY';;

# Slovak - SLOVAKIA
  sk_SK) nlslang='SLOVAK_SLOVAKIA';;

# Slovenian - SLOVENIA
  sl_SI) nlslang='SLOVENIAN_SLOVENIA';;

# Albanian - ALBANIA // no mapping to Oracle LANGUAGE & TERRITORY
  sq_AL) nlslang=${default_language_territory};;

# Serbian - // unknown ISO-3166 code 'YU'
  sr_YU) nlslang='LATIN SERBIAN_SERBIA AND MONTENEGRO';;

# Swedish - FINLAND
  sv_FI) nlslang='SWEDISH_FINLAND';;

# Swedish - SWEDEN
  sv_SE) nlslang='SWEDISH_SWEDEN';;

# Thai - THAILAND
  th_TH) nlslang='THAI_THAILAND';;

# Tagalog - PHILIPPINES // no mappig to Oracle LANGUAGE
  tl_PH) nlslang=${default_language}'_PHILIPPINES';;

# Turkish - TURKEY
  tr_TR) nlslang='TURKISH_TURKEY';;

# Ukrainian - UKRAINE
  uk_UA) nlslang='UKRAINIAN_UKRAINE';;

# Vietnamese - VIET NAM
  vi_VN) nlslang='VIETNAMESE_VIETNAM';;

# Walloon - BELGIUM // no mappig to Oracle LANGUAGE
  wa_BE) nlslang=${default_language}'_BELGIUM';;

# Yiddish - UNITED STATES // no mappig to Oracle LANGUAGE
  yi_US) nlslang=${default_language}'_AMERICA';;

# Chinese - CHINA
  zh_CN) nlslang='SIMPLIFIED CHINESE_CHINA';;

# Chinese - HONG KONG
  zh_HK) nlslang='TRADITIONAL CHINESE_HONG KONG';;

# Chinese - TAIWAN, PROVINCE OF CHINA
  zh_TW) nlslang='TRADITIONAL CHINESE_TAIWAN';;

# we also support the locale aliases that are defined
# in /usr/share/locale/locale.alias

# alias of no_NO
  bokmal)     nlslang='NORWEGIAN_NORWAY';;

# alias of no_NO
  bokmål)     nlslang='NORWEGIAN_NORWAY';;

# alias of ca_ES
  catalan)    nlslang='CATALAN_SPAIN';;

# alias of hr_HR
  croatian)   nlslang='CROATIAN_CROATIA';;

# alias of cs_CZ
  czech)      nlslang='CZECH_CZECH REPUBLIC';;

# alias of da_DK
  danish)     nlslang='DANISH_DENMARK';;

# alias of da_DK
  dansk)      nlslang='DANISH_DENMARK';;

# alias of de_DE
  deutsch)    nlslang='GERMAN_GERMANY';;

# alias of nl_NL
  dutch)      nlslang='DUTCH_THE NETHERLANDS';;

# alias of et_EE
  eesti)      nlslang='ESTONIAN_ESTONIA';;

# alias of et_EE
  estonian)   nlslang='ESTONIAN_ESTONIA';;

# alias of fi_FI
  finnish)    nlslang='FINNISH_FINLAND';;

# alias of fr_FR
  français)   nlslang='FRENCH_FRANCE';;

# alias of fr_FR
  french)     nlslang='FRENCH_FRANCE';;

# alias of gl_ES
  galego)     nlslang=${default_language}'_SPAIN';;

# alias of gl_ES
  galician)   nlslang=${default_language}'_SPAIN';;

# alias of de_DE
  german)     nlslang='GERMAN_GERMANY';;

# alias of el_GR
  greek)      nlslang='GREEK_GREECE';;

# alias of he_IL
  hebrew)     nlslang='HEBREW_ISRAEL';;

# alias of hr_HR
  hrvatski)   nlslang='CROATIAN_CROATIA';;

# alias of hu_HU
  hungarian)  nlslang='HUNGARIAN_HUNGARY';;

# alias of is_IS
  icelandic)  nlslang='ICELANDIC_ICELAND';;

# alias of it_IT
  italian)    nlslang='ITALIAN_ITALY';;

# alias of ja_JP
  japanese)   nlslang='JAPANESE_JAPAN';;

# alias of ko_KR
  korean)     nlslang='KOREAN_KOREA';;

# alias of lt_LT
  lithuanian) nlslang='LITHUANIAN_LITHUANIA';;

# alias of no_NO
  norwegian)  nlslang='NORWEGIAN_NORWAY';;

# alias of nn_NO
  nynorsk)    nlslang='NORWEGIAN_NORWAY';;

# alias of pl_PL
  polish)     nlslang='POLISH_POLAND';;

# alias of pt_PT
  portuguese) nlslang='PORTUGUESE_PORTUGAL';;

# alias of ro_RO
  romanian)   nlslang='ROMANIAN_ROMANIA';;

# alias of ru_RU
  russian)    nlslang='RUSSIAN_RUSSIA';;

# alias of sk_SK
  slovak)     nlslang='SLOVAK_SLOVAKIA';;

# alias of sl_SI
  slovene)    nlslang='SLOVENIAN_SLOVENIA';;

# alias of sl_SI
  slovenian)  nlslang='SLOVENIAN_SLOVENIA';;

# alias of es_ES
  spanish)    nlslang='SPANISH_SPAIN';;

# alias of sv_SE
  swedish)    nlslang='SWEDISH_SWEDEN';;

# alias of th_TH
  thai)       nlslang='THAI_THAILAND';;

# alias of tr_TR
  turkish)    nlslang='TURKISH_TURKEY';;

# default
  *)          nlslang=${default_language_territory};;
esac

# construct the NLS_LANG
#
NLS_LANG=${nlslang}.${charset}

echo $NLS_LANG
