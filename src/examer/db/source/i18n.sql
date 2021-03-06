BEGIN TRANSACTION;

-- Table language
CREATE TABLE IF NOT EXISTS `language` ( 
    `idLang` INTEGER NOT NULL PRIMARY KEY,
    `abbrev` VARCHAR ( 4 ),
    `libelle` VARCHAR ( 64 )
);
-- Content:
INSERT INTO `language` VALUES 
    (1, "AR", "العربية"), 
    (2, "FR", "Français"), 
    (3, "EN", "English")
;

-- Table i18n
CREATE TABLE IF NOT EXISTS `i18n` ( 
    `idLibelle` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    `libelle` VARCHAR ( 64 )
);
-- Content:
INSERT INTO `i18n` VALUES 
    (12, "APP_TITLE"),
    (13, "EXAM_ATTACHED_BRANCHES"),
    (14, "CURRENT_MATTERS_SC"),
    (15, "REGISTRED_STUDENTS_SC"),
    (16, "ABSENTS"),
    (17, "CHEATERS"),
    (18, "PLANNINGS_AND_ABSENCES_SC"),
    (19, "BRANCH"),
    (20, "MATTER"),
    (21, "DATE"),
    (22, "START"),
    (23, "DURATION"),
    (24, "END"),
    (25, "ABSENTS_SC"),
    (26, "TOTAL"),
    (27, "FEMALES"),
    (28, "ROOM_SC"),
    (29, "BRANCH_SC"),
    (30, "MATTER_SC"),
    (31, "CHECK_ALL"),
    (32, "CHECK_POINTED_ELSEWHERE"),
    (33, "SAVE"),
    (34, "ABSENTS_SUM"),
    (35, "CHEATERS_SUM"),
    (36, "DISPLAY"),
    (37, "ROOMS"),
    (38, "GENERATE_ROOMS_SC"),
    (39, "STARTING_FROM_SC"),
    (40, "ENDING_IN_SC"),
    (41, "ADD"),
    (43, "REFRESH"),
    (44, "DELETE"),
    (45, "UPDATE"),  
    (46, "CANCEL"),  
    (47, "STATISTICS_SC"),  
    (48, "ROOMS_NUMBER_SC"),  
    (49, "MAX_CANDIDATES_PER_ROOM_SC"),  
    (50, "MIN_CANDIDATES_PER_ROOM_SC"),  
    (51, "AVG_CANDIDATES_PER_ROOM_SC"),  
    (52, "POPULATED_REGISTERED_ROOMS_SC"),  
    (53, "ROOM_NUMERO"),  
    (54, "CANDIDATES"),  
    (55, "PLANNING"),  
    (56, "ATTACH_BRANCH_TO_EXAM_SC"),  
    (57, "STARTING_DATE_SC"),  
    (58, "NON_PLANNED_BRANCHES"),  
    (59, "ASSIGN"),  
    (60, "EXAM_PLANNED_BRANCHES_SC"),  
    (61, "PLANNED_BRANCHES"),  
    (62, "NEW_DATE_SC"),  
    (63, "DO_PLAN_SC"),  
    (64, "PLAN_SC"),  
    (65, "TODAY_PLAN"),  
    (66, "STATUS"),  
    (67, "PRINTABLES"),  
    (68, "BRANCHES_SC"),  
    (69, "CHOOSE_A_BRANCH"),  
    (70, "BRANCHES_LIST"),  
    (71, "ALL"),  
    (72, "TODAY"),  
    (73, "CURRENT"),  
    (74, "DEFINE_ROOMS_MAX_NUMBER"),  
    (75, "INCLUDE_ABSENTS_INFOS"),  
    (76, "DESTINATION_FOLDER"),  
    (77, "BROWSE"),  
    (78, "GENERATE"),  
    (79, "ROOMS_SC"),  
    (80, "TITLE_SC"),  
    (81, "MATTERS_LIST"),  
    (83, "TRIALS"),  
    (84, "TAPE_EXAM_TITLE"),  
    (85, "SESSION_SC"),  
    (86, "ORDINARY"),  
    (87, "SECONDARY"),  
    (88, "PRESIDENT_NAME_SC"),  
    (89, "TAPE_A_NAME"),  
    (90, "PRESIDENT_CODE_SC"),  
    (91, "TAPE_A_CODE"),  
    (92, "SURVEILANT_NAME_SC"),  
    (93, "SURVEILANT_CODE_SC"),  
    (94, "SECRETARY_NAME_SC"),  
    (95, "SECRETARY_CODE_SC"),  
    (96, "OPENING"),  
    (97, "CLOSING"),  
    (98, "EXAM_CENTER"),  
    (99, "CHOOSE_AN_EXAM_CENTER"),  
    (100, "CENTERS_LIST"),  
    (101, "OR_CREATE_ANOTHER_CENTER"),  
    (102, "ACADEMY_SC"),  
    (103, "ACADEMIES_LIST"),  
    (104, "DIRECTION_SC"),  
    (105, "TAPE_DIRECTION_NAME"),  
    (106, "CENTER_NAME"),  
    (107, "TAPE_CENTER_NAME"),  
    (108, "CREATE"),   
    (110, "CHOOSE"),  
    (111, "GENERATE_CANDIDATES_PER_ROOM_SC"),  
    (112, "CLEAN"),  
    (113, "STARTING_CODE_SC"),  
    (114, "ENDING_CODE_SC"),  
    (115, "CHECK_GENDERS_AND_SECOND_FOREIGN_LANGUAGES_SC"),  
    (116, "HOME"),  
    (117, "PLANNING"),  
    (118, "SPECIAL_CANDIDATES"),  
    (119, "CENTER"),  
    (120, "PRINTABLES"),  
    (121, "PLANNINGS_LIST_SC"),  
    (122, "PLANNINGS_LIST"),  
    (123, "NEW_PLANNING_SC"),  
    (124, "UPDATE_A_PLANNING_SC"),  
    (125, "PERIOD_SC"),  
    (126, "SEANCE_SC"),  
    (127, "HOUR_SC"),  
    (128, "DURATION_SC"),  
    (129, "MORNING"),  
    (130, "AFTERNOON"),  
    (131, "DO_UPDATE"),  
    (133, "RENAME_SC"),  
    (134, "APPLY"),  
    (135, "CHOOSE_AN_EXAM_SC"),  
    (136, "EXAMS_LIST"),  
    (137, "PROCEED"),  
    (138, "OR_CREATE_A_NEW_EXAM"),  
    (139, "LABEL_SC"),  
    (140, "TAPE_EXAM_NAME"),  
    (141, "TAPE_EXAM_SESSION"),  
    (142, "LEVEL_SC"),  
    (143, "TAPE_LEVEL_EXAM"),  
    (144, "DELETE_WARNING"),  
    (145, "FIRST_CODE"),  
    (146, "LAST_CODE"),  
    (147, "DO_PLAN"),  
    (148, "MATTERS_SC"),  
    (149, "CHECK_ABSENTS"),  
    (150, "CHECK_CHEATERS"),  
    (151, "ONE_ROOM"),  
    (152, "TWO_ROOMS"),  
    (153, "3_TO_10_ROOMS"),  
    (154, "ABOVE_10_ROOMS"),  
    (155, "START_SC"),  
    (156, "DURATION_SC"), 
    (161, "ONE_MINUTE"),  
    (162, "TWO_MINUTES"),  
    (163, "3_TO_10_MINUTES"),  
    (164, "ABOVE_10_MINUTES"), 
    (165, "ONE_HOUR"),  
    (166, "TWO_HOURS"),  
    (167, "3_TO_10_HOURS"),  
    (168, "ABOVE_10_HOURS"), 
    (169, "ONE_DAY"),  
    (170, "TWO_DAYS"),  
    (171, "3_TO_10_DAYS"),  
    (172, "ABOVE_10_DAYS"),  
    (173, "AND"),  
    (174, "REMAINING"),  
    (175, "REMAINING_NEXT"),  
    (176, "TRIALS_DUPLICATES_MESSAGE"),  
    (177, "SELECT_EXAM"), 
    (178, "ONE_CANDIDATE"),  
    (179, "TWO_CANDIDATES"),  
    (180, "3_TO_10_CANDIDATES"),  
    (181, "ABOVE_10_CANDIDATES"),  
    (182, "ROOM"),  
    (183, "RAS"),  
    (184, "CONTROL_ABSENTS_SC"),
    (185, "CONTROL_CHEATERS_SC"),  
    (186, "CENTERS_LIST_SC"),  
    (187, "RUNNING"),  
    (188, "WAITING"),  
    (189, "DONE"),  
    (190, "WELCOME"),  
    (191, "CURRENT_ABSENTS"),  
    (192, "ALL_EXAM_COUNTED_ABSENTS"),  
    (193, "CURRENT_COUNTED_ABSENTS"),
    (194, "PLANNINGS_AND_TIMINGS"),
    (195, "EXAM_CODE"),
    (196, "FEMALE"),
    (197, "SECOND_FOREIGN_LANGUAGE"),
    (198, "GENDER"),
    (199, "UNDEFINED_LANGUAGE"),
    (200, "ENGLISH"),
    (201, "SPANISH"),
    (202, "ITALIAN"),
    (203, "GERMAN"),
    (204, "MALE"),
    (205, "GENERALIZE_LANGUAGE_SC"),
    (206, "GENERALIZE_GENDER_SC"),
    (207, "BRANCHES"),
    (208, "BIG_ENVELOPS"),
    (209, "SMALL_ENVELOPS"),
    (210, "TRIALS_SHORT"),
    (211, "CONSIDERE_SFL"),
    (212, "CURRENT_CANDIDATES"),
    (213, "CURRENT_CHEATERS"),
    (214, "EMPTY_TABLE"),
    (215, "APPLICATION"),
    (216, "COMMANDS"),
    (217, "QUESTION_MARK"),
    (218, "QUIT"),
    (219, "CHOOSE_ANOTHER_EXAM"),
    (220, "ABOUT"),
    (221, "UNDER_ONE_MINUTE")
;

-- Table translate
CREATE TABLE IF NOT EXISTS `translate` ( 
    `idLibelle` INTEGER,
    `idLang` INTEGER,
    `synonym` TEXT,
    PRIMARY KEY(`idLibelle`,`idLang`),
    FOREIGN KEY(`idLibelle`) REFERENCES `i18n`(`idLibelle`),
    FOREIGN KEY(`idLang`) REFERENCES `language`(`idLang`)
);
-- Content:
INSERT INTO `translate` VALUES 
    (12, 1, "ممتحن"), (12, 2, "Examinateur"), (12, 3, "Examer"), 
    (13, 1, "المسالك الممتحنة:"), (13, 2, "Filières attachées à l'examen:"), (13, 3, ""),  
    (14, 1, "المواد الممتحنة حاليا:"), (14, 2, "Matières en cours:"), (14, 3, ""),  
    (15, 1, "المترشحون المسجلون:"), (15, 2, "Candidats inscrits:"), (15, 3, ""),  
    (16, 1, "المتغيبون"), (16, 2, "Absents"), (16, 3, ""),  
    (17, 1, "الغاشون"), (17, 2, "Tricheurs"), (17, 3, ""),  
    (18, 1, "تواريخ ومواقيت الامتحان وإحصاء المتغيبين:"), (18, 2, "Planning et absences:"), (18, 3, ""),  
    (19, 1, "المسلك أوالشعبة"), (19, 2, "Filière"), (19, 3, ""),  
    (20, 1, "المادة"), (20, 2, "Matière"), (20, 3, ""),  
    (21, 1, "التاريخ"), (21, 2, "Date"), (21, 3, ""),  
    (22, 1, "البداية"), (22, 2, "Début"), (22, 3, ""),  
    (23, 1, "المدة"), (23, 2, "Durée"), (23, 3, ""),  
    (24, 1, "النهاية"), (24, 2, "Fin"), (24, 3, ""),  
    (25, 1, "المتغيبون:"), (25, 2, "Absents:"), (25, 3, ""),  
    (26, 1, "المجموع"), (26, 2, "Total"), (26, 3, ""),  
    (27, 1, "الإناث"), (27, 2, "Femelles"), (27, 3, ""),  
    (28, 1, "القاعة:"), (28, 2, "Salle:"), (28, 3, ""),  
    (29, 1, "المسلك أوالشعبة:"), (29, 2, "Filière:"), (29, 3, ""),  
    (30, 1, "المادة:"), (30, 2, "Matière:"), (30, 3, ""),  
    (31, 1, "تحديد الجميع"), (31, 2, "Tout cocher"), (31, 3, ""),  
    (32, 1, "تحديد العناصر سابقة التسجيل في مكان آخر."), (32, 2, "Cocher les éléments pointés ici et ailleurs"), (32, 3, ""),  
    (33, 1, "حفـــظ"), (33, 2, "Sauvegarder"), (33, 3, ""),  
    (34, 1, "مجموع المترشحين المتغيبين"), (34, 2, "Somme des candidats absents"), (34, 3, ""),  
    (35, 1, "مجموع المترشحين الغاشين"), (35, 2, "Somme des candidats tricheurs"), (35, 3, ""),  
    (36, 1, "إظهـار"), (36, 2, "Afficher"), (36, 3, ""),  
    (37, 1, "القاعات"), (37, 2, "Salles"), (37, 3, ""),  
    (38, 1, "إنشاء قاعات:"), (38, 2, "Générer des salles:"), (38, 3, ""),  
    (39, 1, "ابتداء من:"), (39, 2, "A partir de:"), (39, 3, ""),  
    (40, 1, "إلى:"), (40, 2, "Jusqu'à:"), (40, 3, ""),  
    (41, 1, "إضافة"), (41, 2, "Ajouter"), (41, 3, ""),  
    (43, 1, "تحيين"), (43, 2, "Rafraîchir"), (43, 3, ""),  
    (44, 1, "حــذف"), (44, 2, "Supprimer"), (44, 3, ""),  
    (45, 1, "تغيير"), (45, 2, "Modifier"), (45, 3, ""),  
    (46, 1, "إلغاء"), (46, 2, "Annuler"), (46, 3, ""),  
    (47, 1, "إحصائيات:"), (47, 2, "Statistiques:"), (47, 3, ""),  
    (48, 1, "العدد الإجمالي للقاعات:"), (48, 2, "Nombre total de salles:"), (48, 3, ""),  
    (49, 1, "العدد الأقصى للمترشحين في القاعات:"), (49, 2, "Nombre maximal de candidats par salle:"), (49, 3, ""),  
    (50, 1, "العدد الأدنى للمترشحين في القاعات:"), (50, 2, "Nombre minimal de candidats par salle:"), (50, 3, ""),  
    (51, 1, "متوسط المترشحين في القاعات:"), (51, 2, "Nombre moyen de candidats par salle:"), (51, 3, ""),  
    (52, 1, "القاعات غير الفارغة:"), (52, 2, "Salles peuplées enregistrées:"), (52, 3, ""),  
    (53, 1, "نمرة القاعة"), (53, 2, "N° Salle"), (53, 3, ""),  
    (54, 1, "المترشحون"), (54, 2, "Candidats"), (54, 3, ""),  
    (55, 1, "البرمجة"), (55, 2, "Planification"), (55, 3, ""),  
    (56, 1, "ربط مسلك أوشعبة:"), (56, 2, "Attacher une filière:"), (56, 3, ""),  
    (57, 1, "تاريخ البداية:"), (57, 2, "Date début:"), (57, 3, ""),  
    (58, 1, "المسالك أوالشعب غير المبرمجة"), (58, 2, "Filières non planifiées"), (58, 3, ""),  
    (59, 1, "ربــط"), (59, 2, "Assigner"), (59, 3, ""),  
    (60, 1, "المسالك أوالشعب المبرمجة:"), (60, 2, "Filières planifiées de cet examen:"), (60, 3, ""),  
    (61, 1, "المسالك أوالشعب المبرمجة"), (61, 2, "Filières planifiées"), (61, 3, ""),  
    (62, 1, "تاريخ جديد:"), (62, 2, "Nouvelle date:"), (62, 3, ""),  
    (63, 1, "برمجة المواد الممتحنة:"), (63, 2, "Planifier les matières de l'examen:"), (63, 3, ""),  
    (64, 1, "برنامج:"), (64, 2, "Plan:"), (64, 3, ""),  
    (65, 1, "برنامج اليوم"), (65, 2, "Programme d'aujourd'hui"), (65, 3, ""),  
    (66, 1, "الحالة"), (66, 2, "Statut"), (66, 3, ""),  
    (67, 1, "إنتاج المطبوعات"), (67, 2, "Production d'imprimés"), (67, 3, ""),  
    (68, 1, "المسالك أوالشعب:"), (68, 2, "Filières:"), (68, 3, ""),  
    (69, 1, "اختر مسلكا أوشعبة:"), (69, 2, "Choisir une filière:"), (69, 3, ""),  
    (70, 1, "قائمة المسالك أوالشعب"), (70, 2, "Liste des filières"), (70, 3, ""),  
    (71, 1, "الجميع"), (71, 2, "Tout"), (71, 3, ""),  
    (72, 1, "اليوم"), (72, 2, "Aujourd'hui"), (72, 3, ""),  
    (73, 1, "الآن"), (73, 2, "En cours"), (73, 3, ""),  
    (74, 1, "تحديد عدد أقصى من القاعات في كل ظرف:"), (74, 2, "Définir un nombre max de salles:"), (74, 3, ""),  
    (75, 1, "ضم معلومات المتغيبين وأعدادهم."), (75, 2, "Inclure les informations relatives aux absences."), (75, 3, ""),  
    (76, 1, "مجلد الحفظ:"), (76, 2, "Dossier de destination:"), (76, 3, ""),  
    (77, 1, "تصفح"), (77, 2, "Parcourir"), (77, 3, ""),  
    (78, 1, "إنتــاج"), (78, 2, "Générer"), (78, 3, ""),  
    (79, 1, "القاعات:"), (79, 2, "Salles:"), (79, 3, ""),  
    (80, 1, "العنوان:"), (80, 2, "Titre:"), (80, 3, ""),  
    (81, 1, "قائمة المواد"), (81, 2, "Liste des matières"), (81, 3, ""),  
    (83, 1, "محاضر الفتح والإغلاق:"), (83, 2, "Procès d'ouverture & fermeture:"), (83, 3, ""),  
    (84, 1, "أدرج عنوانا"), (84, 2, "Saisir un titre d'examen"), (84, 3, ""),  
    (85, 1, "الدورة:"), (85, 2, "Session:"), (85, 3, ""),  
    (86, 1, "العادية"), (86, 2, "Ordinaire"), (86, 3, ""),  
    (87, 1, "الاستدراكية"), (87, 2, "Rattrapage"), (87, 3, ""),  
    (88, 1, "اسم رئيس المركز:"), (88, 2, "Nom du président:"), (88, 3, ""),  
    (89, 1, "أدرج الاسم هنا"), (89, 2, "Saisir un nom"), (89, 3, ""),  
    (90, 1, "رقم تأجير الرئيس:"), (90, 2, "Code du président:"), (90, 3, ""),  
    (91, 1, "أدرج الرقم هنا"), (91, 2, "Saisir un code"), (91, 3, ""),  
    (92, 1, "اسم الملاحظ:"), (92, 2, "Nom du surveillant:"), (92, 3, ""),  
    (93, 1, "رقم تأجير الملاحظ:"), (93, 2, "Code du surveillant:"), (93, 3, ""),  
    (94, 1, "اسم الكاتب:"), (94, 2, "Nom du secrétaire:"), (94, 3, ""),  
    (95, 1, "رقم تأجير الكاتب:"), (95, 2, "Code du secrétaire:"), (95, 3, ""),  
    (96, 1, "محاضر الفتح"), (96, 2, "Ouverture"), (96, 3, ""),  
    (97, 1, "محاضر الإغلاق"), (97, 2, "Fermeture"), (97, 3, ""),  
    (98, 1, "مركز الامتحان"), (98, 2, "Centre d'examen"), (98, 3, ""),  
    (99, 1, "اختر مركز امتحان:"), (99, 2, "Choisir un centre d'examen:"), (99, 3, ""),  
    (100, 1, "قائمة المراكز"), (100, 2, "Liste des centres"), (100, 3, ""),  
    (101, 1, "أو قم بإنشاء مركز جديد:"), (101, 2, "Ou bien créer un nouveau centre:"), (101, 3, ""),  
    (102, 1, "الأكاديمية الجهوية:"), (102, 2, "Académie régionale:"), (102, 3, ""),  
    (103, 1, "قائمة الأكاديميات"), (103, 2, "Liste des académies"), (103, 3, ""),  
    (104, 1, "المديرية الإقليمية:"), (104, 2, "Direction provinciale:"), (104, 3, ""),  
    (105, 1, "أدرج المديرية الإقليمية هنا"), (105, 2, "Saisir la direction provinciale du centre"), (105, 3, ""),  
    (106, 1, "اسم المركز:"), (106, 2, "Nom du centre:"), (106, 3, ""),  
    (107, 1, "أدرج اسم المركز"), (107, 2, "Saisir le nom du centre"), (107, 3, ""),  
    (108, 1, "إنشاء"), (108, 2, "Créer"), (108, 3, ""),  
    (110, 1, "اختيار"), (110, 2, "Choisir"), (110, 3, ""),  
    (111, 1, "تسجيل مترشحين في قاعة:"), (111, 2, "Générer des candidats par salle:"), (111, 3, ""),  
    (112, 1, "حذف جميع المترشحين المسجلين هنا"), (112, 2, "Balayer"), (112, 3, ""),  
    (113, 1, "رقم الامتحان الأول:"), (113, 2, "Code début:"), (113, 3, ""),  
    (114, 1, "رقم الامتحان الأخير:"), (114, 2, "Code fin:"), (114, 3, ""),  
    (115, 1, "تحديد أجناس المترشحين ولغاتهم الأجنبية الثانية:"), (115, 2, "Déterminer les sexes et 2ièmes langues étrangères:"), (115, 3, ""),  
    (116, 1, "الرئيسية"), (116, 2, "Accueil"), (116, 3, ""),  
    (117, 1, "البرمجة"), (117, 2, "Planification"), (117, 3, ""),  
    (118, 1, "مترشحون خاصون"), (118, 2, "Candidats spéciaux"), (118, 3, ""),  
    (119, 1, "المركز"), (119, 2, "Centre"), (119, 3, ""),  
    (120, 1, "المطبوعات"), (120, 2, "Imprimés"), (120, 3, ""),  
    (121, 1, "قائمة المواقيت:"), (121, 2, "Liste des Plannings:"), (121, 3, ""),  
    (122, 1, "قائمة المواقيت"), (122, 2, "Liste des plannings"), (122, 3, ""),  
    (123, 1, "توقيت جديد:"), (123, 2, "Nouveau Planning:"), (123, 3, ""),  
    (124, 1, "تغيير التوقيت:"), (124, 2, "Mise à jour d'un Planning:"), (124, 3, ""),  
    (125, 1, "المرحلة:"), (125, 2, "Période:"), (125, 3, ""),  
    (126, 1, "الحصة:"), (126, 2, "Séance:"), (126, 3, ""),  
    (127, 1, "الساعة:"), (127, 2, "Heure:"), (127, 3, ""),  
    (128, 1, "المدة:"), (128, 2, "Durée:"), (128, 3, ""),  
    (129, 1, "الصباح"), (129, 2, "Matin"), (129, 3, ""),  
    (130, 1, "بعد الزوال"), (130, 2, "Après-midi"), (130, 3, ""),  
    (131, 1, "تحيين"), (131, 2, "Mettre à jour"), (131, 3, ""),  
    (133, 1, "إعادة التسمية:"), (133, 2, "Renommer:"), (133, 3, ""),  
    (134, 1, "تثبيت"), (134, 2, "Appliquer"), (134, 3, ""),  
    (135, 1, "اختر امتحانا:"), (135, 2, "Sélectionner un examen:"), (135, 3, ""),  
    (136, 1, "قائمة الامتحانات"), (136, 2, "Liste des examens"), (136, 3, ""),  
    (137, 1, "بــدء"), (137, 2, "Procéder"), (137, 3, ""),  
    (138, 1, "أو قم بإنشاء امتحان جديد:"), (138, 2, "ou bien créer un nouvel examen:"), (138, 3, ""),  
    (139, 1, "التسمية:"), (139, 2, "Libellé:"), (139, 3, ""),  
    (140, 1, "أدرج اسم امتحان"), (140, 2, "Saisir un nom d'examen"), (140, 3, ""),  
    (141, 1, "أدرج دورة الامتحان"), (141, 2, "Saisir une session d'examen"), (141, 3, ""),  
    (142, 1, "المستوى:"), (142, 2, "Niveau:"), (142, 3, ""),  
    (143, 1, "أدرج مستوى امتحان"), (143, 2, "Saisir un niveau d'examen"), (143, 3, ""),  
    (144, 1, "هذه العملية ستحذف جميع القاعات، والمترشحين وأيضا جميع المواقيت المرتبطة بهذا الامتحان."), (144, 2, "Ceci effacera aussi toutes les salles, tous les candidats  et tous les plannings de la filière."), (144, 3, ""),  
    (145, 1, "أول رقم"), (145, 2, "Premier"), (145, 3, ""),  
    (146, 1, "آخر رقم"), (146, 2, "Dernier"), (146, 3, ""),
    (147, 1, "برمجة"), (147, 2, "Planifier"), (147, 3, ""),
    (148, 1, "المواد:"), (148, 2, "Matières:"), (148, 3, ""),
    (149, 1, "تحديد المترشحين المتغيبين:"), (149, 2, "Cocher les candidats absents:"), (149, 3, ""),
    (150, 1, "تحديد المترشحين الغاشين:"), (150, 2, "Cocher les candidats tricheurs:"), (150, 3, ""),
    (151, 1, "قاعة واحدة"), (151, 2, "une salle"), (151, 3, ""),
    (152, 1, "قاعتان اثنتان"), (152, 2, "deux salles"), (152, 3, ""),
    (153, 1, "%d قاعات"), (153, 2, "%d salles"), (153, 3, ""),
    (154, 1, "%d قاعة"), (154, 2, "%d salles"), (154, 3, ""),
    (155, 1, "البداية: "), (155, 2, "Début: "), (155, 3, ""),
    (156, 1, "المدة: "), (156, 2, "Durée: "), (156, 3, ""),
    (161, 1, "دقيقة واحدة"), (161, 2, "une minute"), (161, 3, ""),
    (162, 1, "دقيقتان"), (162, 2, "%d minutes"), (162, 3, ""),
    (163, 1, "%d دقائق"), (163, 2, "%d minutes"), (163, 3, ""),
    (164, 1, "%d دقيقة"), (164, 2, "%d minutes"), (164, 3, ""),
    (165, 1, "ساعة واحدة"), (165, 2, "une heure"), (165, 3, ""),
    (166, 1, "ساعتان"), (166, 2, "%d heures"), (166, 3, ""),
    (167, 1, "%d ساعات"), (167, 2, "%d heures"), (167, 3, ""),
    (168, 1, "%d ساعة"), (168, 2, "%d heures"), (168, 3, ""),
    (169, 1, "يوم واحد"), (169, 2, "un jour"), (169, 3, ""),
    (170, 1, "يومان"), (170, 2, "%d jours"), (170, 3, ""),
    (171, 1, "%d أيام"), (171, 2, "%d jours"), (171, 3, ""),
    (172, 1, "%d يوما"), (172, 2, "%d jours"), (172, 3, ""),
    (173, 1, " و"), (173, 2, " et "), (173, 3, ""),
    (174, 1, "الزمن المتبقي لانتهاء الامتحان الحالي"), (174, 2, "Temps restant avant la fin de l'examen courant"), (174, 3, ""),
    (175, 1, "الزمن المتبقي لانطلاق الامتحان الموالي"), (175, 2, "Temps restant avant le début de l'examen prochain"), (175, 3, ""),
    (176, 1, "سيتم إنتاج ثلاث نسخ من كل صفحة في نفس المستند."), (176, 2, "Trois copies de chaque page seront générées dans le même fichier."), (176, 3, ""),
    (177, 1, "اختيار امتحان آخر"), (177, 2, "Choisir un autre examen."), (177, 3, ""),
    (178, 1, "مترشح واحد"), (178, 2, "un candidat"), (178, 3, ""),
    (179, 1, "مترشحان"), (179, 2, "%d candidats"), (179, 3, ""),
    (180, 1, "%d مترشحين"), (180, 2, "%d candidats"), (180, 3, ""),
    (181, 1, "%d مترشحا"), (181, 2, "%d candidats"), (181, 3, ""),
    (182, 1, "القاعة"), (182, 2, "Salle"), (182, 3, ""),
    (183, 1, "لا شيء"), (183, 2, "Rien à signaler"), (183, 3, ""),
    (184, 1, "ضبط المترشحين المتغيبين:"), (184, 2, "Contrôle des candidats absents:"), (184, 3, ""),
    (185, 1, "ضبط المترشحين الغاشين:"), (185, 2, "Contrôle des candidats tricheurs:"), (185, 3, ""),  
    (186, 1, "قائمة المراكز:"), (186, 2, "Liste des centres:"), (186, 3, ""),  
    (187, 1, "سار حاليا"), (187, 2, "En cours"), (187, 3, ""),  
    (188, 1, "في الانتظار"), (188, 2, "En attente"), (188, 3, ""),  
    (189, 1, "انتهى"), (189, 2, "Terminé"), (189, 3, ""),  
    (190, 1, "مرحبا"), (190, 2, "Bienvenue"), (190, 3, ""),  
    (191, 1, "المتغيبون حاليا"), (191, 2, "Absents courants"), (191, 3, ""),  
    (192, 1, "المتغيبون المسجلون في الامتحان كله"), (192, 2, "Les absents enregistrés dans l'examen tout entier"), (192, 3, ""),  
    (193, 1, "المتغيبون المسجلون في المسالك الممتحنة حاليا"), (193, 2, "Les absents enregistrés dans les branches examinées actuellement"), (193, 3, ""),  
    (194, 1, "تواريخ ومواقيت الامتحان"), (194, 2, "Dates et plannings de l'examen"), (194, 3, ""),  
    (195, 1, "أرقام الامتحان"), (195, 2, "Code d'examen"), (195, 3, ""),  
    (196, 1, "أنثى"), (196, 2, "Femelle"), (196, 3, ""),  
    (197, 1, "اللغة الأجنبية الثانية"), (197, 2, "Deuxième langue étrangère"), (197, 3, ""),  
    (198, 1, "الجنس"), (198, 2, "Sexe"), (198, 3, ""),  
    (199, 1, "لغة غير محددة"), (199, 2, "Langue indéterminée"), (199, 3, ""),  
    (200, 1, "اللغة الإنجليزية"), (200, 2, "Anglais"), (200, 3, ""),  
    (201, 1, "اللغة الإسبانية"), (201, 2, "Espagnol"), (201, 3, ""),  
    (202, 1, "اللغة الإيطالية"), (202, 2, "Italien"), (202, 3, ""),  
    (203, 1, "اللغة الألمانية"), (203, 2, "Allemand"), (203, 3, ""),  
    (204, 1, "ذكـر"), (204, 2, "Male"), (204, 3, ""),  
    (205, 1, "تعميم اللغة الأجنبية الثانية التالية:"), (205, 2, "Généraliser la 2ième langue étrangère suivante:"), (205, 3, ""),  
    (206, 1, "تعميم الجنس التالي:"), (206, 2, "Généraliser le sexe suivant:"), (206, 3, ""),  
    (207, 1, "المسالك"), (207, 2, "Branches"), (207, 3, ""),  
    (208, 1, "ترويسات الأظرفة الكبيرة"), (208, 2, "Entêtes des grandes enveloppes"), (208, 3, ""),  
    (209, 1, "ترويسات الأظرفة الصغيرة"), (209, 2, "Entêtes des petites enveloppes"), (209, 3, ""),  
    (210, 1, "المحاضر"), (210, 2, "Procès"), (210, 3, ""),  
    (211, 1, "تجزيء القاعات متعددة اللغات الأجنبية الثانية."), (211, 2, "Diviser les salles multilingues (2ième langue étrangère)."), (211, 3, ""),  
    (212, 1, "المترشحون الحاليون"), (212, 2, "Candidats courants"), (212, 3, ""),  
    (213, 1, "الغاشون الحاليون"), (213, 2, "Tricheurs courants"), (213, 3, ""),  
    (214, 1, "جدول فارغ"), (214, 2, "Table vide"), (214, 3, ""),  
    (215, 1, "تطبيق"), (215, 2, "Fichier"), (215, 3, ""),  
    (216, 1, "عمليات"), (216, 2, "Commandes"), (216, 3, ""),  
    (217, 1, "؟"), (217, 2, "?"), (217, 3, ""),  
    (218, 1, "خروج"), (218, 2, "Quitter"), (218, 3, ""),  
    (219, 1, "اختيار امتحان آخر"), (219, 2, "Choisir un autre examen"), (219, 3, ""),  
    (220, 1, "عن البرمجية"), (220, 2, "A propos"), (220, 3, ""),  
    (221, 1, "أقل من دقيقة"), (221, 2, "Moins d'une minute"), (221, 3, "")
;

COMMIT;