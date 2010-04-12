create table RFMS_TICKET
(
  TICKET_ID          NUMBER(10) not null,
  TICKET_SERIAL      VARCHAR2(255),
  TICKET_NAME        VARCHAR2(255),
  TYPE               CHAR(1),
  PAR_VALUE          NUMBER(14),
  SEND_COUNT         NUMBER(14) not null,
  USE_COUNT          NUMBER(14) not null,
  STATUS             CHAR(1),
  CREATE_DATE        DATE,
  UPDATE_DATE        DATE,
  OPERATOR_ID        NUMBER(14),
  PAR_ZHEKOU         NUMBER(4),
  BEGIN_DATE         DATE,
  END_DATE           DATE,
  TICKET_COUNT       NUMBER(14),
  TARGET_MEMBER_TYPE VARCHAR2(255),
  USE_RULE           VARCHAR2(255),
  OHTER_INFO         CHAR(1),
  MERCHANT_ID        NUMBER(14),
  PRIMARY KEY(TICKET_ID)
);
create sequence RFMS_TICKET_SEQ;

create table RFMS_TICKET_DETAIL(
DETAIL_ID NUMBER(14),
TICKET_ID NUMBER(14),
SEQ_NUMBER VARCHAR(64),
STATUS  NUMBER(2),--1.初始化 2，已经下发或者领用 3.已经消费 4.已经失效
MOBILE VARCHAR(14),
SEND_DATE DATE,
SEND_OPERATOR_ID NUMBER(14),
USE_DATE DATE,
USER_POS NUMBER(14),
PRIMARY KEY(DETAIL_ID)
);
create sequence RFMS_TICKET_DETAIL_SEQ;

CREATE TABLE RFMS_TICKET_BIND(
BIND_ID NUMBER(14),
TICKET_ID NUMBER(14),
POS_ID NUMBER(14),
CREATE_DATE NUMBER(14),
UPDATE_DATE NUMBER(14),
PRIMARY KEY(BIND_ID)
);
create sequence RFMS_TICKET_BIND_SEQ;

CREATE TABLE RFMS_MERCHANT_PAYMENT(
PAYMENT_ID NUMBER(14),
METCHANT_ID NUMBER(14),
AMOUNT NUMBER(14),--充值金额
PAYMENT_DATE DATE,
OPERATOR_ID NUMBER(14),
PAYMENT_TYPE NUMBER(14),--1.充值 2.消费
NOTES varchar(1024),
PRIMARY KEY(PAYMENT_ID)
);
create sequence RFMS_MERCHANT_PAYMENT_SEQ;

create table RFMS_MEMBER
(
  ID          NUMBER(10) not null,
  MOBILE      VARCHAR2(255),
  NAME        VARCHAR2(120),
  SEX         CHAR(1),
  BIRTH_DATE  DATE,
  TEL         VARCHAR2(255),
  EMAIL       VARCHAR2(255),
  ADDRESS     VARCHAR2(255),
  ZIPCODE     VARCHAR2(255),
  STATUS      CHAR(1),
  MEMBER_TYPE NUMBER(2),
  CREATE_DATE DATE,
  UPDATE_DATE DATE,
  OPERATOR_ID NUMBER(14),
  PWD    VARCHAR2(36),
  PRIMARY KEY(ID)
);
create sequence RFMS_MEMBER_SEQ;

create table RFMS_SMS
(
  ID          NUMBER(10) not null,
  MOBILE      VARCHAR2(255),
  MESSAGE     VARCHAR2(120),
  STATUS      CHAR(1),
  SEND_DATE   DATE,
  CREATE_DATE DATE,
  UPDATE_DATE DATE,
  OPERATOR_ID NUMBER(14),
  PRIMARY KEY(ID)
);
create sequence RFMS_SMS_SEQ;

alter table rfms.rfms_merchant add amount number(14) default 0;

--pos签到表
create table RFCS_POS_SIGNIN(
SIGNIN_ID NUMBER(14),
OPERATOR_ID NUMBER(14),
POS_ID NUMBER(14),
PIN_KEY VARCHAR2(256),
MAC_KEY VARCHAR2(256),
SIGNIN_TIME DATE,
PRIMARY KEY(SIGNIN_ID)
);
CREATE SEQUENCE RFCS_POS_SIGNIN_SEQ;

--交易记录表
CREATE TABLE rfms.RFCS_TRADE
(
TRANS_NO NUMBER(14) not null,
SYSTEM_ID NUMBER(14),
SYSTEM_CODE VARCHAR2(256),
MERCHANT_ID NUMBER(14),
MERCHANT_CODE VARCHAR2(256),
POS_ID NUMBER(14),
POS_CODE VARCHAR2(256),
POS_TRANS_NO NUMBER(14),
MERCHANT_TRANS_NO VARCHAR2(256),
CARD_TRANSNO NUMBER(14),
TRADE_TYPE NUMBER(14),
TICKET_NO  VARCHAR2(256),
RND VARCHAR2(256),
AMOUNT NUMBER(14),
STATUS varchar2(14),
TRANS_DATE DATE,
RELATE_TRANSNO NUMBER(14),
ERR_CODE VARCHAR2(12),
ERR_DISC VARCHAR2(256),
POS_ERR_CODE VARCHAR2(12),
POS_ERR_DISC VARCHAR2(256),
POS_TRADE_TYPE varchar2(14),
CREATE_TIME DATE,
REMARK VARCHAR2(2000),
PRIMARY KEY(TRANS_NO)
);
create sequence RFCS_TRADE_SEQ;



alter table rfms.rfms_merchant_pos add pinkey varchar2(256);
alter table rfms.rfms_merchant_pos add mackey varchar2(256);
alter table rfms.rfms_merchant_pos add key_Change_TIME date;
