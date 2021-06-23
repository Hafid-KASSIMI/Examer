-- * Author:  Sicut
-- * Created: 22 oct. 2020

BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS `Branch` (
    `idBranch`	INTEGER NOT NULL PRIMARY KEY,
    `label`	VARCHAR(1024)
);

INSERT INTO `Branch` VALUES 
    (1, "الآداب والعلوم الإنسانية"),
    (2, "الآداب"),
    (3, "العلوم الإنسانية"),
    (4, "العلوم التجريبية"),
    (5, "العلوم التجريبية - خيار فرنسية"),
    (6, "العلوم الفيزيائية"),
    (7, "العلوم الفيزيائية - خيار فرنسية"),
    (8, "علوم الحياة والأرض"),
    (9, "علوم الحياة والأرض - خيار فرنسية"),
    (10, "العلوم الزراعية"),
    (11, "العلوم الرياضية (أ)"),
    (12, "العلوم الرياضية (ب)"),
    (13, "العلوم الرياضية (أ) - خيار فرنسية"),
    (14, "العلوم الرياضية (ب) - خيار فرنسية"),
    (15, "علوم الاقتصاد")
;

CREATE TABLE IF NOT EXISTS `Matter` (
    `idMatter`	INTEGER NOT NULL PRIMARY KEY,
    `label`	VARCHAR(1024)
);

INSERT INTO `Matter` VALUES 
    (1, "اللغة العربية"),
    (2, "اللغة الفرنسية"),
    (3, "اللغة الإنجليزية"),
    (4, "اللغة الإسبانية"),
    (5, "اللغة الأجنبية الثانية"),
    (6, "التربية الإسلامية"),
    (7, "الفلسفة"),
    (8, "التاريخ والجغرافيا"),
    (9, "الرياضيات"),
    (10, "الفيزياء والكيمياء"),
    (11, "علوم الحياة والأرض")
;

CREATE TABLE IF NOT EXISTS `Region` (
    `idRegion`	INTEGER NOT NULL PRIMARY KEY,
    `labelAR`	VARCHAR(1024),
    `labelTA`	VARCHAR(1024)
);

INSERT INTO `Region` VALUES 
    (0, "##", "##"),
    (1, "درعة تافيلالت", "dra tafilalt"),
    (2, "فاس مكناس", "fas mknas"), 
    (3, "الرباط سلا القنيطرة", "eëëbaï sla qniïra"), 
    (4, "سوس ماسة", "sus massa"),
    (5, "مراكش آسفي", "mëëakc asfi"),
    (6, "الدار البيضاء سطات", "kaça açïïaä"),
    (7, "بني ملال خنيفرة", "bni mllal xnifra"),
    (8, "الجهة الشرقية", "tagmuäant"),
    (9, "طنجة تطوان الحسيمة", "ïanja tiïawin lpusima"),
    (10, "كلميم واد نون", "gulmim wad nun"),
    (11, "العيون الساقية الحمراء", "loyun ssaqya lhamra"),
    (12, "الداخلة وادي الذهب", "ddaxla wad ddahab");

CREATE TABLE IF NOT EXISTS `Center` (
    `idCenter`	INTEGER NOT NULL PRIMARY KEY,
    `direction`	VARCHAR(1024),
    `label`	VARCHAR(1024),
    `idRegion`	INTEGER
);

INSERT INTO `Center` VALUES 
    (0, "##", "##", 0);

CREATE TABLE IF NOT EXISTS `Exam` (
    `idExam`	INTEGER NOT NULL PRIMARY KEY,
    `label`	VARCHAR(1024),
    `examSession`	VARCHAR(1024),
    `examLevel`	VARCHAR(1024),
    `idCenter`	INTEGER,
    FOREIGN KEY (`idCenter`) REFERENCES `Center`(`idCenter`)
);

CREATE TABLE IF NOT EXISTS `Exam_branch` (
    `idExam`	INTEGER,
    `idBranch`	INTEGER,
    `startingDate`	DATE,
    PRIMARY KEY (`idExam`, `idBranch`),
    FOREIGN KEY (`idExam`) REFERENCES `Exam`(`idExam`),
    FOREIGN KEY (`idBranch`) REFERENCES `Branch`(`idBranch`)
);

CREATE TABLE IF NOT EXISTS `Room` (
    `idRoom`	INTEGER NOT NULL PRIMARY KEY,
    `numero`	INTEGER,
    `idBranch`	INTEGER,
    FOREIGN KEY (`idBranch`) REFERENCES `Branch`(`idBranch`)
);

CREATE TABLE IF NOT EXISTS `Plan` (
    `idPlan`	INTEGER NOT NULL PRIMARY KEY,
    `startTime`	TIME,
    `duration`	TIME,
    `seance`	SMALLINT,
    `period`	VARCHAR(2)
);

-- SFL: Second foreign language
CREATE TABLE IF NOT EXISTS `SFL` (
    `idSFL`	INTEGER NOT NULL PRIMARY KEY,
    `abrev`	VARCHAR(5),
    `label`	VARCHAR(512)
);

INSERT INTO `SFL` VALUES 
    (0, "##", "##"),
    (1, "EN", "الإنجليزية"),
    (2, "CA", "الإسبانية"),
    (3, "IT", "الإيطالية"),
    (4, "DE", "الألمانية");

CREATE TABLE IF NOT EXISTS `Candidate` (
    `idCandidate`	INTEGER NOT NULL PRIMARY KEY,
    `code`	INTEGER,
    `gender`	CHAR,
    `idSFL`	INTEGER,
    `idRoom`	INTEGER,
    FOREIGN KEY (`idSFL`) REFERENCES `SFL`(`idSFL`),
    FOREIGN KEY (`idRoom`) REFERENCES `Room`(`idRoom`)
);

CREATE TABLE IF NOT EXISTS `Absent` (
    `idCandidate`	INTEGER,
    `idMatter`	INTEGER,
    `justification`	VARCHAR(1024),
    PRIMARY KEY (`idCandidate`, `idMatter`),
    FOREIGN KEY (`idCandidate`) REFERENCES `Candidate`(`idCandidate`),
    FOREIGN KEY (`idMatter`) REFERENCES `Matter`(`idMatter`)
);

CREATE TABLE IF NOT EXISTS `Cheater` (
    `idCandidate`	INTEGER,
    `idMatter`	INTEGER,
    `sanction`	VARCHAR(1024),
    PRIMARY KEY (`idCandidate`, `idMatter`),
    FOREIGN KEY (`idCandidate`) REFERENCES `Candidate`(`idCandidate`),
    FOREIGN KEY (`idMatter`) REFERENCES `Matter`(`idMatter`)
);

CREATE TABLE IF NOT EXISTS `PlMB` (
    `idMatter`	INTEGER,
    `idBranch`	INTEGER,
    `idExam`	INTEGER,
    `idPlan`	INTEGER,
    `planDate`	DATE,
    PRIMARY KEY (`idMatter`, `idBranch`),
    FOREIGN KEY (`idMatter`) REFERENCES `Matter`(`idMatter`),
    FOREIGN KEY (`idBranch`) REFERENCES `Branch`(`idBranch`),
    FOREIGN KEY (`idExam`) REFERENCES `Exam`(`idExam`)
);

COMMIT;
