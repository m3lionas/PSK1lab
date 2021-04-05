create table festival (
    ID IDENTITY NOT NULL PRIMARY KEY,
    NAME VARCHAR(255)
);
create table band(
    ID IDENTITY NOT NULL PRIMARY KEY,
    NAME VARCHAR(255)
);
create table festivalband(
    ID identity NOT NULL PRIMARY KEY,
    BAND_ID INT8,
    foreign key (BAND_ID) references band(ID),
    FESTIVAL_ID INT8,
    foreign key (FESTIVAL_ID) references festival(ID)
);
create table member(
    ID identity NOT NULL PRIMARY KEY,
    NAME VARCHAR(255),
    BAND_ID INT8,
    foreign key (BAND_ID) references band(ID)
);

