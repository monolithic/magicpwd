CREATE TABLE C2010100(C2010101 INTEGER,C2010102 VARCHAR(16) NOT NULL,C2010103 VARCHAR(8) NOT NULL,C2010104 VARCHAR(256),C2010105 VARCHAR(256),C2010106 VARCHAR(64),C2010107 VARCHAR(2048),C2010108 TIMESTAMP,C2010109 TIMESTAMP,PRIMARY KEY(C2010102,C2010103))
CREATE TABLE C2010200(C2010201 INTEGER,C2010202 INTEGER,C2010203 VARCHAR(16) NOT NULL PRIMARY KEY,C2010204 VARCHAR(16),C2010205 VARCHAR(256),C2010206 VARCHAR(256),C2010207 VARCHAR(64),C2010208 VARCHAR(2048),C2010209 TIMESTAMP,C201020A TIMESTAMP)

CREATE TABLE P30F0000(P30F0001 VARCHAR(16) NOT NULL PRIMARY KEY,P30F0002 VARCHAR(1024),P30F0003 TIMESTAMP)
CREATE TABLE P30F0100(P30F0101 INTEGER,P30F0102 INTEGER,P30F0103 INTEGER,P30F0104 VARCHAR(8),P30F0105 VARCHAR(16),P30F0106 VARCHAR(16),P30F0108 VARCHAR(16),P30F0107 VARCHAR(24),P30F0109 VARCHAR(256),P30F010A VARCHAR(1024),P30F010B VARCHAR(16),P30F010C VARCHAR(1024),P30F010D VARCHAR(16),P30F010E VARCHAR(1024),P30F010F VARCHAR(2048),PRIMARY KEY(P30F0104,P30F0105))
CREATE TABLE P30F0200(P30F0201 INTEGER,P30F0202 VARCHAR(16),P30F0203 VARCHAR(4096),PRIMARY KEY(P30F0201,P30F0202))
CREATE TABLE P30F0600(P30F0601 INTEGER,P30F0602 VARCHAR(16) PRIMARY KEY,P30F0603 VARCHAR(32),P30F0604 VARCHAR(256),P30F0605 VARCHAR(64),P30F0606 VARCHAR(2048))
CREATE TABLE P30F0700(P30F0701 INTEGER,P30F0702 INTEGER,P30F0703 INTEGER,P30F0704 INTEGER,P30F0705 INTEGER,P30F0706 INTEGER,P30F0707 INTEGER,P30F0708 VARCHAR(16) PRIMARY KEY,P30F0709 VARCHAR(16),P30F070A VARCHAR(16),P30F070B VARCHAR(256),P30F070C TIMESTAMP,P30F070D TIMESTAMP,P30F070E TIMESTAMP,P30F070F TIMESTAMP,P30F0710 INTEGER,P30F0711 VARCHAR(512),P30F0712 VARCHAR(1024),P30F0713 VARCHAR(2048))
CREATE TABLE P30F0800(P30F0801 INTEGER,P30F0802 INTEGER,P30F0803 VARCHAR(16) PRIMARY KEY,P30F0804 VARCHAR(128),P30F0805 VARCHAR(1024),P30F0806 VARCHAR(2048))

CREATE TABLE P30F1100(P30F1101 INTEGER,P30F1102 INTEGER,P30F1103 VARCHAR(16) NOT NULL PRIMARY KEY,P30F1104 VARCHAR(16),P30F1105 VARCHAR(256),P30F1106 VARCHAR(256),P30F1107 VARCHAR(2048),P30F1108 TIMESTAMP,P30F1109 TIMESTAMP)
CREATE TABLE P30F1200(P30F1201 INTEGER,P30F1202 VARCHAR(16) NOT NULL PRIMARY KEY,P30F1203 VARCHAR(16),P30F1204 VARCHAR(64),P30F1205 VARCHAR(256),P30F1206 VARCHAR(2048))
CREATE TABLE P30F2100(P30F2101 INTEGER,P30F2102 INTEGER,P30F2103 VARCHAR(16) NOT NULL PRIMARY KEY,P30F2104 VARCHAR(256),P30F2105 VARCHAR(256),P30F2106 VARCHAR(512),P30F2107 VARCHAR(2048),P30F2108 TIMESTAMP,P30F2109 TIMESTAMP)