-- Active: 1664296356986@@10.224.12.14@3306@blogpj

create table if not exists Users(
    username VARCHAR(100) not NULL PRIMARY KEY,
    password VARCHAR(255) NOT null,
    email VARCHAR(255),
    create_at DATETIME not NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP
);

create table if not exists Posts(
    id int not null AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT null,
    content TEXT ,
    create_at DATETIME not NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP,
    user_id VARCHAR(100) NOT NULL,
    CONSTRAINT FK_UserPost FOREIGN KEY (user_id)
    REFERENCES Users(username)
);

create TABLE IF NOT EXISTS Images(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    alt VARCHAR(255) null,
    name VARCHAR(50),
    path VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,

    CONSTRAINT FK_UserImage FOREIGN KEY (username)
    REFERENCES Users(Username)

);

/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
INSERT INTO Users(username,password,email) VALUES
    ("aniruts","123",""),
    ("Phantom","abc","vill@a.com") ;
INSERT INTO Posts(id,title,content,user_id) VALUES
    ( null, "1post", "Hello world", "aniruts"),
    ( null, "2post", "Hello world", "aniruts"),
    ( null, "3post", "Hello world", "aniruts"),
    ( null, "4post", "Hello world", "aniruts"),
    ( null, "5post", "Hello world", "aniruts")
;

/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=== */
CREATE Table tmp
(
    id int PRIMARY KEY,
    name VARCHAR(100),
    ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);





























/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
/* V2 */
SET FOREIGN_KEY_CHECKS = 0;
    DROP TABLE IF EXISTS User;
    DROP TABLE IF EXISTS Image;
    DROP TABLE IF EXISTS Post;
SET FOREIGN_KEY_CHECKS = 1;

CREATE Table User(
    username VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    imgId int,
    create_at DATETIME not NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT PK_User PRIMARY KEY(username)
);

CREATE Table Image(
    imgId int NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    name VARCHAR(255),
    alt VARCHAR(255),
    url VARCHAR(255) NOT NULL,
    
    CONSTRAINT FK_ImageOfUser FOREIGN KEY(username)
    REFERENCES User(username),
    CONSTRAINT PK_Image PRIMARY KEY(imgId)
);

ALTER TABLE User
    ADD CONSTRAINT PK_UserImage
    FOREIGN KEY(username,imgId)
    REFERENCES Image(username,imgId);

CREATE Table Post(
    postId int NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    imgId INT,
    title VARCHAR(255),
    content TEXT,
    short_content TEXT,
    create_at DATETIME not NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT PK_Post PRIMARY KEY(postID),
    CONSTRAINT FK_PostUser FOREIGN KEY(username) REFERENCES User(username),
    CONSTRAINT FK_PostImage FOREIGN KEY(imgId) REFERENCES Image(imgId)
);


INSERT INTO User (username,password,email,imgId) VALUES
    ( "villium","password","a@com",null)
;
INSERT INTO Image VALUES
    ( null,"villium", "profile",null,"null")
;
INSERT INTO Post (postId,username,imgId,title,content,short_content)  VALUES
    ( null, "villium", 1, "hey","content","cont")
    ;