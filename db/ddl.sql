
create table WIFI_INFO
(
	X_SWIFI_MGR_NO      TEXT constraint WIFI_INFO_PK primary key,
	X_SWIFI_WRDOFC      TEXT,
	X_SWIFI_MAIN_NM     TEXT,
	X_SWIFI_ADRES1      TEXT,
	X_SWIFI_ADRES2      TEXT,
	X_SWIFI_INSTL_FLOOR TEXT,
	X_SWIFI_INSTL_TY    TEXT,
	X_SWIFI_INSTL_MBY   TEXT,
	X_SWIFI_SVC_SE      TEXT,
	X_SWIFI_CMCWR       TEXT,
	X_SWIFI_CNSTC_YEAR  TEXT,
	X_SWIFI_INOUT_DOOR  TEXT,
	X_SWIFI_REMARS3     TEXT,
	LAT                 REAL,
	LNT                 REAL,
	WORK_DTTM           TEXT
);

create table POS_HISTORY
(
	ID integer constraint POS_HISTORY_PK primary key autoincrement,
	LAT REAL,
	LNT REAL,
	REG_DT TEXT
);

create table BOOK_MARK_GROUP
(
    ID integer constraint BOOK_MARK_GROUP primary key autoincrement,
    NAME TEXT,
    SORT_VALUE integer,
    REG_DT TEXT,
    UDT_DT TEXT
);

create table BOOK_MARK
(
    ID integer constraint BOOK_MARK_GROUP primary key autoincrement,
    X_SWIFI_MGR_NO TEXT,
    BOOK_MARK_GROUP_ID integer,
    REG_DT TEXT
);

