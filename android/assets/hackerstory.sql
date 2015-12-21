DROP TABLE IF EXISTS "class";
CREATE TABLE "class" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "name" TEXT NOT NULL );
INSERT INTO "class" VALUES(1,'Warrior');
INSERT INTO "class" VALUES(2,'Thief');
INSERT INTO "class" VALUES(3,'Bowman');
INSERT INTO "class" VALUES(4,'Magician
');
INSERT INTO "class" VALUES(5,'Pirate
');
INSERT INTO "class" VALUES(6,'Beginner');
DROP TABLE IF EXISTS "inventory";
CREATE TABLE "inventory" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "slot_1_item_id" INTEGER, "slot_2_item_id" INTEGER, "slot_3_item_id" INTEGER, "slot_4_item_id" INTEGER, "slot_5_item_id" INTEGER, "slot_6_item_id" INTEGER, "slot_7_item_id" INTEGER, "slot_8_item_id" INTEGER, "slot_9_item_id" INTEGER);
INSERT INTO "inventory" VALUES(1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
DROP TABLE IF EXISTS "item";
CREATE TABLE "item" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "name" TEXT, "image" TEXT, "value" INTEGER NOT NULL  DEFAULT 0);
DROP TABLE IF EXISTS "job";
CREATE TABLE "job" ("id" INTEGER PRIMARY KEY  NOT NULL ,"class_id" INTEGER NOT NULL ,"advancement" INTEGER NOT NULL  DEFAULT (null) , "name" TEXT);
INSERT INTO "job" VALUES(1,6,0,'Beginner');
INSERT INTO "job" VALUES(2,1,1,'Swordman');
INSERT INTO "job" VALUES(3,1,2,'Spearman
');
INSERT INTO "job" VALUES(4,1,3,'Berserker');
INSERT INTO "job" VALUES(5,1,4,'Dark Knight');
DROP TABLE IF EXISTS "player";
CREATE TABLE "player" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "job_id" , "inventory_id" , "level" , "experience" );
INSERT INTO "player" VALUES(1,4,1,78,243);
