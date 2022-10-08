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
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    alt VARCHAR(255) null,
    name VARCHAR(50),
    path VARCHAR(255) NOT NULL,
    usernaem VARCHAR(100) NOT NULL,

    CONSTRAINT FK_UserImage FOREIGN KEY (usernaem)
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