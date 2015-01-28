create table USERS (
    userid varchar(80) not null,   
    firstname varchar(80) not null,
    lastname varchar(80) not null,    
    password varchar(80) not null,
    constraint pk_account primary     key (userid),
    CONSTRAINT fk_PerOrders FOREIGN KEY (userid) REFERENCES Persons(P_Id)
);


create table USERS3 (
    user_id varchar(80) not null,   
    first_name varchar(80) not null,
    last_name varchar(80) not null,    
    password varchar(80) not null,
    constraint pk_account primary     key (userid),
    CONSTRAINT fk_PerOrders FOREIGN KEY (userid) REFERENCES Persons(P_Id)
);

