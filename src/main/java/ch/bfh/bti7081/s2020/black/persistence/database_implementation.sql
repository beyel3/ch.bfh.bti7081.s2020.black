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
    imageID INTEGER PRIMARY KEY,
    image BLOB
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