DROP TABLE IF EXISTS tbl_account;
CREATE TABLE tbl_account (
    accountID INTEGER PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    patientInfo VARCHAR NULL,
    level INTEGER NULL,
    accountType VARCHAR
);
INSERT INTO tbl_account VALUES (NULL,'Michael','Jackson','jackson@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Relative',0,'RELATIVE');
INSERT INTO tbl_account VALUES (NULL,'Billy','Mitchell','mitchell@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Patient',0,'PATIENT');
INSERT INTO tbl_account VALUES (NULL,'Ben','Dover','dover@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Patient',0,'PATIENT');
INSERT INTO tbl_account VALUES (NULL,'Harald','Hidepain','hidepain@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Patient',0,'PATIENT');
INSERT INTO tbl_account VALUES (NULL,'Jon','Stark','stark@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Patient',0,'PATIENT');
INSERT INTO tbl_account VALUES (NULL,'Tim','Shore','shore@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Patient',0,'PATIENT');
INSERT INTO tbl_account VALUES (NULL,'Peter','Reber','reber@mail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','Ich bin ein Relative',0,'RELATIVE');

DROP TABLE IF EXISTS tbl_friendship;
CREATE TABLE tbl_friendship (
    accountID1 INTEGER,
    accountID2 INTEGER,

    FOREIGN KEY (accountID1)
        REFERENCES tbl_account (accountID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    FOREIGN KEY (accountID2)
        REFERENCES tbl_account (accountID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

INSERT INTO tbl_friendship VALUES (1,2);
INSERT INTO tbl_friendship VALUES (1,3);

INSERT INTO tbl_friendship VALUES (7,4);
INSERT INTO tbl_friendship VALUES (7,5);

DROP TABLE IF EXISTS tbl_participants;
CREATE TABLE tbl_participants (
    accountID INTEGER,
    eventID INTEGER,

    FOREIGN KEY (accountID)
        REFERENCES tbl_account (accountID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    FOREIGN KEY (eventID)
        REFERENCES tbl_event (eventID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS tbl_post;
CREATE TABLE tbl_post (
    accountID INTEGER,
    eventID INTEGER,
    content VARCHAR,
    creationTime DATE,

    FOREIGN KEY (accountID)
        REFERENCES tbl_account (accountID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    FOREIGN KEY (eventID)
        REFERENCES tbl_event (eventID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS tbl_event;
CREATE TABLE tbl_event (
    eventID INTEGER PRIMARY KEY,
    info VARCHAR,
    isPublic BOOLEAN,
    rating INTEGER,
    state VARCHAR,
    maxParticipants INTEGER,
    eventTemplateID INTEGER,
    imageID INTEGER,

    FOREIGN KEY (eventTemplateID)
        REFERENCES tbl_eventTemplate (eventTemplateID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    FOREIGN KEY (imageID)
        REFERENCES tbl_image (imageID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS tbl_image;
CREATE TABLE tbl_image (

	eventID INTEGER,
    image BLOB,
    
     FOREIGN KEY (eventID)
        REFERENCES tbl_event (eventID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS tbl_eventTemplate;
CREATE TABLE tbl_eventTemplate (
    eventTemplateID INTEGER PRIMARY KEY,
    title VARCHAR,
    description VARCHAR,
    rating DOUBLE
);

DROP TABLE IF EXISTS tbl_tagEventTemplateREL;
CREATE TABLE tbl_tagEventTemplateREL (
    tagID INTEGER,
    eventTemplateID INTEGER,

    FOREIGN KEY (tagID)
        REFERENCES tbl_tag (tagID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    FOREIGN KEY (eventTemplateID)
        REFERENCES tbl_eventTemplate (eventTemplateID)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS tbl_tag;
CREATE TABLE tbl_tag (
    tagID INTEGER PRIMARY KEY,
    tag_name VARCHAR
);

INSERT INTO tbl_tag VALUES (NULL,'Outdoor');
INSERT INTO tbl_tag VALUES (NULL,'Indoor');
INSERT INTO tbl_tag VALUES (NULL,'Sport');
INSERT INTO tbl_tag VALUES (NULL,'Quick');
INSERT INTO tbl_tag VALUES (NULL,'Chill');
INSERT INTO tbl_tag VALUES (NULL,'Active');
INSERT INTO tbl_tag VALUES (NULL,'Food');
INSERT INTO tbl_tag VALUES (NULL,'Music');
